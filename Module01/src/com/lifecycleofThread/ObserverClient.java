package com.lifecycleofThread;

/**
 * @author LiXingyu
 * @date 2020-04-29 4:08 下午
 */
public class ObserverClient {
    public static void main(String[] args) {
        Subject subject = new Subject();
        BinaryObservers binaryObservers = new BinaryObservers(subject);
        OctalObservers octalObservers = new OctalObservers(subject);
        subject.setState(2);
    }
}
