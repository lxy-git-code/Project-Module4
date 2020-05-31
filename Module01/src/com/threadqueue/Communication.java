package com.threadqueue;

/**
 * @author LiXingyu
 * @date 2020-04-04 2:50 下午
 */
public class Communication {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            Thread thread = new Thread(new ReadData());
            thread.start();
        }

    }
}