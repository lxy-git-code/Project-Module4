package com.chapter1;

/**
 * @author LiXingyu
 * @date 2020-04-21 10:33 上午
 */
public class ExecutorThread {
    private Thread executorThread;
    private boolean finish = false;
    public void execute(Runnable task)
    {
       executorThread=new Thread("task"){
           @Override
           public void run() {
               Thread runner = new Thread(task);
               runner.setDaemon(true);
               runner.start();
               try {
                   runner.join();
                   finish=true;
               } catch (InterruptedException e) {
                 //  e.printStackTrace();
               }
           }
       };
        executorThread.start();
    }

    public void shutDown(long millis)
    {
        long currentTime=System.currentTimeMillis();
        while(!finish){
            if(System.currentTimeMillis()-currentTime>=millis){
                System.out.println("time out");
                executorThread.interrupt();
                break;
            }
            try {
                executorThread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorThread executorThread = new ExecutorThread();
        long begin = System.currentTimeMillis();
        executorThread.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorThread.shutDown(10_000); //main thread call
        long end = System.currentTimeMillis();
        System.out.println(end - begin);

    }
}
