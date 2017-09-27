package com.gionee.bloodsoulnote.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    public void onclick1(View view) {
        startActivity(new Intent(this, ZhenAniActivity.class));
    }

    public void onclick2(View view) {
        startActivity(new Intent(this, BujianAniActivity.class));
    }

    public void onclick3(View view) {
        startActivity(new Intent(this, ShuxingAniActivity.class));
    }
}
