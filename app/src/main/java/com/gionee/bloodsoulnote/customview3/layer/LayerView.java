package com.gionee.bloodsoulnote.customview3.layer;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.customview3.layer
 *  @文件名:   LayerView
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/19 11:17
 *  @描述：    TODO
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class LayerView extends View {

    private Paint mPaint;

    public LayerView(Context context) {
        super(context);
        init(context);
    }

    public LayerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, mPaint);

//        canvas.save();
        canvas.saveLayerAlpha(0, 0, 200, 200, 127);

        mPaint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, mPaint);

        canvas.restore(); // 回到最初的 canvas
    }
}
