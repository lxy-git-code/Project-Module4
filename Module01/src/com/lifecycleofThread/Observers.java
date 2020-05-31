package com.lifecycleofThread;

/**
 * @author LiXingyu
 * @date 2020-04-29 11:22 上午
 */
public abstract class Observers {
    protected Subject subject;

    public abstract void update();

    public Observers(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }
}
