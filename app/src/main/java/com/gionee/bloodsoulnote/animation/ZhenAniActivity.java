package com.gionee.bloodsoulnote.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gionee.bloodsoulnote.R;

public class ZhenAniActivity extends AppCompatActivity {

    private ImageView mImg;
    private AnimationDrawable mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhen_ani);

        mImg = (ImageView) findViewById(R.id.img);
        mImg.setBackgroundResource(R.drawable.frames_animation);
        mAnimation = (AnimationDrawable) mImg.getBackground();
    }

    public void clickBtn1(View view) {
        mAnimation.start();
    }

    public void clickBtn2(View view) {
        mAnimation.stop();
    }
}
