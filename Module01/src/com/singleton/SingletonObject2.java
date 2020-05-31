package com.singleton;

/**
 * @author LiXingyu
 * @date 2020-04-23 9:06 上午
 */
public class SingletonObject2 {
    /**
     * 可以做到延迟加载但是，多线程情况下存在线程安全问题，需要做synchronized同步
     */
    private static SingletonObject2 singletonObject;

    private SingletonObject2() {
    }

    public SingletonObject2 getSingletonObject() {
        return newInstance();
    }

    private SingletonObject2 newInstance() {
        if(singletonObject==null)
             singletonObject=new SingletonObject2();
        return singletonObject;
    }

    /**
     * synchronized同步，存在的问题是如果只是读取是也需要获取到锁效率低
     */
    private static SingletonObject2 newInstance_Synchronized() {
        synchronized (SingletonObject2.class){
            if(singletonObject==null)
                singletonObject=new SingletonObject2();
            return singletonObject;
        }

    }

    /**
     *采取double check方式，但可能导致空指针异常。
     * 假设thread_1已经执行到STEP_1但是由于new SingletonObject2()
     * 涉及到很多外部引用还未创建完成，
     * thread_2执行到first check时判断不为空并返回。由于singletonObject还未创建完成.
     * 根本原因是java的重排序及优化
     */
    private SingletonObject2 newInstance_Synchronized_DoubleCheck() {
        if(singletonObject==null){
            synchronized (SingletonObject2.class) {
                if (singletonObject == null)
                    STEP_1:  singletonObject = new SingletonObject2();
            }
        }
        return singletonObject;


    }
}
