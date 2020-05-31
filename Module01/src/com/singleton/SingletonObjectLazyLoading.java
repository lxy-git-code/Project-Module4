package com.singleton;

/**
 * @author LiXingyu
 * @date 2020-04-23 8:44 上午
 */
public class SingletonObjectLazyLoading {

    private static class innerclass {
        private static final SingletonObjectLazyLoading SingletonObject = new SingletonObjectLazyLoading();
    }

    public static SingletonObjectLazyLoading getSingletonObject() {
        return innerclass.SingletonObject;
    }
    public static void main(String[] args) {


   /*     for (int i = 0; i < 10; i++) {
            new Thread("Thread" + i) {
                @Override
                public void run() {
                    System.out.println(SingletonObjectLazyLoading.getSingletonObject());
                }
            }.start();
        }*/
    }
}

