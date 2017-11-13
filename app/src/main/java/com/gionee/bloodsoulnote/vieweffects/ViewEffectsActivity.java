package com.gionee.bloodsoulnote.vieweffects;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.vieweffects.imageview.ImageViewActivity;
import com.gionee.bloodsoulnote.vieweffects.textview.TextViewActivity;

public class ViewEffectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_effects);
    }

    public void clickBtn1(View view) {
        startActivity(new Intent(this, TextViewActivity.class));
    }

    public void clickBtn2(View view) {
        startActivity(new Intent(this, ImageViewActivity.class));
    }
}
