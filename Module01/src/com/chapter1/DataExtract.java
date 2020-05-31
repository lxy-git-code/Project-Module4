package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-20 9:13 上午
 */
public class DataExtract{
    private volatile boolean isFull=false;
    public static final Object lock=new Object();
    private boolean checkIsFull()
    {
            if(ThreadList.threadList.size()>9)
                isFull=true;
            else
                isFull=false;
            return isFull;
    }

    public void dataExtract() {
        synchronized (lock){
            while(checkIsFull()){
                System.out.println("ThreadList is full:"+Thread.currentThread().getName()+" wait...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("ThreadList is not full:"+Thread.currentThread().getName()+" add to ThreadList");
            ThreadList.threadList.add(Thread.currentThread());

        }

        System.out.println(Thread.currentThread().getName()+" start to extract data... ");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" extract data end");

        synchronized (lock){
            ThreadList.threadList.remove(Thread.currentThread());
            System.out.println(Thread.currentThread().getName()+" remove from ThreadList");
            lock.notifyAll();
        }

    }

    public static void main(String[] args) {
        DataExtract dataExtract = new DataExtract();

        for (int i=0;i<20;i++)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dataExtract.dataExtract();
                }
            }).start();
        }
    }
}
