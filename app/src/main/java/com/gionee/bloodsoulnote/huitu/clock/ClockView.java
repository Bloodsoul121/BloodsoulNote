package com.gionee.bloodsoulnote.huitu.clock;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.huitu.clock
 *  @文件名:   ClockView
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/6 21:47
 *  @描述：    TODO
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ClockView extends View {

    private Paint mCirclePaint;
    private Paint mLinePain;
    private int mWidth;
    private int mHeight;
    private Paint mHourPaint;
    private Paint mMinutePaint;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setAntiAlias(true);

        mLinePain = new Paint();
        mLinePain.setStrokeWidth(3);

        mHourPaint = new Paint();
        mHourPaint.setStrokeWidth(20);

        mMinutePaint = new Paint();
        mMinutePaint.setStrokeWidth(10);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画圆
        canvas.drawCircle(mWidth / 2, mHeight /2, mWidth / 2, mCirclePaint);

        // 画刻度
        for (int i = 0; i < 24; i++) {
            // 区分整点和非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                mLinePain.setStrokeWidth(5);
                mLinePain.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2,
                                mHeight / 2 - mWidth / 2 + 60, mLinePain);
                String num = String.valueOf(i);
                canvas.drawText(num, mWidth / 2 - mLinePain.measureText(num) / 2,
                                mHeight / 2 - mWidth / 2 + 90, mLinePain);
            } else {
                mLinePain.setStrokeWidth(3);
                mLinePain.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2,
                                mHeight / 2 - mWidth / 2 + 30, mLinePain);
                String num = String.valueOf(i);
                canvas.drawText(num, mWidth / 2 - mLinePain.measureText(num) / 2,
                                mHeight / 2 - mWidth / 2 + 60, mLinePain);
            }

            // 旋转画布
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, mHourPaint);
        canvas.drawLine(0, 0, 100, 200, mMinutePaint);
        canvas.restore();
    }
}
