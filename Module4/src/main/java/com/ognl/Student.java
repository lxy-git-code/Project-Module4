package com.ognl;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LiXingyu
 * @date 2020-05-09 3:31 下午
 */
public class Student implements Runnable {
AtomicReference<Integer> a=new AtomicReference<Integer>();
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new HashMap().put()
        Student student = new Student();
        Thread[] threads=new Thread[1000];
        for (int i=0;i<threads.length;i++){
            threads[i]=new Thread(student);
        }
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
    }
}
