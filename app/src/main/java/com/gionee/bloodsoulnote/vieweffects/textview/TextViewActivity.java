package com.gionee.bloodsoulnote.vieweffects.textview;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class TextViewActivity extends AppCompatActivity {

    private TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        txt1 = ((TextView) findViewById(R.id.txt1));
        txt2 = ((TextView) findViewById(R.id.txt2));
        txt3 = ((TextView) findViewById(R.id.txt3));
        //添加删除线
        txt1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //在代码中设置加粗
        txt2.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        //添加下划线
        txt3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //第四个是在布局文件中设置加粗
        //第五个是在布局文件中设置斜体字
        //第六个是在布局文件中设置斜体加斜体字
    }
}
