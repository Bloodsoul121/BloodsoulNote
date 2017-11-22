package com.gionee.bloodsoulnote.customview3.customanimator;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.customview3.customanimator
 *  @文件名:   CustomAnimator
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/22 22:18
 *  @描述：    TODO
 */

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CustomAnimator extends Animation {

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    /**
     *
     * @param interpolatedTime 时间因子
     * @param t 矩阵的包装类
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        // // TODO: 2017/11/22
        Matrix matrix = t.getMatrix();
        matrix.preScale(1, 1 - interpolatedTime, 0, 0);
    }
}
