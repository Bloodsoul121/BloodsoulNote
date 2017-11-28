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
        LinearGradient linearGradient = new LinearGradient(0, 0, 100, 0,
                new int[]{Color.BLUE, 0xffffff, Color.BLUE}, null,
                Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, 200, 200, mPaint);
    }
}
