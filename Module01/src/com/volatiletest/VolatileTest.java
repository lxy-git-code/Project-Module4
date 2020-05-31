package com.volatiletest;

/**
 * @author LiXingyu
 * @date 2020-04-23 10:58 上午
 */
public class VolatileTest {
    /**
     * INIT_VALUE 不加volatile 会导致thread1无法感知到其变化一直死循环在HOLD处
     */
    private  static int INIT_VALUE = 0;
    private static final int MAX_LIMIT = 5;

    public static void main(String[] args) {
        Thread thread = new Thread("read_data_thread") {

            @Override
            public void run() {
                int localValue = INIT_VALUE;
                while (localValue < MAX_LIMIT) {
                   HOLD: if (localValue != INIT_VALUE) {
                        System.out.println(Thread.currentThread().getName() + "update to " + (INIT_VALUE));
                        localValue = INIT_VALUE;
                    }
                }
            }
        };
        Thread thread1 = new Thread("update_thread") {

            @Override
            public void run() {
                int localvalue = INIT_VALUE;
                while (INIT_VALUE < MAX_LIMIT) {
                    System.out.println(Thread.currentThread().getName() + " will update the value to " + ++localvalue);
                    INIT_VALUE = localvalue;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        thread1.start();
    }

    ;

}

