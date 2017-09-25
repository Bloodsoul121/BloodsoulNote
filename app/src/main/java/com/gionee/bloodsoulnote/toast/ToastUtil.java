package com.gionee.bloodsoulnote.toast;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cgz on 17-9-22.
 */

public class ToastUtil {

    // 消息内容
    private String message;
    // 图标
    private int icon;
    // 字体大小
    private int textSize = 0;
    // 字体颜色
    private String textColor;
    // 背景颜色
    private int bgColor = 0;
    // 上下文
    private Context mContext;
    // 是否显示
    private boolean mShow = false;
    // Toast
    private Toast mToast;
    // 布局
    private LinearLayout mLayout;
    // 位置
    private int gravity = 0;
    // ImageView
    private ImageView mImgView;
    // TextView
    private TextView mTxtContent;
    // 显示时长
    private int duration = 0;
    // X轴偏移量
    private int floatX;
    // Y轴偏移量
    private int floatY;
    // 图标大小
    private int mImageSize;

    //构造函数设置为私有的，不能直接New
    private ToastUtil() {}

    /**
     * Builder
     *
     * @author Silence
     *
     */
    public static class Builder {
        ToastUtil mToastUtil = new ToastUtil();

        public Builder(Context context) {
            mToastUtil.mContext = context;

        }

        /**
         * 消息内容
         *
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            mToastUtil.message = message;
            return this;
        }

        /**
         * Toast显示位置
         *
         * @param gravity
         * @return
         */
        public Builder setGrivaty(int gravity) {
            mToastUtil.gravity = gravity;
            return this;
        }

        /**
         * 显示的图标
         *
         * @param icon
         * @return
         */
        public Builder setIcon(int icon) {
            mToastUtil.icon = icon;
            return this;
        }

        /**
         * 现实时长
         *
         * @param duration
         * @return
         */
        public Builder setDuration(int duration) {
            mToastUtil.duration = duration;
            return this;
        }

        /**
         * 显示的字体颜色
         *
         * @param textColor
         * @return
         */
        public Builder setTextColor(String textColor) {
            mToastUtil.textColor = textColor;
            return this;
        }

        /**
         * 显示的字体大小
         *
         * @param textSize
         * @return
         */
        public Builder setTextSise(int textSize) {
            mToastUtil.textSize = textSize;
            return this;
        }

        /**
         * X轴偏移量
         *
         * @param floatX
         * @return
         */
        public Builder setFloatX(int floatX) {
            mToastUtil.floatX = floatX;
            return this;
        }

        /**
         * Y轴偏移量
         *
         * @param floatY
         * @return
         */
        public Builder setFloatY(int floatY) {
            mToastUtil.floatY = floatY;
            return this;
        }

        /**
         * 图标大小
         *
         * @param imageSize
         * @return
         */
        public Builder setImageSize(int imageSize) {
            mToastUtil.mImageSize = imageSize;
            return this;
        }

        /**
         * 显示的背景颜色
         *
         * @param bgColor
         * @return
         */
        public Builder setBackgroudColor(int bgColor) {
            mToastUtil.bgColor = bgColor;
            return this;
        }

        /**
         * 创建
         *
         * @return
         */
        public ToastUtil build() {
            mToastUtil.setLayoutView();
            return mToastUtil;
        }

    }

    public void setLayoutView() {
        if (!mShow) {
            mToast = new Toast(mContext);
            // 图标
            mImgView = new ImageView(mContext);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(mImageSize, mImageSize);
            mImgView.setImageResource(icon);
            lParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            lParams.setMargins(5, 5, 5, 5);
            mImgView.setLayoutParams(lParams);

            // 消息内容
            mTxtContent = new TextView(mContext);
            LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (!TextUtils.isEmpty(textColor)) {
                mTxtContent.setTextColor(Color.parseColor(textColor));
            }
            if (textSize != 0) {
                mTxtContent.setTextSize(textSize);
            }
            mTxtContent.setLayoutParams(lParams1);
            // 布局
            mLayout = new LinearLayout(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mLayout.setOrientation(LinearLayout.HORIZONTAL);
            mLayout.setLayoutParams(params);
            mLayout.addView(mImgView);
            mLayout.addView(mTxtContent);
            if (bgColor != 0) {

                mLayout.setBackgroundResource(bgColor);
            }
            if (gravity != 0) {
                mToast.setGravity(gravity, floatX, floatY);
            }
            mToast.setView(mLayout);
            if (duration != 0) {
                mToast.setDuration(Toast.LENGTH_LONG);
            }
            if (!TextUtils.isEmpty(message)) {
                mTxtContent.setText(message);
            }
            mToast.show();
        }
    }

}
