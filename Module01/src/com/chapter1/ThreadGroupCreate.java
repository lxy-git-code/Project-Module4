package com.chapter1;

import java.util.Arrays;

/**
 * @author LiXingyu
 * @date 2020-04-20 3:12 下午
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {
                    try {
     /*                   System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent().activeCount());*/
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        ThreadGroup tg2 = new ThreadGroup("TG2");

        new Thread(tg2, "T2") {
            @Override
            public void run() {
                System.out.println(tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);

                }

            }.start();
        }

    }

