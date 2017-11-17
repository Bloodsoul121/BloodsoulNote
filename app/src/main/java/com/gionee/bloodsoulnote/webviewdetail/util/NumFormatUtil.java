package com.gionee.bloodsoulnote.webviewdetail.util;

import java.text.DecimalFormat;

public class NumFormatUtil {

    public static String formatNum(String num) {
        int formatNum = Integer.valueOf(num);
        if (formatNum < 0) {
            return String.valueOf(num);
        } else if (formatNum < 10000) {
            return String.valueOf(num);
        } else {
            return formatNumToWan(formatNum) + "万";
        }
    }

    private static String formatNumToWan(double num) {
        DecimalFormat df = new DecimalFormat();
        String style = "0.0";
        df.applyPattern(style);
        return df.format(num);
    }

}
