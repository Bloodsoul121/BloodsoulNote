package com.gionee.bloodsoulnote.customview2.img;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.customview2.img
 *  @文件名:   CustomImg
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/4 12:38
 *  @描述：    TODO
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomImg extends View {

    private int mLength;
    private RectF mRectF;
    private float mCircleXY;
    private float mRadius;
    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;
    private float mSweepAngle = 120;
    private String mShowText = "绘制文字";
    private int mTextSize = 40;

    public CustomImg(Context context) {
        super(context);
    }

    public CustomImg(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.GREEN);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);//取消锯齿
        mArcPaint.setStyle(Paint.Style.STROKE);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义

        mArcPaint.setColor(Color.BLUE);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLength = getMeasuredWidth();

        mCircleXY = mLength / 2;
        mRadius = (float) (mLength * 0.5 / 2);

        float left   = (float) (mLength * 0.1);
        float top    = (float) (mLength * 0.1);
        float right  = (float) (mLength * 0.9);
        float bottom = (float) (mLength * 0.9);
        mRectF = new RectF(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mArcPaint.setStrokeWidth(mLength / 8);

        // 画圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        // 画弧形
        canvas.drawArc(mRectF, 270, mSweepAngle, false, mArcPaint);
        // 绘制文字
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY
                + mTextSize / 4, mTextPaint);
    }

    public void setSweepAngle(float sweepAngle) {
        if (sweepAngle != 0) {
            mSweepAngle = sweepAngle;
        } else {
            mSweepAngle = 0;
        }
        this.invalidate();
    }
}
