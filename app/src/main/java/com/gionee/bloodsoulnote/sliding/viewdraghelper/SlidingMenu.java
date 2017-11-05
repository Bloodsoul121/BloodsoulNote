package com.gionee.bloodsoulnote.sliding.viewdraghelper;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.sliding.viewdraghelper
 *  @文件名:   SlidingMenu
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/5 11:59
 *  @描述：    TODO
 */

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class SlidingMenu extends FrameLayout {

    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;
    private ViewDragHelper mViewDragHelper;

    public SlidingMenu(Context context) {
        super(context);
        initView();
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mContentView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
        }
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        // 何时开始检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mContentView == child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return super.clampViewPositionVertical(child, top, dy);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mContentView.getLeft() < mMenuWidth / 2) {
                // 关闭菜单
                mViewDragHelper.smoothSlideViewTo(mContentView, 0 , 0);
                ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
            } else {
                mViewDragHelper.smoothSlideViewTo(mContentView, mMenuWidth, 0);
                ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
            }
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

    };
}
