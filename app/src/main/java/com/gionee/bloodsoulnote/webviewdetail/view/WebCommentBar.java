package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.gionee.bloodsoulnote.R;

public class WebCommentBar extends LinearLayout {

    public WebCommentBar(Context context) {
        super(context);
        init(context);
    }

    public WebCommentBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_web_comment_bar, this);
    }

}
