package com.threadqueue;

/**
 * @author LiXingyu
 * @date 2020-04-04 3:20 下午
 */
public class ReadData implements Runnable{

    @Override
    public void run() {
        synchronized (ThreadContainer.threadList){
            while(ThreadContainer.checkIsFull()){
                System.out.println("线程已满10个"+Thread.currentThread().getName()+"线程等待执行");
                try {
                    ThreadContainer.threadList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ThreadContainer.threadList.add(this);
        }


        System.out.println("线程未满10个"+Thread.currentThread().getName()+"线程加入");
        System.out.println(Thread.currentThread().getName()+": read data .....");
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+": finish");
        System.out.println(Thread.currentThread().getName()+"推出线程");
        synchronized ( ThreadContainer.threadList){
            ThreadContainer.threadList.remove(this);
            ThreadContainer.threadList.notifyAll();

        }


    }
}
