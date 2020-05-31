package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-20 9:02 上午
 */
public class TheDifferenceOfSleepAndWait {
    public static void main(String[] args) {
        Thread thread = new Thread("thread-1"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        thread.start();
    }
}
