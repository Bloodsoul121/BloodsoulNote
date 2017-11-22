package com.gionee.bloodsoulnote.customview3.wrapanimator;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.customview3.wrapanimator
 *  @文件名:   ViewWrapper
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/22 21:45
 *  @描述：    TODO
 */

import android.view.View;

public class ViewWrapper {

    private View targetView;

    public ViewWrapper(View view) {
        this.targetView = view;
    }

    public int getWidth() {
        return this.targetView.getWidth();
    }

    public void setWidth(int width) {
        this.targetView.getLayoutParams().width = width;
        this.targetView.requestLayout();
    }
}
