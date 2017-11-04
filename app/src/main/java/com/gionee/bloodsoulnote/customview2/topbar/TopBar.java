package com.gionee.bloodsoulnote.customview2.topbar;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.customview2.topbar
 *  @文件名:   TopBar
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/4 11:34
 *  @描述：    TODO
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class TopBar extends RelativeLayout {

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;
    private TopbarClickListener mTopbarClickListener;
    private Button mLeftBtn;
    private Button mRightBtn;
    private TextView mTitleView;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray     = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);

        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = typedArray.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 0);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = typedArray.getString(R.styleable.TopBar_title);

        typedArray.recycle();

        initView(context);
    }

    private void initView(Context context) {
        mLeftBtn = new Button(context);
        mRightBtn = new Button(context);
        mTitleView = new TextView(context);

        mLeftBtn.setText(mLeftText);
        mLeftBtn.setTextColor(mLeftTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLeftBtn.setBackground(mLeftBackground);
        }

        mRightBtn.setText(mRightText);
        mRightBtn.setTextColor(mRightTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRightBtn.setBackground(mRightBackground);
        }

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                   ViewGroup.LayoutParams.MATCH_PARENT);
        leftParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftBtn, leftParams);

        LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                   ViewGroup.LayoutParams.MATCH_PARENT);
        rightParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightBtn, rightParams);

        LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                    ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(CENTER_IN_PARENT, TRUE);
        mTitleView.setPadding(10, 10, 10, 10);
        addView(mTitleView, titleParams);

        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListener != null) {
                    mTopbarClickListener.leftClick();
                }
            }
        });

        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListener != null) {
                    mTopbarClickListener.rightClick();
                }
            }
        });
    }

    interface TopbarClickListener{
        void leftClick();
        void rightClick();
    }

    public void setOnTopbarClickListener(TopbarClickListener topbarClickListener) {
        mTopbarClickListener = topbarClickListener;
    }

    /**
     *
     * @param id 左边button为0, 否则, 为右边button
     * @param flag 是否显示
     */
    public void setButtonVisible(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftBtn.setVisibility(VISIBLE);
            } else {
                mRightBtn.setVisibility(VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftBtn.setVisibility(GONE);
            } else {
                mRightBtn.setVisibility(GONE);
            }
        }
    }
}
