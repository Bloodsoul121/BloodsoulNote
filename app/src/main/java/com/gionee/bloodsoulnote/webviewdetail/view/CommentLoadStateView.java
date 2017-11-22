package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class CommentLoadStateView extends RelativeLayout {

    private TextView mStateTv;

    public CommentLoadStateView(Context context) {
        super(context);
        init(context);
    }

    public CommentLoadStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_web_comment_load_state_view, this);
        mStateTv = (TextView) findViewById(R.id.web_comment_load_state_tv);
    }

    public void setStateTv(String state) {
        mStateTv.setText(state);
    }

    public void setStateTvOnClickListener(OnClickListener onClickListener) {
        mStateTv.setOnClickListener(onClickListener);
    }
}
