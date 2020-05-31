package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-17 10:17 上午
 */
public class TestInterrupt {
    public static void main(String[] args)  {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        });
        thread.start();
        Thread thread2 = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_0000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread2.interrupt();
            }
        });

        thread1.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
