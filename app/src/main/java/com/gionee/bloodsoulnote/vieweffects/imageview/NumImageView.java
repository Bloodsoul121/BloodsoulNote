package com.gionee.bloodsoulnote.vieweffects.imageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.gionee.bloodsoulnote.R;

public class NumImageView extends AppCompatImageView {

    //要显示的数量数量
    private int num = 0;
    //红色圆圈的半径
    private float radius;
    //圆圈内数字的半径
    private float textSize;
    //右边和上边内边距
    private int paddingRight;
    private int paddingTop;

    private Paint paint;

    public NumImageView(Context context) {
        super(context);
        init();
    }

    public NumImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    public void setNum(int num) {
        this.num = num;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (num > 0) {
            //初始化半径
            radius = getWidth() / 6;
            //初始化字体大小
            textSize = num < 10 ? radius + 5 : radius;
            //初始化边距
            paddingRight = getPaddingRight();
            paddingTop = getPaddingTop();
            //初始化画笔

            //设置抗锯齿
            paint.setAntiAlias(true);
            //设置颜色为红色
            paint.setColor(0xffff4444);
            //设置填充样式为充满
            paint.setStyle(Paint.Style.FILL);
            //画圆
            canvas.drawCircle(getWidth() - radius - paddingRight/2, radius + paddingTop/2, radius, paint);
            //设置颜色为白色
            paint.setColor(getResources().getColor(R.color.num_color));
            //设置字体大小
            paint.setTextSize(textSize);
            //画数字
            canvas.drawText("" + num,
                    num < 10 ? getWidth() - radius - textSize / 4 - paddingRight/2
                            : getWidth() - radius - textSize / 2 - paddingRight/2,
                    radius + textSize / 3 + paddingTop/2, paint);
        }
    }
}
