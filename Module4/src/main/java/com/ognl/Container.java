package com.ognl;

/**
 * @author LiXingyu
 * @date 2020-05-24 5:36 下午
 */
public class Container {

public volatile static  long a= 0;
    public long p9,p10,p11,p12,p13,p14,p15,p16;

    public volatile static  long b= 0;

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(()->{
            for (long i = 0; i < 10000_0000L; i++) {
                a = i;
            }
        });
        Thread t2 = new Thread(()->{
            for (long i = 0; i < 10000_0000L; i++) {
                b = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
