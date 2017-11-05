package com.gionee.bloodsoulnote.pxdp;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.pxdp
 *  @文件名:   PxUtil
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/5 13:00
 *  @描述：    TODO
 */

import android.content.Context;

public class PxUtil {

    public static int px2dip(Context context, float px) {
        float density = context.getResources()
                               .getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    public static int dip2px(Context context, float dip) {
        float density = context.getResources()
                               .getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public static int px2sp(Context context, float px) {
        float scaledDensity = context.getResources()
                                     .getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity + 0.5f);
    }

    public static int sp2px(Context context, float sp) {
        float scaledDensity = context.getResources()
                                     .getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity + 0.5f);
    }

}
