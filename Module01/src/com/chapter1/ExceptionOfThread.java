package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-20 2:53 下午
 */
public class ExceptionOfThread {
    public final static int a=10;
    public final static int b=0;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5_000);
                    int result=a/b;
                    System.out.println(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println(e);
                        System.out.println(t);
                    }
                });
        thread.start();
    }
}