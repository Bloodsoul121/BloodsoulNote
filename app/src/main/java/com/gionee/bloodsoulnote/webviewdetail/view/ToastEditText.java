package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.Toast;

public class ToastEditText extends AppCompatEditText {

    public ToastEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLength(context, attrs);
    }

    private void initLength(Context context, AttributeSet attrs) {
        String namespace = "http://schemas.android.com/apk/res/android";
        int maxLength = attrs.getAttributeIntValue(namespace, "maxLength", -1);
        if (maxLength > -1) {
            setFilters(new InputFilter[]{new ToastLengthFilter(context, maxLength)});
        }
    }

    private static class ToastLengthFilter implements InputFilter {

        private final int mMax;
        private Context context;
        private Toast mToast;

        public ToastLengthFilter(Context context, int max) {
            this.context = context;
            mMax = max;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            int keep = mMax - (dest.length() - (dend - dstart));
            if (keep <= 0) {
                showToast(context, "字数不能超过" + mMax);
                return "";
            } else if (keep >= end - start) {
                return null; // keep original
            } else {
                keep += start;
                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                    --keep;
                    if (keep == start) {
                        return "";
                    }
                }
                return source.subSequence(start, keep);
            }
        }

        private void showToast(Context mContext, String text) {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }

    }

}
