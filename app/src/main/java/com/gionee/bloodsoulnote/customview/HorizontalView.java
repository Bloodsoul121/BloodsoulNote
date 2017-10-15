package com.gionee.bloodsoulnote.customview;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.customview
 *  @文件名:   HorizontalView
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/10/15 11:05
 *  @描述：    TODO
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalView extends ViewGroup {

    private int mLastInterceptX;
    private int mLastInterceptY;
    private int mLastX;
    private int mLastY;
    private int mCurrentIndex = 0;
    private int mChildWidth = 0;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public HorizontalView(Context context) {
        super(context);
        init(context);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        // 检测滑动速度
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        View child;
        int left = 0;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childWidth = child.getMeasuredWidth();
                mChildWidth = childWidth;
                child.layout(left, 0, left + childWidth, child.getMeasuredHeight());
                left += childWidth;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth      = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // 如果没有子元素，就设置宽高为0（简化处理）
        if (getChildCount() == 0) {
            setMeasuredDimension(0,0);
        }
        //宽和高都是AT_MOST，则设置宽度所有子元素的宽度的和；高度设置为第一个元素的高度；
        else if (modeWidth == MeasureSpec.AT_MOST && modeHeight == MeasureSpec.AT_MOST) {
            View child = getChildAt(0);
            int  childMeasuredWidth = child.getMeasuredWidth();
            int  childMeasuredHeight = child.getMeasuredHeight();
            setMeasuredDimension(childMeasuredWidth * getChildCount(), childMeasuredHeight);
        } else if (modeWidth == MeasureSpec.AT_MOST) {
            int childWidth = getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(childWidth * getChildCount(), sizeHeight);
        } else if (modeHeight == MeasureSpec.AT_MOST) {
            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(sizeWidth, childHeight);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int   x         = (int) ev.getX();
        int   y         = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;

                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastInterceptX;
                int offsetY = y - mLastInterceptY;
                // 水平滑动，拦截掉
                if (Math.abs(offsetX) - Math.abs(offsetY) > 0) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }

        mLastX = x;
        mLastY = y;
        mLastInterceptX = x;
        mLastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                scrollBy(-offsetX, 0);
                break;
            case MotionEvent.ACTION_UP:
                // 相对当前 view 滑动的距离，正为向左，负为向右
                int distance = getScrollX() - mCurrentIndex * mChildWidth;
                // 滑动的距离要大于 1/2 个宽度，否则不会切换到其他页面
                if (Math.abs(distance) > mChildWidth / 2) {
                    if (distance > 0) {
                        mCurrentIndex++;
                    }else {
                        mCurrentIndex--;
                    }
                } else {
                    // 调用该方法计算 1000 ms 内滑动的平均速度
                    mVelocityTracker.computeCurrentVelocity(1000);
                    // 获取水平方向上的速度
                    float xVelocity = mVelocityTracker.getXVelocity();
                    if (Math.abs(xVelocity) > 50) {
                        if (xVelocity > 0) {
                            mCurrentIndex--;
                        } else {
                            mCurrentIndex++;
                        }
                    }
                }

                mCurrentIndex = mCurrentIndex < 0 ? 0 : mCurrentIndex > getChildCount() - 1 ?
                                                        getChildCount() - 1 : mCurrentIndex;

                smoothScrollTo(mCurrentIndex * mChildWidth, 0);
                // 重置速度计算器
                mVelocityTracker.clear();
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void smoothScrollTo(int destX, int destY) {
        mScroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY(), 1000);
        invalidate();
    }
}
