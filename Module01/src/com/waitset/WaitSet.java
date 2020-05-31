package com.waitset;

/**
 * @author LiXingyu
 * @date 2020-04-23 9:46 上午
 *
 * 1.所有的对象都会有一个wait set来存放调用该对象wait()方法后进入block状态的线程
 * 2.线程被notify之后，不一定能够立即执行，
 * 3.线程从wait set中被唤醒程序不一定是FIFO
 * 4.被唤醒的线程，如果能够获得锁就会继续从wait()方法后面继续执行
 */
public class WaitSet {
    static final Object lock = new Object();

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread("thread" + i) {
                @Override
                public void run() {
                    synchronized (lock) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " start to entry wait set");
                            lock.wait();
                            System.out.println(Thread.currentThread().getName() + " leave from wait set ");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        try {

            Thread.sleep(1000);
                synchronized (lock) {
                    lock.notify();
                }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
