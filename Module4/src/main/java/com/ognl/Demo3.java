package com.ognl;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiXingyu
 * @date 2020-05-24 4:50 下午
 */
public class Demo3 {
    final static ReentrantLock lock = new ReentrantLock();
    final static Condition condition = Demo3.lock.newCondition();
    static volatile boolean ready = false;
    final static Random random = new Random();

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock.lock();
                        ready = random.nextInt(100) > 500 ? true : false;
                        if (ready)
                            condition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        await(2_000);
    }

    private static void await(long waitTime) {
        Date date = new Date(System.currentTimeMillis() + waitTime);
        boolean continueToWait = true;
        lock.lock();
        try {
            while (!ready) {
                System.out.println("is no ready");
                if (!continueToWait) {
                    System.out.println("time out!");
                    return;
                }
                try {
                    continueToWait = condition.awaitUntil(date);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println("do action.....");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish!");
    }

}
