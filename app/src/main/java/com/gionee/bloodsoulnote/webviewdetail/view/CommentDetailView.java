package com.gionee.bloodsoulnote.webviewdetail.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebCommentDetail;
import com.gionee.bloodsoulnote.webviewdetail.presenter.WebCommentDetailPresenter;

public class CommentDetailView extends RelativeLayout implements IWebCommentDetail.IView, View.OnClickListener {

    private IWebCommentDetail.IPresenter mPresenter;

    private OnCommentDetailClickListener mOnCommentDetailClickListener;
    private SwipeView mSwipeView;

    public CommentDetailView(Context context) {
        super(context);
        init(context);
    }

    public CommentDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_comment_detail, this);
        WebCommentDetailPresenter.bindPresenter(this);
    }

    @Override
    public void initView() {
        ImageView back = (ImageView) findViewById(R.id.comment_detail_back);
        back.setOnClickListener(this);
        mSwipeView = (SwipeView) findViewById(R.id.swipe_view);
//        swipeView.attachToParentView(this);
        mSwipeView.setOnSwipeFinishListener(new SwipeView.OnSwipeFinishListener() {
            @Override
            public void onSwipeFinish() {
                if (mOnCommentDetailClickListener != null) {
                    mOnCommentDetailClickListener.onViewFinish();
                }
            }
        });
    }

    @Override
    public void bindPresenter(IWebCommentDetail.IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_detail_back:
                if (mOnCommentDetailClickListener != null) {
                    mOnCommentDetailClickListener.onClickTopBack();
                }
                break;
        }
    }

    public void OpenCommentDetail() {
        mSwipeView.show();
    }

    private void startAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mSwipeView, "translationX", mSwipeView.getWidth(), 0f);
        animator.setDuration(800);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }

    interface OnCommentDetailClickListener{
        void onClickTopBack();
        void onViewFinish();
    }

    public void setOnCommentDetailClickListener(OnCommentDetailClickListener onCommentDetailClickListener) {
        this.mOnCommentDetailClickListener = onCommentDetailClickListener;
    }

}
