package com.deadlock;

/**
 * @author LiXingyu
 * @date 2020-04-19 8:00 上午
 */
public class Fork {
Knife knife;

    public void setKnife(Knife knife) {
        this.knife = knife;
    }

    public void getFork()
    {
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+"获取到叉子");
            System.out.println(Thread.currentThread().getName()+"准备获取刀子......");
            knife.getKnife();
        }
    }
}
