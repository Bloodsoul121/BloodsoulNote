package com.gionee.bloodsoulnote;

public class BloodsoulTest extends ApplicationTest {

    public static void main(String[] args) {
        String formatNum = formatNum(12563);
        System.out.println(formatNum);
    }

    public static String formatNum(int num) {
        if (num < 0) {
            return String.valueOf(num);
        } else if (num < 10000) {
            return String.valueOf(num);
        } else {
            float formatNum = num / 10000 * 1.0f;
            return formatNum + "万";
        }
    }

}
