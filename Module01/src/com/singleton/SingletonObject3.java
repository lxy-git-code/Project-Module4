package com.singleton;

/**
 * @author LiXingyu
 * @date 2020-04-23 9:33 上午
 */
public class SingletonObject3 {
    /**
     * 使用volatile关键字，可以保证可见性和有序性
     */
    private static volatile SingletonObject3 singletonObject;

    private SingletonObject3 newInstance_Synchronized_DoubleCheck() {
        if (singletonObject == null) {
            synchronized (SingletonObject3.class) {
                if (singletonObject == null)
                    STEP_1:singletonObject = new SingletonObject3();
            }
        }
        return singletonObject;


    }
}
