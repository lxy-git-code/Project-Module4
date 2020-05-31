package com.ognl;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.SimpleTimeZone;

/**
 * @author LiXingyu
 * @date 2020-05-24 8:44 上午
 */
public abstract class StatTask implements Runnable {
    private final SimpleDateFormat sdf;
    private final Calendar calendar;
    private final int simpleInternal;
    private static final String TIME_STAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    private final StatProcess statProcess;


    public StatTask(int simpleInternal, int traceIdDiff, String expectOperationName, String expectExternalDeviceList) {
        this(simpleInternal, new StatProcess(simpleInternal, traceIdDiff, expectOperationName, expectExternalDeviceList));
    }

    public StatTask(int simpleInternal, StatProcess statProcess) {
        SimpleTimeZone timeZone = new SimpleTimeZone(0, "UTC");
        this.sdf = new SimpleDateFormat(TIME_STAMP);
        this.calendar = Calendar.getInstance(timeZone);
        this.simpleInternal = simpleInternal;
        this.statProcess = statProcess;
    }

    abstract void doCalculate() throws IOException, InterruptedException;

    @Override
    public void run() {
        try {
            doCalculate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<Long, DelayItem> result = statProcess.getResult();
        report(result);
    }

    void report(Map<Long, DelayItem> result) {
        int simpleCount;
        final PrintStream out = System.out;
        for (DelayItem delaysDate :
                result.values()) {
            System.out.println();

        }
    }
}
