package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class DiscussView extends RelativeLayout implements View.OnClickListener, TextWatcher {

    private Context mContext;

    private ToastEditText mDiscussEdit;

    private OnDiscussViewClickListener mOnDiscussViewClickListener;

    private TextView mDiscussPublish;

    private boolean mIsEditEmpty = true;

    public DiscussView(Context context) {
        super(context);
        init(context);
    }

    public DiscussView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_discuss_view, this);

        TextView discussCancel = (TextView) findViewById(R.id.discuss_cancel);
        mDiscussPublish = (TextView) findViewById(R.id.discuss_publish);
        mDiscussEdit = (ToastEditText) findViewById(R.id.discuss_edit);
        discussCancel.setOnClickListener(this);
        mDiscussPublish.setOnClickListener(this);
        mDiscussEdit.addTextChangedListener(this);
    }

    public void showDiscussBox() {
        setVisibility(VISIBLE);
        mDiscussEdit.setFocusable(true);
        mDiscussEdit.setFocusableInTouchMode(true);
        mDiscussEdit.requestFocus();
        showSoftKeyboard();
    }

    public void hideDiscussBox() {
        setVisibility(GONE);
        mDiscussEdit.setFocusable(false);
        mDiscussEdit.setFocusableInTouchMode(false);
        hideSoftKeyboard();
    }

    private void showSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mDiscussEdit, 0);
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mDiscussEdit.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discuss_cancel:
                if (mOnDiscussViewClickListener != null) {
                    mOnDiscussViewClickListener.onDiscussViewClickCancel();
                }
                break;
            case R.id.discuss_publish:
                if (mOnDiscussViewClickListener != null) {
                    mOnDiscussViewClickListener.onDiscussViewClickPublish(mDiscussEdit.getText().toString());
                }
                break;
        }
    }

    public void clear() {
        mDiscussEdit.setText(null);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        Log.i("DiscussView", "beforeTextChanged content " + s + ", " + start + ", " + after + ", " + count);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        Log.i("DiscussView", "onTextChanged content " + s + ", " + start + ", " + before + ", " + count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        String content = s.toString();
        if (TextUtils.isEmpty(content)) {
            mIsEditEmpty = true;
            mDiscussPublish.setTextColor(getResources().getColor(R.color.web_discuss_view_publish_noclickable_color));
        } else {
            if (mIsEditEmpty) {
                mDiscussPublish.setTextColor(getResources().getColor(R.color.web_discuss_view_publish_clickable_color));
                mIsEditEmpty = false;
            }
        }
    }

    public interface OnDiscussViewClickListener{
        void onDiscussViewClickCancel();
        void onDiscussViewClickPublish(String comment);
    }

    public void setOnDiscussViewClickListener(OnDiscussViewClickListener onDiscussViewClickListener) {
        this.mOnDiscussViewClickListener = onDiscussViewClickListener;
    }

}
