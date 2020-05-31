package com.SimpleThreadsPool;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author LiXingyu
 * @date 2020-04-20 4:45 下午
 */
public class SimpleThreadPool {
    private final static int DEFAULT_SIZE = 10;
    private final int size;
    private static volatile int seq = 0;
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<Runnable>();
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private final static ThreadGroup THREAD_GROUP = new ThreadGroup("pool_group");
    private final static ArrayList<WorkTask> THREAD_QUEUE = new ArrayList<WorkTask>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.add(runnable);
            TASK_QUEUE.notifyAll();
        }

    }

    private void createWorkTask() {
        WorkTask workTask = new WorkTask(THREAD_GROUP, THREAD_PREFIX + (seq++));
        workTask.start();
        THREAD_QUEUE.add(workTask);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    private static class WorkTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                     runnable = TASK_QUEUE.removeFirst();

                }
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;

                }
            }

        }
    }

    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        
        for(int i=0;i<40;i++){
            simpleThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("the runnable be serviced by "+Thread.currentThread()+"start");
                    try {
                        Thread.sleep(1_0000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("the runnable be serviced by "+Thread.currentThread()+"end");
                }
            });
        }
    }
}
