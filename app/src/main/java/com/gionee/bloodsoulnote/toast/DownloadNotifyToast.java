package com.gionee.bloodsoulnote.toast;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.gionee.bloodsoulnote.NoteApplication;
import com.gionee.bloodsoulnote.openfile.AndroidUtils;

/**
 * Created by zhuzhejun on 16-12-1.
 */
public class DownloadNotifyToast {
    private static final int DURATION_SHORT = 2000;
    private static final int DURATION_LONG = 5000;

    private static final int DEFAULT_TOAST_TEXT_SIZE = 15;

    private static final int DEFAULT_TOAST_ANIM_DUR = 350;

    private static int DEFAULT_PADDING_LEFT = 18;

    private static int DEFAULT_PADDING_TOP = 15;

    private static int DEFAULT_PADDING_RIGHT = 18;

    private static int DEFAULT_PADDING_BOTTOM = 15;

    private static List<DownloadNotifyToast> sTaskQueue;

    private static DownloadNotifyToast mCurrentToast;

    private static Handler sHandler;
    private Context context;

    private TextView mShowTv;

    private WindowManager mWindowManager;

    private int mToastDuration = DURATION_SHORT;

    private boolean mDismissed = false;

    private List<ToastText> mToastTexts = new ArrayList<>();

    public DownloadNotifyToast(Context context) {
        this.context = context;
    }

    public void setToastDuration(int dura) {
        mToastDuration = dura;
    }

    public void addToastText(ToastText toastText) {
        mToastTexts.add(toastText);
    }

    public void show() {
        enqueueToast(this);
    }

    private static void enqueueToast(DownloadNotifyToast downloadNotifyToast) {
        if (sTaskQueue == null) {
            sTaskQueue = new ArrayList<>();
        }
        sTaskQueue.add(downloadNotifyToast);
        startToastLoop();
    }

    private static void startToastLoop() {
        if (sHandler == null) {
            sHandler = new Handler();
        }

        if (mCurrentToast != null) {
            return;
        }

        if (sTaskQueue.size() <= 0) {
            sHandler = null;
        } else {
            DownloadNotifyToast curToast = sTaskQueue.remove(0);
            mCurrentToast = curToast;
            if (curToast != null) {
                curToast.innShow();
            } else {
                sHandler = null;
            }
        }
    }

    private void innShow() {
        mShowTv = buildColorClickText(context);
        mShowTv.setAlpha(0);
        int textWidth = AndroidUtils.getViewMakeMeasureWidth(mShowTv);

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = AndroidUtils.getWindowsWidth(context) / 2 - textWidth / 2;
        params.y = AndroidUtils.getWindowsHeight(context) * 3 / 4;
        mWindowManager.addView(mShowTv, params);

        doShowAnim();

        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doDismissAnim();
            }
        }, mToastDuration);
    }

    private void doShowAnim() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mShowTv, "Alpha", 0f, 1f);
        oa.setDuration(DEFAULT_TOAST_ANIM_DUR);
        oa.start();
    }

    private void doDismissAnim() {
        if (mDismissed) {
            return;
        }

        ObjectAnimator oa = ObjectAnimator.ofFloat(mShowTv, "Alpha", 1f, 0f);
        oa.setDuration(DEFAULT_TOAST_ANIM_DUR);
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mShowTv.setVisibility(View.INVISIBLE);
                removeShowTextView();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        oa.start();
    }

    private void removeShowTextView() {
        try {
            mDismissed = true;
            mWindowManager.removeView(mShowTv);
            mCurrentToast = null;
            startToastLoop();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private TextView buildColorClickText(Context context) {
        TextView textView = new TextView(context);
        StringBuilder mStringBuilder = new StringBuilder();
        for (ToastText mToastText : mToastTexts) {
            mStringBuilder.append(mToastText.mTextStr);
        }
        SpannableStringBuilder builder = new SpannableStringBuilder(mStringBuilder.toString());

        int index = 0;
        for (ToastText toastText : mToastTexts) {

            if (toastText.getOnClickListener() != null) {
                builder.setSpan(new ClickSpannable(toastText.getOnClickListener()),
                        index, index + toastText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if (toastText.getTextColor() != ToastText.NO_COLOR) {
                builder.setSpan(new ForegroundColorSpan(toastText.getTextColor()),
                        index, index + toastText.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }

            index += toastText.length();
        }

        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(builder);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(DEFAULT_TOAST_TEXT_SIZE);
        textView.setBackgroundResource(com.gionee.bloodsoulnote.R.drawable.toast_custom_bg);
        textView.setPadding(AndroidUtils.dip2px(context, DEFAULT_PADDING_LEFT), AndroidUtils.dip2px(context, DEFAULT_PADDING_TOP),
                AndroidUtils.dip2px(context, DEFAULT_PADDING_RIGHT), AndroidUtils.dip2px(context, DEFAULT_PADDING_BOTTOM));
        return textView;
    }

    private class ClickSpannable extends ClickableSpan implements View.OnClickListener {
        private View.OnClickListener mOnClickListener;

        public ClickSpannable(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }

        @Override
        public void onClick(View widget) {
            removeShowTextView();
            mOnClickListener.onClick(widget);
        }

        @Override
        //去掉下划线
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);
        }
    }

    public static class ToastText {

        protected static final int NO_COLOR = -0x00001;

        private View.OnClickListener mOnClickListener = null;

        private int mTextColor = NO_COLOR;

        private String mTextStr;

        public ToastText(String mTextStr) {
            this.mTextStr = mTextStr;
        }

        public ToastText(@StringRes int res) {
            this.mTextStr = NoteApplication.getInstance().getApplicationContext().getResources().getString(res);
        }

        public int length() {
            if (TextUtils.isEmpty(mTextStr)) {
                return 0;
            }
            return mTextStr.length();
        }

        public View.OnClickListener getOnClickListener() {
            return mOnClickListener;
        }

        public void setOnClickListener(View.OnClickListener mOnClickListener) {
            this.mOnClickListener = mOnClickListener;
        }

        public int getTextColor() {
            return mTextColor;
        }

        public void setTextColor(int mTextColor) {
            this.mTextColor = mTextColor;
        }

        public void setTextColorRes(@ColorRes int res) {
            this.mTextColor = NoteApplication.getInstance().getApplicationContext().getResources().getColor(res);
        }
    }
}
