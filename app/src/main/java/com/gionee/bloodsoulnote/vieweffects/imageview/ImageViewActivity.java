package com.gionee.bloodsoulnote.vieweffects.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        NumImageView myIv = (NumImageView) findViewById(R.id.num_img);
        myIv.setNum(50);

        com.gionee.bloodsoulnote.webviewdetail.view.NumImageView numImg = (com.gionee.bloodsoulnote.webviewdetail.view.NumImageView) findViewById(R.id.num_img2);
        numImg.setNum(20);
    }
}
