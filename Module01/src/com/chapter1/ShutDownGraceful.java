package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-21 10:19 上午
 */
public class ShutDownGraceful implements Runnable {

    @Override
    public void run() {
        System.out.println("processing...");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
