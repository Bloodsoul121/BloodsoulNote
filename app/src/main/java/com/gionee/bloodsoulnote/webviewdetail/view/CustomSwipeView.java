package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.gionee.bloodsoulnote.R;

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
    /**
     * 记录滑动过程中的X坐标
     */
    private int mTempX;
    /**
     * 记录滑动过程中的Y坐标
     */
    private int mTempY;

    private boolean isSliding;

    private boolean isScrolling = false;

    private boolean isFinish = false;

    private int mViewWidth;

    private int mViewHeight;

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;

    private int mMinFlingVelocity;
    /**
     * content view
     */
    private View mContentView;
    /**
     * View左侧的阴影
     */
    private Drawable mShadowDrawable;
    /**
     * 半透明的阴影, 透明度跟随手指的滑动而改变
     */
    private int mBackgroundColor = 0xaa000000;
    /**
     * 比例值，用于计算alpha值
     */
    private float mRatio = 1.0f;

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
        // 初始化最小滑动距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMinFlingVelocity = 6 * ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        Log.d(TAG, "mMinFlingVelocity: " + mMinFlingVelocity);
        mScroller = new Scroller(context);
        mShadowDrawable = getResources().getDrawable(R.drawable.shadow);
        mContentView = this;
    }

    public void attachToParentView(View parent) {
        mContentView = parent;
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
        setBackgroundColor(mBackgroundColor);
    }

    /**
     * 给View的左侧加上阴影，阴影的宽度是 = (mViewWidth / 20)
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mShadowDrawable != null && mContentView != null) {
            int left = mContentView.getLeft()
                    - mViewWidth / 20;
            int right = left + mViewWidth / 20;
            int top = mContentView.getTop();
            int bottom = mContentView.getBottom();

            mShadowDrawable.setBounds(left, top, right, bottom);
            mShadowDrawable.draw(canvas);

            // 动态更新背景的alpha
            int alphaValue = (mBackgroundColor >> 24) & 0xFF;
            alphaValue *= mRatio;
            int backGroundColor = alphaValue << 24;
            setBackgroundColor(backGroundColor);
        }
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
                        // 计算Scroll的比例值
                        mRatio = 1- Math.abs(mContentView.getScrollX() * 1.0f / mViewWidth);
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
                    return;
                }
            }

            // 计算mRatio,用于设置alpha值
            if (mContentView.getScrollX() != 0) {
                // 横向滑动
                mRatio = 1 - Math.abs(mContentView.getScrollX() * 1.0f / mViewWidth);
            } else {
                // 纵向滑动
                mRatio = 1- Math.abs(mContentView.getScrollY() * 1.0f / mViewHeight);
            }
        }
    }

    public void show() {
        scrollToOriginX();
    }

    private void scrollToOriginX() {
        isFinish = false;
        isScrolling = true;
        final int delta = mContentView.getScrollX();
        final int duration = (int) (MAX_DURATION * (Math.abs(delta) * 1.0f / mViewWidth));
        Log.d(TAG, "duration: " + duration);
        mScroller.startScroll(mContentView.getScrollX(), 0, -delta, 0, duration);
        postInvalidate();
    }

    private void scrollToRight() {
        isFinish = true;
        isScrolling = true;
        final int delta = mViewWidth + mContentView.getScrollX();
        final int duration = (int) (MAX_DURATION * (Math.abs(delta) * 1.0f / mViewWidth));
        Log.d(TAG, "duration: " + duration);
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
