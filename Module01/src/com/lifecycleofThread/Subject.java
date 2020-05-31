package com.lifecycleofThread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXingyu
 * @date 2020-04-29 11:16 上午
 */
public class Subject {
    private List<Observers> observers = new ArrayList<Observers>();

    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if(state!=this.state){
            this.state=state;
            notityAllObserve();
        }
    }

    protected boolean registerObserver(Observers Observer)
    {
        return observers.add(Observer);
    }

    private void notityAllObserve() {
        observers.stream().forEach(Observers::update);
    }
}
