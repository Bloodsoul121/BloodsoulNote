package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebComment;
import com.gionee.bloodsoulnote.webviewdetail.adapter.CommentAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.CommonRecyAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.presenter.WebCommentPresenter;

import java.util.List;

public class CommentView extends FrameLayout implements IWebComment.IView,
                                                           CommonRecyAdapter.OnLoadMoreListener,
                                                           CommentAdapter.OnItemChildClickListener
{

    private Context mContext;
    private IWebComment.IPresenter mPresenter;
    private RecyclerView           mRecyclerView;
    private CommentAdapter         mRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;

    public CommentView(Context context) {
        super(context);
        init(context);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        WebCommentPresenter.bindPresenter(this);
        mPresenter.loadMoreComments();
    }

    @Override
    public void bindPresenter(IWebComment.IPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initView() {
        mRecyclerView = new RecyclerView(mContext);
        mLayoutManager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    ViewGroup.LayoutParams.MATCH_PARENT));
        mRecyclerAdapter = new CommentAdapter(mContext);
        initAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setOnItemChildClickListener(this);
        addView(mRecyclerView);
    }

    private void initAdapter() {
        mRecyclerAdapter.setOpenLoadMore(true);
        mRecyclerAdapter.setOnLoadMoreListener(this);
        mRecyclerAdapter.setOnItemChildClickListener(this);
        //初始化EmptyView
        mRecyclerAdapter.setEmptyView(R.layout.layout_foot_comment_empty);
        //初始化 开始加载更多的loading View
        mRecyclerAdapter.setLoadingView(R.layout.layout_foot_comment_empty);

//        mRecyclerAdapter.startLoadMore(mRecyclerView, mLayoutManager);

//        //加载失败，更新footer view提示
//        mRecyclerAdapter.setLoadFailedView(R.layout.load_failed_layout);
//        //加载完成，更新footer view提示
//        mRecyclerAdapter.setLoadEndView(R.layout.load_end_layout);
    }

    @Override
    public void bindWebview(WebView webView) {
        // // TODO: 2017/11/12
        // 1. 滑到底部的监听
        // 2. 进度条的监听, start, finish, overrideurl
        // 3. 参数 , 比如 网页id
    }

    @Override
    public void updateNewData(List<CommentBean> datas) {
        Log.i("bloodsoul", "updateNewData --> " + datas.size());
        mRecyclerAdapter.addNewBottomData(datas);
    }

    @Override
    public void refreshItemData(int position) {
        mRecyclerAdapter.refreshItemData(position);
    }

    @Override
    public void deleteItemData(int position) {
        mRecyclerAdapter.deleteItemData(position);
    }

    @Override
    public void onLoadBefore() {
        TextView tv = new TextView(mContext);
        tv.setText("加载中...");
        mRecyclerAdapter.setLoadingView(tv);
    }

    @Override
    public void onLoadFailed() {
        loadBottomEnd();
    }

    @Override
    public void onLoadEnd() {
        loadBottomEnd();
    }

    @Override
    public void onLoadMore(boolean isReload) {
        mPresenter.loadMoreComments();
    }

    private void loadBottomMore() {
        mRecyclerAdapter.startLoadMore();
    }

    private void loadBottomEnd() {
        mRecyclerAdapter.endLoadMore();
    }

    @Override
    public void onLikeClick(ViewHolder viewHolder, CommentBean data, int position) {

    }

    @Override
    public void onReplyClick(ViewHolder viewHolder, CommentBean data, int position) {

    }

    public void bindParentViewGroup(WebDetailView webDetailView) {
        webDetailView.setOnScrollChangeListener(new WebDetailView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(WebDetailView view, int x, int y, int oldx, int oldy) {

            }

            @Override
            public void onScrollBottom() {
                loadBottomMore();
            }

            @Override
            public void onScrollTop() {

            }
        });
    }
}
