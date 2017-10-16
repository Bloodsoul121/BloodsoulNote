package com.gionee.bloodsoulnote.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gionee.bloodsoulnote.R;

/**
 * Created by cgz on 17-10-14.
 */

public class CustomView extends View {

    private Paint mPaint;
    private int mColor;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        mColor = typedArray.getColor(R.styleable.CustomView_custom_color, Color.RED);
        typedArray.recycle();

        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0, 0, 400, 400, mPaint);

//        int width = getWidth();
//        int height = getHeight();
//        canvas.drawRect(0, 0, width, height, mPaint);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        canvas.drawRect(0, 0, width, height, mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeWidth == MeasureSpec.AT_MOST && modeHeight == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, 400);
        } else if (modeWidth == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, sizeHeight);
        } else if (modeHeight == MeasureSpec.AT_MOST) {
            setMeasuredDimension(sizeWidth, 400);
        }
    }
}
