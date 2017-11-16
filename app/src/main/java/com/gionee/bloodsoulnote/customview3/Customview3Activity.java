package com.gionee.bloodsoulnote.customview3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.customview3.showAnimator.RightOutActivity;
import com.gionee.bloodsoulnote.customview3.showAnimator.ShowAnimatorActivity;
import com.gionee.bloodsoulnote.customview3.slidefinish.SlideFinishActivity;

public class Customview3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview3);
    }

    public void clickBtn1(View view) {
        startActivity(new Intent(this, SlideFinishActivity.class));
    }

    public void clickBtn2(View view) {
        startActivity(new Intent(this, ShowAnimatorActivity.class));
    }

    public void clickBtn3(View view) {
        startActivity(new Intent(this, RightOutActivity.class));
    }
}
