package com.deadlock;

/**
 * @author LiXingyu
 * @date 2020-04-19 7:49 上午
 */
public class Customer_2 extends Thread {
    Fork fork;

    public void setFork(Fork fork) {
        this.fork = fork;
    }

    @Override
    public void run() {
    fork.getFork();
    }
}
