package com.gionee.bloodsoulnote.customview2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cgz on 17-11-28.
 */

public class GradientView extends View {

    private Paint mPaint;

    private LinearGradient mLinearGradientNormal;

    public GradientView(Context context) {
        super(context);
        init(context);
    }

    public GradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();

        mPaint.setColor(Color.parseColor("#FFFF00"));



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRect(0, 0, 200, 200, mPaint);

        mLinearGradientNormal = new LinearGradient(80, 80, 320, 320,
                new int[]{Color.parseColor("#2600baff"), Color.parseColor("#2616d5ff")}, null,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradientNormal);


        mPaint.setColor(Color.parseColor("#2616d5ff"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        canvas.drawCircle(200, 200, 122, mPaint);


        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.parseColor("#2600baff"));  // 这个坑

        mPaint.setColor(Color.WHITE);

        canvas.drawCircle(200, 200, 120, mPaint);
    }
}
