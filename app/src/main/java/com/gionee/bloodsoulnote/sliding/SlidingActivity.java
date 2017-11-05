package com.gionee.bloodsoulnote.sliding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.sliding.viewdraghelper.ViewGragHeplerActivity;

public class SlidingActivity
        extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);
    }

    public void clickBtn1(View view) {
        startActivity(new Intent(this, ViewGragHeplerActivity.class));
    }

    public void clickBtn2(View view) {

    }

    public void clickBtn3(View view) {

    }
}
