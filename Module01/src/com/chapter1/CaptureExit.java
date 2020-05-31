package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-20 2:42 下午
 */
public class CaptureExit
{
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("the application will exit");
            }
        }));

        int i=0;
        while (true){
            try {
                Thread.sleep(1_000);
                System.out.println("I'm working...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if(i>20)
            {
                throw new RuntimeException("error");
            }
        }
    }
}
