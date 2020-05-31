package com.ognl;

import java.util.Map;

/**
 * @author LiXingyu
 * @date 2020-05-24 9:19 上午
 */

public class StatProcess{
    int simpleInternal;
    int traceIdDiff;
    String expectOperationName;
    String expectExternalDeviceList;

    public StatProcess(int simpleInternal, int traceIdDiff, String expectOperationName, String expectExternalDeviceList) {
        this.simpleInternal = simpleInternal;
        this.traceIdDiff = traceIdDiff;
        this.expectOperationName = expectOperationName;
        this.expectExternalDeviceList = expectExternalDeviceList;
    }

    public Map<Long,DelayItem> getResult() {
        return null;
    }
}