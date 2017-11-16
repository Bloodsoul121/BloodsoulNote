package com.gionee.bloodsoulnote.customview3.slidefinish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;

public class SlideFinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_finish);

        SwipeFinishLayout swipeFinishLayout = (SwipeFinishLayout) findViewById(R.id.swipe_finish_layout);
    }
}
