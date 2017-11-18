package com.gionee.bloodsoulnote.webviewdetail.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebCommentDetail;
import com.gionee.bloodsoulnote.webviewdetail.adapter.CommentDetailAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.presenter.WebCommentDetailPresenter;

import java.util.List;

public class CommentDetailView extends RelativeLayout implements IWebCommentDetail.IView,
        View.OnClickListener, CommentDetailAdapter.OnItemChildClickListener,
        CustomSwipeView.OnSwipeFinishListener, DiscussView.OnDiscussViewClickListener {

    private Context mContext;

    private CustomSwipeView mSwipeView;

    private IWebCommentDetail.IPresenter mPresenter;

    private OnCommentDetailClickListener mOnCommentDetailClickListener;

    private CommentBean mData;

    private CommentDetailAdapter mAdapter;

    private DiscussView mDiscussView;

    private Toast mToast;

    private TextView mBottomBar;

    private boolean mIsFirstIn = true;

    private boolean mIsDataChange;

    public CommentDetailView(Context context) {
        super(context);
        init(context);
    }

    public CommentDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_comment_detail, this);
        WebCommentDetailPresenter.bindPresenter(this);
    }

    @Override
    public void initView() {
        ImageView back = (ImageView) findViewById(R.id.comment_detail_back);
        back.setOnClickListener(this);
        mSwipeView = (CustomSwipeView) findViewById(R.id.swipe_view);
        mSwipeView.setOnSwipeFinishListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.comment_detail_content);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CommentDetailAdapter(mContext);
        mAdapter.setOpenLoadMore(false);
        mAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(mAdapter);
        mBottomBar = (TextView) findViewById(R.id.comment_detail_bottom_bar);
        mDiscussView = (DiscussView) findViewById(R.id.comment_detail_discuss_view);
        mBottomBar.setOnClickListener(this);
        mDiscussView.setOnDiscussViewClickListener(this);
    }

    @Override
    public void onDiscussViewClickCancel() {
        showBottomBar();
    }

    @Override
    public void onDiscussViewClickPublish(String comment) {
        if (TextUtils.isEmpty(comment)) {
            toast("发表不能为空");
            return;
        }
        mPresenter.publish(comment);
    }

    @Override
    public void onSwipeFinish() {
        if (mOnCommentDetailClickListener != null) {
            mOnCommentDetailClickListener.onCommentDetailViewSwipeFinish(mData, mIsDataChange);
        }
    }

    private void updateSelfComment(CommentBean bean) {
        mAdapter.addNewBottomData(bean);
    }

    private void showBottomBar() {
        mDiscussView.hideDiscussBox();
        mBottomBar.setVisibility(VISIBLE);
    }

    private void showDiscussBox() {
        mDiscussView.showDiscussBox();
        mBottomBar.setVisibility(GONE);
    }

    @Override
    public void bindPresenter(IWebCommentDetail.IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void publishFailed() {
        // 发表失败
        toast(getResources().getString(R.string.publish_failed));
    }

    @Override
    public void publishSucceed(String comment) {
        // 发表成功
        showBottomBar();
        toast(getResources().getString(R.string.publish_succeed));
        // 更新评论区, 滑到最新评论区
        CommentBean bean = new CommentBean();
        bean.setName("13654982630");
        bean.setComment("评论区 - " + comment);
        bean.setGroupId("new");
        bean.setId("" + System.currentTimeMillis());
        updateSelfComment(bean);

        mIsDataChange = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_detail_back:
                mSwipeView.hide();
                if (mOnCommentDetailClickListener != null) {
                    mOnCommentDetailClickListener.onCommentDetailViewClickBack(mData, mIsDataChange);
                }
                break;
            case R.id.comment_detail_bottom_bar:
                showDiscussBox();
                break;
        }
    }

    public void OpenCommentDetail(CommentBean data, int startX) {
        show(startX);
        checkLastData(data);
    }

    private void checkLastData(CommentBean data) {
        if (data == null) {
            return;
        } else if (mData == null) {
            // 第一次进
            setNewData(data.getDetails());
        } else if (mData.getId() != null && mData.getId().equals(data.getId())) {
            // 同一个数据
            return;
        } else if (!mData.getId().equals(data.getId())){
            // 不同数据
            setNewData(data.getDetails());
        } else {
            return;
        }
        mData = data;
    }

    private void setNewData(List<CommentBean> datas) {
        mAdapter.setNewData(datas);
    }

    private void show(int startX) {
        if (mIsFirstIn) {
            showSwipeView(startX);
            mIsFirstIn = false;
        } else {
            mSwipeView.show();
        }
    }

    private void showSwipeView(float startX) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mSwipeView, "translationX", startX, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(300);
        animator.start();
    }

    @Override
    public void onItemChildLikeClick(ViewHolder viewHolder, CommentBean data, int position) {

    }

    @Override
    public void onItemChildReplyClick(ViewHolder viewHolder, CommentBean data, int position) {
        showDiscussBox();
    }

    public void hide() {
        mSwipeView.hide();
    }

    interface OnCommentDetailClickListener{
        void onCommentDetailViewClickBack(CommentBean data, boolean isDataChange);
        void onCommentDetailViewSwipeFinish(CommentBean data, boolean isDataChange);
    }

    public void setOnCommentDetailClickListener(OnCommentDetailClickListener onCommentDetailClickListener) {
        this.mOnCommentDetailClickListener = onCommentDetailClickListener;
    }

    public boolean onBackCliked() {
        if (mDiscussView != null && mDiscussView.getVisibility() == VISIBLE) {
            showBottomBar();
            return true;
        }
        return false;
    }

    private void toast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

}
