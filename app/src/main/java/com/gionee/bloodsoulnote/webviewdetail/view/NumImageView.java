package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.gionee.bloodsoulnote.R;

public class NumImageView extends AppCompatImageView {

    //要显示的数量数量
    private String num = "0";
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
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.num_color));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void setNum(int num) {
        this.num = String.valueOf(num);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化半径
        radius = getWidth() / 6;
        //初始化字体大小
        textSize = radius;
        //初始化边距
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();

        paint.setTextSize(textSize);

        //画数字
        canvas.drawText(num, getMeasuredWidth() - radius - num.length() - paddingRight/2,
                radius + num.length() + paddingTop/2, paint);
    }
}
