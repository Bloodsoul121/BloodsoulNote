package com.gionee.bloodsoulnote.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gionee.bloodsoulnote.R.layout.activity_layout);

        ImageView img = (ImageView) findViewById(com.gionee.bloodsoulnote.R.id.img);
    }
}
