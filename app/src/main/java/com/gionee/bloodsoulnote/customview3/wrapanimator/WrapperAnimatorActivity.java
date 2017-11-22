package com.gionee.bloodsoulnote.customview3.wrapanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class WrapperAnimatorActivity
        extends AppCompatActivity
{

    private TextView mTv;
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper_animator);

        mContainer = (LinearLayout) findViewById(R.id.container);
        mTv = (TextView) findViewById(R.id.tv);
    }

    public void clickBtn1(View view) {
        ViewWrapper wrapper = new ViewWrapper(mTv);
        ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(500).start();
    }

    public void clickBtn2(View view) {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 300f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(mTv, holder1, holder2, holder3).setDuration(1000).start();
    }

    public void clickBtn3(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setTarget(mTv);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                // // TODO: 2017/11/22
            }
        });    }

    public void clickBtn4(View view) {
        Animator ani = AnimatorInflater.loadAnimator(this, R.animator.objectani);
        ani.setTarget(mTv);
        ani.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void clickBtn5(View view) {
        mTv.animate()
                .alpha(0)
                .y(300)
                .setDuration(300)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                .start();
    }

    public void clickBtn6(View view) {
        TextView tv = new TextView(this);
        tv.setText("add view");
        mContainer.addView(tv);
    }
}
