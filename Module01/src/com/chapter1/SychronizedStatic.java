package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-17 4:09 下午
 */
public class SychronizedStatic {

    static{
        synchronized (SychronizedStatic.class){
            try {
                System.out.println(Thread.currentThread().getName()+"execute static block");
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1(){

        System.out.println("m1 "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2(){

        System.out.println("m2 "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3(){

        System.out.println("m3 "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
