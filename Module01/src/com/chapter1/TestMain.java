package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-17 4:22 下午
 */
public class TestMain {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SychronizedStatic.m1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SychronizedStatic.m2();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                SychronizedStatic.m3();
            }
        });
        thread.start();
        thread2.start();
        thread3.start();
    }
}
