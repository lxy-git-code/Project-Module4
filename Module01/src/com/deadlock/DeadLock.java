package com.deadlock;

/**
 * @author LiXingyu
 * @date 2020-04-19 7:42 上午
 */
public class DeadLock {


    public static void main(String[] args) throws InterruptedException {
        Fork fork=new Fork();
        Knife knife=new Knife();
        fork.setKnife(knife);
        knife.setFork(fork);
        Customer_1 customer_1 = new Customer_1();
        Customer_2 customer_2 = new Customer_2();
        customer_1.setKnife(knife);
        customer_2.setFork(fork);
        customer_1.start();
        customer_2.start();
        Thread.sleep(10000_0000);

        String a="asd";
        int b=1;
    }
}
