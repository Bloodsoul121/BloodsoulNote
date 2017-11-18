package com.gionee.bloodsoulnote.vieweffects.textview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class TextViewLinesActivity extends AppCompatActivity {

    /**
     * 最多展示3行。
     */
    private static final int LINES = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_lines);

        final TextView textView = (TextView) findViewById(R.id.tv);
        textView.setText(R.string.text);

        TextView btn = (TextView) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取省略的字符数，0表示没和省略
                int ellipsisCount = textView.getLayout().getEllipsisCount(textView.getLineCount() - 1);
                //ellipsisCount>0说明没有显示全部，存在省略部分。
                if (ellipsisCount > 0) {
                    //展示全部，按钮设置为点击收起。
                    textView.setMaxHeight(getResources().getDisplayMetrics().heightPixels);
                    ((TextView) findViewById(R.id.btn)).setText("收起");
                } else {
                    //显示2行，按钮设置为点击显示全部。
                    ((TextView) findViewById(R.id.btn)).setText("显示全部");
                    textView.setMaxLines(LINES);
                }
            }
        });

        textView.post(new Runnable() {
            @Override
            public void run() {
                //获取省略的字符数，0表示没和省略
                int ellipsisCount = textView.getLayout().getEllipsisCount(textView.getLineCount() - 1);
                Log.i("TextViewLinesActivity", "ellipsisCount --> " + ellipsisCount);
            }
        });

    }
}
