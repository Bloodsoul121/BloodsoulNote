package com.gionee.bloodsoulnote.androidview.textview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class TextViewActivity extends AppCompatActivity {

    private TextView mTv1;
    private TextView mTv2;
    private RelativeLayout mRelativeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view2);

        initView();
        initEvent();
        initTv2();
    }

    private void initTv2() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.channel_red_dot);
        RelativeLayout.LayoutParams redBotParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        redBotParams.addRule(RelativeLayout.RIGHT_OF, mTv2.getId());
        mRelativeContainer.addView(iv, redBotParams);
    }

    private void initView() {
        mTv1 = (TextView) findViewById(R.id.tv1);
        mRelativeContainer = (RelativeLayout) findViewById(R.id.relative_container);
        mTv2 = (TextView) findViewById(R.id.tv2);
    }

    private void initEvent() {
        String str = "汉你哈宁\u3000回复\u3000死定了";

        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.RED), 5, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.BLUE), 8, 11, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        mTv1.setText(builder); // 使用 tostring 就失效啦
        mTv1.setTextColor(Color.GREEN);
    }


}
