package com.gionee.bloodsoulnote.huitu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.huitu.clock.ClockActivity;
import com.gionee.bloodsoulnote.huitu.layer.LayerActivity;
import com.gionee.bloodsoulnote.huitu.yinyin.YinyinActivity;

public class HuituActivity
        extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huitu);
    }

    public void clickBtn1(View view) {
        startActivity(new Intent(this, YinyinActivity.class));
    }

    public void clickBtn2(View view) {
        startActivity(new Intent(this, LayerActivity.class));
    }

    public void clickBtn3(View view) {
        startActivity(new Intent(this, ClockActivity.class));
    }

    public void clickBtn4(View view) {

    }
}
