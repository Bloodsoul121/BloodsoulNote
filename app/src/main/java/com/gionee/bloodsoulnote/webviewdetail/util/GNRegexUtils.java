package com.gionee.bloodsoulnote.webviewdetail.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GNRegexUtils {

    /**
     * 是否是电话号码（仅判断国内手机号码）
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles == null) {
            return false;
        }
        Pattern p = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static String formatPhoneNum(String num) {
        try {
            String preNum = num.substring(0, 3);
            String afterNum = num.substring(7);
            return preNum + "****" + afterNum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

}
