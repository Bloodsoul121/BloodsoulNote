package com.gionee.bloodsoulnote.openfile;

import java.math.BigDecimal;

public class FileSizeUtil {

    public static final String MB = "MB";

    public static final String KB = "KB";

    public static final int NUM1024 = 1024;

    public static String bytes2kb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(NUM1024 * NUM1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1)
            return (returnValue + MB);
        BigDecimal kilobyte = new BigDecimal(NUM1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return (returnValue + "KB");
    }
}
