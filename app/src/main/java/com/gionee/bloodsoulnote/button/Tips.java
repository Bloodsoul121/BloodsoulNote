package com.gionee.bloodsoulnote.button;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cgz on 17-12-6.
 */

public class Tips {

    @SerializedName("1")
    private String tip1;
    @SerializedName("2")
    private String tip2;
    @SerializedName("3")
    private String tip3;

    public String getTip1() {
        return tip1;
    }

    public void setTip1(String tip1) {
        this.tip1 = tip1;
    }

    public String getTip2() {
        return tip2;
    }

    public void setTip2(String tip2) {
        this.tip2 = tip2;
    }

    public String getTip3() {
        return tip3;
    }

    public void setTip3(String tip3) {
        this.tip3 = tip3;
    }

    @Override
    public String toString() {
        return "Tips{" +
                "tip1='" + tip1 + '\'' +
                ", tip2='" + tip2 + '\'' +
                ", tip3='" + tip3 + '\'' +
                '}';
    }
}
