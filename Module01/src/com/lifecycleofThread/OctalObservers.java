package com.lifecycleofThread;

/**
 * @author LiXingyu
 * @date 2020-04-29 4:00 下午
 */
public class OctalObservers extends Observers {


    public OctalObservers(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal string:" + Integer.toOctalString(subject.getState()));

    }
}
