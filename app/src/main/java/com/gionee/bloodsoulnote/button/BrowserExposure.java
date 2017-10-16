package com.gionee.bloodsoulnote.button;

import android.util.Log;

/**
 * Created by cgz on 17-9-28.
 */

public class BrowserExposure {

    private BrowserExposure() {}

    // 曝光面积
    private float exposedArea = 1/2;

    // 停留时间
    private int dwellTime = 1000;

    // 单次去重, 单次下发上下滑动多次曝光去重
    private boolean disposeRepeat = true;

    // 两次曝光间隔小于10分钟去重
    private int validExposedTime = 10 * 60 * 1000;

    // 退出, 去重
    private boolean disposeRepeatWhenQuit = false;

    // 循环查询上报
    public void record() {
        Log.i("BrowserExposure", "record ---> " + disposeRepeat);
    }

    public static class Builder {

        private BrowserExposure exposure = new BrowserExposure();

        public Builder() {}

        public Builder setExposedArea(float exposedArea) {
            exposure.exposedArea = exposedArea;
            return this;
        }

        public Builder setDwellTime(int dwellTime) {
            exposure.dwellTime = dwellTime;
            return this;
        }

        public Builder setValidExposedTime(int validExposedTime) {
            exposure.validExposedTime = validExposedTime;
            return this;
        }

        public Builder setDisposeRepeatWhenQuit(boolean disposeRepeatWhenQuit) {
            exposure.disposeRepeatWhenQuit = disposeRepeatWhenQuit;
            return this;
        }

        public Builder setDisposeRepeat(boolean disposeRepeat) {
            exposure.disposeRepeat = disposeRepeat;
            return this;
        }

        public BrowserExposure build() {
            return exposure;
        }

    }

}
