package com.cas;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author LiXingyu
 * @date 2020-05-19 9:11 上午
 */
public class AlarmAgent {
    public static Object lock = new Object();
    volatile boolean isConnect = false;
    static Socket socket;

    public void heartDetect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                        try {
                            OutputStream outputStream = socket.getOutputStream();
                            outputStream.write("".getBytes());
                            outputStream.flush();
                            isConnect = true;
                            lock.notifyAll();
                        } catch (IOException e) {
                            isConnect = false;
                        }
                }

            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void connectHost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("127.0.0.1", 9080);
                } catch (IOException e) {
                    isConnect = false;
                }
            }

        });
        thread.setDaemon(true);
        thread.start();
    }

    public void sendMessage() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (!isConnect) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (isConnect) {
                        try {
                            OutputStream outputStream = socket.getOutputStream();
                            outputStream.write("error message report".getBytes());
                            outputStream.flush();
                            Thread.sleep(10_000);
                        } catch (IOException | InterruptedException e) {
                            isConnect = false;
                        }
                    }


                }

            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        AlarmAgent alarmAgent = new AlarmAgent();

        alarmAgent.connectHost();
        while(true){
            alarmAgent.heartDetect();
            Thread.sleep(5_000);
            alarmAgent.sendMessage();
        }
    }

}


