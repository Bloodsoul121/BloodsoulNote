package com.gionee.bloodsoulnote.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gionee.bloodsoulnote.R;

public class BujianAniActivity extends AppCompatActivity {

    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bujian_ani);

        mImg = (ImageView) findViewById(R.id.img);
    }

    public void clickBtn1(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        mImg.startAnimation(animation);
    }

    public void clickBtn2(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        mImg.startAnimation(animation);
    }

    public void clickBtn3(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        mImg.startAnimation(animation);
    }

    public void clickBtn4(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        mImg.startAnimation(animation);
    }
}
