package com.cas;

import java.io.*;
import java.util.Random;

/**
 * @author LiXingyu
 * @date 2020-05-21 9:11 上午
 */
public class ShootPractice {

    static class ReadFile implements Runnable {
        PipedInputStream pipedInputStream;

        public ReadFile(PipedInputStream pipedInputStream) {
            this.pipedInputStream = pipedInputStream;
        }

        @Override
        public void run() { try {
            byte[] bytes = new byte[100];
            int read;
            byte[] buffer=new byte[1];
            while (true) {
                int i=0;
                do{
                    this.pipedInputStream.read(buffer);
                    bytes[i]= buffer[0];
                    i++;
                }while (buffer[0]!=10);
                byte[] bytes1 = new byte[i];
                for(int j=0;j<=i-1;j++)
                {
                    bytes1[j]=bytes[j];
                }
                System.out.println(new String(bytes1));
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   static class WriteFile implements Runnable {
        PipedOutputStream pipedOutputStream;

        public WriteFile(PipedOutputStream pipedOutputStream) {
            this.pipedOutputStream = pipedOutputStream;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    this.pipedOutputStream.write(new String("the number is "+new Random().nextInt(100)+"\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        try {
            pipedOutputStream.connect(pipedInputStream);
            Thread thread = new Thread(new ReadFile(pipedInputStream));
            Thread thread2 = new Thread(new WriteFile(pipedOutputStream));
            thread.start();
            thread2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
