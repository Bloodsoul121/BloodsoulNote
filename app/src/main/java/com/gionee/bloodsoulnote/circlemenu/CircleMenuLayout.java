package com.gionee.bloodsoulnote.circlemenu;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.circlemenu
 *  @文件名:   CircleMenuLayout
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/10/11 21:38
 *  @描述：    TODO
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class CircleMenuLayout extends ViewGroup {

    // 圆形直径
    private int mRadius;
    // 该容器内 child item 的默认尺寸
    private static final float radio_default_child_dimension = 1 /4f;
    // 该容器的内边距，无视 padding 属性，如需边距请使用该变量
    private static final float radio_default_padding_layout = 1 / 12f;
    // 该容器的内边距，无视 padding 属性，如需边距请使用该变量
    private float mPadding;
    // 布局时的开始角度
    private double mStartAngle = 0;
    // 菜单项的文本
    private String[] mItemTexts;
    // 菜单项的图标
    private int[] mItemImgs;
    // 菜单的个数
    private int mMenuItemCount;
    // 菜单布局资源 id
    private int mMenuItemLayoutId = R.layout.circle_menu_item;
    // MenuItem 的点击事件
    private OnItemClickListener mOnItemClickListener;

    public CircleMenuLayout(Context context) {
        super(context);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void setMenuItemIconsAndTexts(int[] images, String[] texts) {
        if (images == null && texts == null) {
            throw new IllegalArgumentException("菜单项文本和图片至少设置其一");
        }

        mItemImgs = images;
        mItemTexts = texts;
        // 初始化 mMenuItemCount
        mMenuItemCount = images == null ? texts.length : images.length;
        if (images != null && texts != null) {
            mMenuItemCount = Math.min(images.length, texts.length);
        }
        // 构建菜单项
        buildMenuItems();
    }

    private void buildMenuItems() {
        // 根据用户设置的参数，初始化 menu item
        for (int i = 0; i < mMenuItemCount; i++) {
            View itemView = inflateMenuView(i);
            // 初始化菜单项
            initMenuItem(itemView, i);
            // 添加 view 到容器中
            addView(itemView);
        }
    }

    private void initMenuItem(View itemView, int childIndex) {
        ImageView iv = (ImageView) itemView.findViewById(R.id.id_circle_menu_item_image);
        TextView tv = (TextView) itemView.findViewById(R.id.id_circle_menu_item_text);
        iv.setVisibility(VISIBLE);
        iv.setImageResource(mItemImgs[childIndex]);
        tv.setVisibility(VISIBLE);
        tv.setText(mItemTexts[childIndex]);
    }

    private View inflateMenuView(final int childIndex) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View           itemView  = inflater.inflate(mMenuItemLayoutId, this, false);
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v, childIndex);
                }
            }
        });
        return itemView;
    }

    public void setMenuItemLayoutId(int mMenuItemLayoutId) {
        this.mMenuItemLayoutId = mMenuItemLayoutId;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 设置布局的宽高，并测量 menu item 宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 丈量自身尺寸
        measureMyself(widthMeasureSpec, heightMeasureSpec);
        // 丈量菜单项尺寸
        measureChildViews();
    }

    private void measureMyself(int widthMeasureSpec, int heightMeasureSpec) {
        int resWidth = 0;
        int resHeight = 0;
        // 根据传入的参数，分别获取测量值和测量模式
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 如果宽或高的测量模式非精确值
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            // 主要设置为背景图的高度
            resWidth = getSuggestedMinimumWidth();
            // 如果未设置背景图片，则设置为屏幕宽高的默认值
            resWidth = resWidth == 0 ? getDefaultWith() : resWidth;
            resHeight = getSuggestedMinimumHeight();
            resHeight = resHeight == 0 ? getDefaultHeight() : resHeight;
        } else {
            resWidth = resHeight = Math.min(width, height);
        }
        setMeasuredDimension(resWidth, resHeight);
    }

    private int getDefaultHeight() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    private int getDefaultWith() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    private void measureChildViews() {
        // 获得半径
        mRadius = Math.max(getMeasuredWidth(),getMeasuredHeight());
        // menu item 数量
        final int count = getChildCount();
        // menu item 尺寸
        int childSize = (int) (mRadius * radio_default_child_dimension);
        // menu item 测量模式
        int childMode = MeasureSpec.EXACTLY;
        // 迭代测量
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
        }
    }

}
