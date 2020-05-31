package com.volatiletest;

/**
 * @author LiXingyu
 * @date 2020-04-24 4:29 上午
 * thread1 和 thread2的执行结果说明，每个线程还是会从主内存中去重新读取的。
 * 但是VolatileTest1为什么，一直是死循环呢？因为thread对于INIT_VALUE都
 * 是读操作java会自作主张的优化直接从cache中读而不会从主内存中读取，而
 * VolatileTest2会每个Thread都有写操作，都会将值从cache中刷新回main memory
 * 
 *
 *
 */
public class VolatileTest2 {
    private volatile static int INIT_VALUE = 0;
    private static final int MAX_LIMIT = 20;

    public static void main(String[] args) {

        new Thread("thread_1"){
            @Override
            public void run() {
                while(INIT_VALUE<MAX_LIMIT)
                {
                    System.out.println(Thread.currentThread().getName()+":"+(++INIT_VALUE));
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread("thread_2"){
            @Override
            public void run() {
                while(INIT_VALUE<MAX_LIMIT)
                {
                    System.out.println(Thread.currentThread().getName()+":"+(++INIT_VALUE));
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
