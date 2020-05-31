package com.deadlock;

/**
 * @author LiXingyu
 * @date 2020-04-19 7:45 上午
 */
public class Customer_1 extends Thread {
     Knife knife;

    public void setKnife(Knife knife) {
        this.knife = knife;
    }

    @Override
    public void run() {
        knife.getKnife();
    }
}
