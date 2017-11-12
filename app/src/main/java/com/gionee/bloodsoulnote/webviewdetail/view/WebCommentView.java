package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.gionee.bloodsoulnote.webviewdetail.I.IWebComment;
import com.gionee.bloodsoulnote.webviewdetail.adapter.CommentAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.CommonRecyAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.presenter.WebCommentPresenter;

import java.util.List;

public class WebCommentView extends FrameLayout implements IWebComment.IView,
                                                           CommonRecyAdapter.OnLoadMoreListener,
                                                           CommentAdapter.OnItemChildClickListener
{

    private CommentAdapter         mRecyclerAdapter;
    private RecyclerView           mRecyclerView;
    private IWebComment.IPresenter mPresenter;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;

    public WebCommentView(Context context) {
        super(context);
        init(context);
    }

    public WebCommentView(Context context, AttributeSet attrs) {
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
        mRecyclerView.setAdapter(mRecyclerAdapter);
        addView(mRecyclerView);

        initAdapter();

//        mRecyclerAdapter.setOnItemChildClickListener(this);
    }

    private void initAdapter() {
        mRecyclerAdapter.setOpenLoadMore(true);
        mRecyclerAdapter.setOnLoadMoreListener(this);
        mRecyclerAdapter.startLoadMore(mRecyclerView, mLayoutManager);
        mRecyclerAdapter.setOnItemChildClickListener(this);

//        //初始化EmptyView
//        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
//        mRecyclerAdapter.setEmptyView(emptyView);
//        //初始化 开始加载更多的loading View
//        mRecyclerAdapter.setLoadingView(R.layout.load_loading_layout);
//        //加载失败，更新footer view提示
//        mRecyclerAdapter.setLoadFailedView(R.layout.load_failed_layout);
//        //加载完成，更新footer view提示
//        mRecyclerAdapter.setLoadEndView(R.layout.load_end_layout);
    }

    @Override
    public void bindWebview(WebView webView) {
        // // TODO: 2017/11/12
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
    public void onLoadMore(boolean isReload) {
        mPresenter.loadMoreComments();
    }

    @Override
    public void onDiscussClick(ViewHolder viewHolder, CommentBean data, int position) {

    }

    @Override
    public void onDeleteClick(ViewHolder viewHolder, CommentBean data, int position) {

    }
}