package com.gionee.bloodsoulnote.circlemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class CircleMenuActivity
        extends AppCompatActivity
{

    private String[] mItemTexts = new String[]{"安全中心", "特色服务", "投资理财", "转账汇款", "我的账户", "信通卡"};

    private int[] mItemImgs = new int[] {R.drawable.s01,
                                         R.drawable.s02,
                                         R.drawable.s03,
                                         R.drawable.s06,
                                         R.drawable.s07,
                                         R.drawable.s08,
                                         };
    private CircleMenuLayout mCircleMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.menulayout);

        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);


    }

    @Override
    public void onAttachedToWindow() {

        super.onAttachedToWindow();
        mCircleMenuLayout.addView(new TextView(this));
    }
}
