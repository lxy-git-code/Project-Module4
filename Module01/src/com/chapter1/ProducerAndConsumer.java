package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-19 8:27 上午
 */
public class ProducerAndConsumer {
    static final Object lock =new Object();
    int i=0;
    volatile boolean isConsume=true;
    public void produce(){
        synchronized (lock)
        {
            if(isConsume){
                i++;
                System.out.println(Thread.currentThread().getName()+"produce"+i);
                isConsume=false;
                lock.notifyAll();
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void consume()
    {
        synchronized (lock)
        {
            if(!isConsume){
                System.out.println(Thread.currentThread().getName()+"consume"+i);
                isConsume=true;
                lock.notifyAll();
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    producerAndConsumer.produce();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    producerAndConsumer.produce();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    producerAndConsumer.consume();
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    producerAndConsumer.consume();
                }
            }
        });
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
