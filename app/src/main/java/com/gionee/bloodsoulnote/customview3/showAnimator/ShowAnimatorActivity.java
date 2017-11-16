package com.gionee.bloodsoulnote.customview3.showAnimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.pxdp.PxUtil;

public class ShowAnimatorActivity
        extends AppCompatActivity
{

    private View    mView1;
    private View    mView2;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animator);

        mView1 = findViewById(R.id.view1);
        mView2 = findViewById(R.id.view2);

        mContext = this;

        mView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ShowAnimatorActivity", "getMeasuredHeight --> " + mView2.getMeasuredHeight());

                if (mView2.getVisibility() == View.GONE) {
                    showAnimator();
                } else {
                    closeAnimator();
                }
            }
        });
    }

    private void showAnimator() {
        int height = PxUtil.dip2px(mContext, 100);

        mView2.setVisibility(View.VISIBLE);
        ValueAnimator dropAnimator = createDropAnimator(mView2,
                                                        0,
                                                        height);
        dropAnimator.start();
    }

    private void closeAnimator() {
        int height = PxUtil.dip2px(mContext, 100);

        ValueAnimator animator = createDropAnimator(mView2, height, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView2.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
