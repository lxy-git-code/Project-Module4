package com.chapter1;

import java.util.concurrent.CountDownLatch;

/**
 * @author LiXingyu
 * @date 2020-04-17 9:51 上午
 */
public class TestJoin {
   static CountDownLatch countDownLatch = new CountDownLatch(1);
   static int data;
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for(;;){
                    data++;
                    countDownLatch.countDown();

                }
            }

        });
        thread.start();
        countDownLatch.await();
        System.out.println(data);

    }
}
