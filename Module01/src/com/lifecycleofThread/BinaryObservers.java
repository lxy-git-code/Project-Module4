package com.lifecycleofThread;

/**
 * @author LiXingyu
 * @date 2020-04-29 11:57 上午
 */
public class BinaryObservers extends Observers {


    public BinaryObservers(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary string:" + Integer.toBinaryString(subject.getState()));
    }
}
