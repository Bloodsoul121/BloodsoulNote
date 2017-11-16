package com.gionee.bloodsoulnote.customview3.showAnimator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.gionee.bloodsoulnote.R;

public class RightOutActivity
        extends AppCompatActivity
{

    private View    mView1;
    private View    mView2;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_out);

        mView1 = findViewById(R.id.view1);
        mView2 = findViewById(R.id.view2);

        mView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView2.setVisibility(View.VISIBLE);
                fromRightMoveToLeft();
            }
        });
    }

    private void moveUpToView() {
        //                mView1.setVisibility(View.GONE);
        mView2.setVisibility(View.VISIBLE);
        //                mView1.setAnimation(moveToViewBottom());
        mView2.setAnimation(moveToViewLocation());
    }

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                                                  Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                                                  0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                                                  Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                                                  1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    private void fromRightMoveToLeft() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView2, "translationX", 600.0f, 0.0f);
        animator.setDuration(500);
        animator.start();
    }


}
