package com.singleton;

/**
 * @author LiXingyu
 * @date 2020-04-23 8:37 上午
 */
public class SingletonObject {
    /**
     * 单例模式的饿汉式，类加载后在初始化阶段就会实例化对象，无法做到懒加载
     */
    private static final SingletonObject SINGLETON_OBJECT=new SingletonObject();
    private SingletonObject()
    {

    }
    public static SingletonObject getSingletonObject() {
        return SINGLETON_OBJECT;
    }
}
