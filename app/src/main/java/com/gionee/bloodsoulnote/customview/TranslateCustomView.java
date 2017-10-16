package com.gionee.bloodsoulnote.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by cgz on 17-10-14.
 */

public class TranslateCustomView extends View{

    private int mFirstX;
    private int mFirstY;
    private Scroller mScroller;

    public TranslateCustomView(Context context) {
        super(context);
        init(context);
    }

    public TranslateCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstX = x;
                mFirstY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                int offsetX = x - mFirstX;
                int offsetY = y - mFirstY;

                // 第一种
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);

                // 第二种
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                // 第三种, 注意布局的位置, 不然无效
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);

                // 第四种
//                ((View)getParent()).scrollBy(-offsetX, -offsetY);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    // 配合 scroll 使用
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //1000秒内滑向destX
        mScroller.startScroll(scrollX,0,delta,0,2000);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
