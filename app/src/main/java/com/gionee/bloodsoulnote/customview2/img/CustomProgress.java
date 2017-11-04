package com.gionee.bloodsoulnote.customview2.img;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.customview2.img
 *  @文件名:   CustomProgress
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/4 13:55
 *  @描述：    TODO
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomProgress extends View{

    private Paint mPaint;

    /**
     * 圆的宽度
     */
    private int mCircleWidth = 3;

    public CustomProgress(Context context) {
        this(context, null);
    }

    public CustomProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setColor(Color.CYAN);

        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;

        RectF oval = new RectF(x, y, getWidth() - x, getHeight() - y);

        canvas.drawArc(oval,360,140,true,mPaint);
    }
}
