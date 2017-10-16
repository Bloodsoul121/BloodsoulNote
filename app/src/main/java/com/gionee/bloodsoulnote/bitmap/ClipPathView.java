package com.gionee.bloodsoulnote.bitmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cgz on 17-9-4.
 */

public class ClipPathView extends View {

    private Paint mPaint;

    public ClipPathView(Context context) {
        super(context);
        init();
    }

    public ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //DIFFERENCE是第一次不同于第二次的部分显示出来A-B-------
        //REPLACE是显示第二次的B******
        //REVERSE_DIFFERENCE 是第二次不同于第一次的部分显示--------
        //INTERSECT交集显示A-(A-B)******* (默认)
        //UNION全部显示A+B******
        //XOR补集 就是全集的减去交集生育部分显示--------

        canvas.save();
        canvas.translate(10,10);
        canvas.clipRect(0,0,300,300);
        canvas.clipRect(200,200,400,400, Region.Op.XOR);
        canvas.clipRect(0,0,400,400);  // 与之前的交集，默认
        canvas.drawColor(Color.BLUE);
        canvas.restore();

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(10,10);
        canvas.drawRect(0,0,300,300,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(200,200,400,400,mPaint);
        invalidate();
    }
}
