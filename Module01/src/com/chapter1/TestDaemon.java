package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-17 9:33 上午
 */
public class TestDaemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "begin....");
                    Thread.sleep(1_0000);
                    System.out.println(Thread.currentThread().getName() + "end");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //thread.setDaemon(true);
        thread.start();
        System.out.println(Thread.currentThread().getName());
    }
}
