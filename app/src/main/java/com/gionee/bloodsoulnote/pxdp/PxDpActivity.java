package com.gionee.bloodsoulnote.pxdp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class PxDpActivity
        extends AppCompatActivity
{

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_px_dp);

        mTv = (TextView) findViewById(R.id.textView);
    }

    public void clickBtn1(View view) {
        int px = PxUtil.dip2px(this, 20);
        mTv.setText(px + "");
    }

    public void clickBtn2(View view) {
        int dip = PxUtil.px2dip(this, 20);
        mTv.setText(dip + "");
    }
}
