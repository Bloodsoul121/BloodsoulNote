package com.gionee.bloodsoulnote.numformat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;

public class NumFormatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_format);
    }

    public void clickBtn1(View view) {
        NumFormatUtil.formatNum();
    }
}
