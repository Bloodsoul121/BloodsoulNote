package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class CustomSwipeView extends RelativeLayout {

    public static final String TAG = "SwipeView";

    public static final int ORIENTATION_NONE = 0;

    public static final int ORIENTATION_HORIZONTAL = 1;

    public static final int ORIENTATION_VERTICAL = 2;

    private int mScrollOrientation = ORIENTATION_NONE;

    public static final int MAX_DURATION = 800;

    private int mTouchSlop;

    private int mDownX;

    private int mDownY;

    private int mTempX;

    private int mTempY;

    private boolean isSliding;

    private boolean isScrolling = false;

    private boolean isFinish = false;

    private int mViewWidth;

    private int mViewHeight;

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;

    private int mMinFlingVelocity;

    private View mContentView;

    private OnSwipeFinishListener mOnSwipeFinishListener;

    public CustomSwipeView(Context context) {
        this(context, null);
    }

    public CustomSwipeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMinFlingVelocity = 6 * ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        Log.d(TAG, "mMinFlingVelocity: " + mMinFlingVelocity);
        mScroller = new Scroller(context);
        mContentView = this;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTempX = mDownX = (int) ev.getRawX();
                mTempY = mDownY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getRawX();
                int moveY = (int) ev.getRawY();
                // 横向滑动：满足此条件就对事件进行拦截
                if (moveX - mDownX > mTouchSlop && Math.abs((int)ev.getRawY() - mDownY) < mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                startVelocityTracker(event);
                int moveY = (int) event.getRawY();
                int deltaY = mTempY - moveY;
                mTempY = moveY;
                int moveX = (int) event.getRawX();
                int deltaX = mTempX - moveX;
                mTempX = moveX;
                // 横向滑动
                if (moveX - mDownX > mTouchSlop
                        && Math.abs((int)event.getRawY() - mDownY) < mTouchSlop
                        && mScrollOrientation != ORIENTATION_VERTICAL) {
                    isSliding = true;
                    mScrollOrientation = ORIENTATION_HORIZONTAL;
                }

                if (isSliding) {
                    if (mScrollOrientation == ORIENTATION_HORIZONTAL) {
                        mContentView.scrollBy(deltaX, 0);
                        // 边界控制
                        if (mContentView.getScrollX() > 0) {
                            mContentView.scrollTo(0, 0);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isSliding = false;
                if (!isScrolling) {
                    if (mScrollOrientation == ORIENTATION_HORIZONTAL) {
                        int scrollVelocityX = getScrollVelocityX();
                        Log.d(TAG, "scrollVelocityX: " + scrollVelocityX);
                        if (Math.abs(scrollVelocityX) <= mMinFlingVelocity) {
                            if (mContentView.getScrollX() < -mViewWidth / 2) {
                                scrollToRight();
                            } else {
                                scrollToOriginX();
                            }
                        } else if (scrollVelocityX > mMinFlingVelocity) {
                            scrollToRight();
                        } else if (scrollVelocityX < -mMinFlingVelocity) {
                            scrollToOriginX();
                        }
                    }
                }
                mScrollOrientation = ORIENTATION_NONE;
                break;
            case MotionEvent.ACTION_CANCEL:
                stopVelocityTracker();
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mContentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();

            if (mScroller.isFinished()) {
                isScrolling = false;
                if (isFinish) {
                    if (mOnSwipeFinishListener != null) {
                        mOnSwipeFinishListener.onSwipeFinish();
                    }
                }
            }
        }
    }

    public void show() {
        scrollToOriginX();
    }

    public void hide() {
        scrollToRight();
    }

    private void scrollToOriginX() {
        isFinish = false;
        isScrolling = true;
        final int delta = mContentView.getScrollX();
        final int duration = (int) (MAX_DURATION * (Math.abs(delta) * 1.0f / mViewWidth));
        Log.d(TAG, "duration: " + duration + ", " + delta + ", " + mContentView.getScrollX());
        mScroller.startScroll(mContentView.getScrollX(), 0, -delta, 0, duration);
        postInvalidate();
    }

    private void scrollToRight() {
        isFinish = true;
        isScrolling = true;
        final int delta = mViewWidth + mContentView.getScrollX();
        final int duration = (int) (MAX_DURATION * (Math.abs(delta) * 1.0f / mViewWidth));
        Log.d(TAG, "duration: " + duration + ", " + delta + ", " + mContentView.getScrollX());
        mScroller.startScroll(mContentView.getScrollX(), 0, -delta, 0, duration);
        postInvalidate();
    }

    private void startVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void stopVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private int getScrollVelocityX() {
        if (mVelocityTracker != null) {
            // 设置单位, 1000表示1s内移动的像素值
            mVelocityTracker.computeCurrentVelocity(1000);
            int velocityX = (int) mVelocityTracker.getXVelocity();
            return velocityX;
        } else {
            return 0;
        }
    }

    public interface OnSwipeFinishListener{
        void onSwipeFinish();
    }

    public void setOnSwipeFinishListener(OnSwipeFinishListener onSwipeFinishListener) {
        this.mOnSwipeFinishListener = onSwipeFinishListener;
    }

}
