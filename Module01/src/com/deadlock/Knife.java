package com.deadlock;

/**
 * @author LiXingyu
 * @date 2020-04-19 7:56 上午
 */
public class Knife {

    Fork fork;

    public void setFork(Fork fork) {
        this.fork = fork;
    }

    public void getKnife()
    {
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+"获取到刀子");
            System.out.println(Thread.currentThread().getName()+"准备获取叉子......");
            fork.getFork();
        }
    }
}
