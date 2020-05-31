package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-17 10:50 上午
 */
public class GracefulShutdown {

    public static class Worker extends Thread{
        volatile boolean trigger=true;

        @Override
        public void run() {
            while (trigger){

            }
        }

        public void shutDown(){
            trigger=false;
        }
    }


    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutDown();
    }
}
