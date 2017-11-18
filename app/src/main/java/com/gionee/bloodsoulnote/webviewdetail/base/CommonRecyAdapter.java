package com.gionee.bloodsoulnote.webviewdetail.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRecyAdapter<T> extends RecyclerView.Adapter<ViewHolder>
{

    public static final int TYPE_COMMON_VIEW = 100001;
    public static final int TYPE_FOOTER_VIEW = 100002;
    public static final int TYPE_EMPTY_VIEW = 100003;
    public static final int TYPE_NODATE_VIEW = 100004;
    public static final int TYPE_RELOAD_VIEW = 100005;

    protected Context mContext;

    private LayoutInflater mInflater;

    protected List<T> mDatas;

    private boolean mOpenLoadMore = false;
    private boolean isAutoLoadMore = true;
    private boolean isRemoveEmptyView = false;

    private OnItemClickListeners<T> mItemClickListener;
    private OnLoadMoreListener mLoadMoreListener;

    private RelativeLayout mFooterLayout;
    private View mLoadingView;
    private View mLoadFailedView;
    private View mLoadEndView;
    private View mEmptyView;
    private View mReloadView;

    private ArrayList<Integer> mItemChildIds = new ArrayList<>();
    private ArrayList<OnItemChildClickListener<T>> mItemChildListeners = new ArrayList<>();

    public CommonRecyAdapter(Context context) {
        init(context, null, false);
    }

    public CommonRecyAdapter(Context context, List<T> datas) {
        init(context, datas, false);
    }

    public CommonRecyAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        init(context, datas, isOpenLoadMore);
    }

    public void init(Context context, List<T> datas, boolean isOpenLoadMore) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas == null ? new ArrayList<T>() : datas;
        mOpenLoadMore = isOpenLoadMore;
    }

    public void setData(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_COMMON_VIEW:
                viewHolder = ViewHolder.create(mContext, getItemLayoutId(), parent);
                break;
            case TYPE_FOOTER_VIEW:
                if (mFooterLayout == null) {
                    mFooterLayout = new RelativeLayout(mContext);
                }
                viewHolder = ViewHolder.create(mFooterLayout);
                break;
            case TYPE_EMPTY_VIEW:
                viewHolder = ViewHolder.create(mEmptyView);
                break;
            case TYPE_NODATE_VIEW:
                viewHolder = ViewHolder.create(new View(mContext));
                break;
            case TYPE_RELOAD_VIEW:
                viewHolder = ViewHolder.create(mReloadView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_COMMON_VIEW:
                bindCommonItem(holder, position);
                break;
            case TYPE_FOOTER_VIEW:
                break;
            case TYPE_EMPTY_VIEW:
                break;
            case TYPE_NODATE_VIEW:
                break;
            case TYPE_RELOAD_VIEW:
                break;
        }
    }

    private void bindCommonItem(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        convert(viewHolder, mDatas.get(position), position, isFirstInGroup(position), isLastInGroup(position));
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(viewHolder, mDatas.get(position), position);
                }
            }
        });

        for (int i = 0; i < mItemChildIds.size(); i++) {
            final int tempI = i;
            if (viewHolder.getConvertView().findViewById(mItemChildIds.get(i)) != null) {
                viewHolder.getConvertView().findViewById(mItemChildIds.get(i)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemChildListeners.get(tempI).onItemChildClick(viewHolder, mDatas.get(position), position, mItemChildIds.get(tempI));
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas.isEmpty() && mEmptyView != null) {
            return 1;
        }
        return mDatas.size() + + getFooterViewCount();
    }

    private int getFooterViewCount() {
        return mOpenLoadMore && !mDatas.isEmpty() ? 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.isEmpty()) {
            if (mEmptyView != null && !isRemoveEmptyView) {
                return TYPE_EMPTY_VIEW;
            }

            if (isRemoveEmptyView && mReloadView != null) {
                return TYPE_RELOAD_VIEW;
            } else {
                return TYPE_NODATE_VIEW;
            }
        }
        if (isFooterView(position)) {
            return TYPE_FOOTER_VIEW;
        }
        return TYPE_COMMON_VIEW;
    }

    public T getItem(int position) {
        if (mDatas.isEmpty()) {
            return null;
        }
        return mDatas.get(position);
    }

    protected abstract void convert(ViewHolder holder, T data, int position, boolean isFirstInGroup, boolean isLastInGroup);

    protected abstract int getItemLayoutId();

    public void setOnItemClickListener(OnItemClickListeners<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    public void addOnItemChildClickListener(int viewId, OnItemChildClickListener<T> itemChildClickListener) {
        mItemChildIds.add(viewId);
        mItemChildListeners.add(itemChildClickListener);
    }

    public interface OnItemClickListeners<T> {
        void onItemClick(ViewHolder viewHolder, T t, int position);
    }

    public interface OnLoadMoreListener{
        void onLoadMore(boolean isReload);
    }

    public interface OnItemChildClickListener<T> {
        void onItemChildClick(ViewHolder viewHolder, T data, int position, int id);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFooterView(position)) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    private boolean isFooterView(int position) {
        return mOpenLoadMore && position >= getItemCount() - 1;
    }

    public void setOpenLoadMore(boolean isOpenLoadMore) {
        mOpenLoadMore = isOpenLoadMore;
    }

    public void startLoadMore() {
        if (!mOpenLoadMore || mLoadMoreListener == null) {
            return;
        }

        if (isAutoLoadMore) {
            scrollLoadMore();
            isAutoLoadMore = false;
        }
    }

    public void endLoadMore() {
        isAutoLoadMore = true;
    }

    private void scrollLoadMore() {
        if (mFooterLayout != null && mFooterLayout.getChildAt(0) == mLoadingView) {
            mLoadMoreListener.onLoadMore(false);
        }
    }

    public void addNewBottomData(List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        int count = mDatas.size();
        this.mDatas.addAll(datas);
        if (count <= 0) {
            notifyDataSetChanged();
        } else {
            notifyItemChanged(count - 1);
        }
    }

    public void addNewBottomData(T datas) {
        if (datas == null) {
            return;
        }
        int count = mDatas.size();
        this.mDatas.add(datas);
        if (count <= 0) {
            notifyDataSetChanged();
        } else {
            notifyItemChanged(count - 1);
        }
    }

    public void addNewTopData(List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        this.mDatas.addAll(0, datas);
        notifyDataSetChanged();
    }

    public void addNewTopData(T datas) {
        if (datas == null) {
            return;
        }
        this.mDatas.add(0, datas);
        notifyDataSetChanged();
    }

    public void setNewData(List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void refreshItemData(int position) {
        notifyItemChanged(position);
    }

    public void deleteItemData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    public void setEmptyView(int emptyId) {
        setEmptyView(mInflater.inflate(emptyId, null));
    }

    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
        addFooterView(mLoadingView);
    }

    public void setLoadFailedView(View loadFailedView) {
        mLoadFailedView = loadFailedView;
    }

    public void setLoadEndView(View loadEndView) {
        mLoadEndView = loadEndView;
    }

    public void setReloadView(View reloadView) {
        mReloadView = reloadView;
        isRemoveEmptyView = true;
        notifyDataSetChanged();
    }

    public void removeEmptyView() {
        isRemoveEmptyView = true;
        notifyDataSetChanged();
    }

    public void setLoadingView(int loadingId) {
        setLoadingView(mInflater.inflate(loadingId, null));
    }

    public void setLoadFailedView(int loadFailedId) {
        setLoadFailedView(mInflater.inflate(loadFailedId, null));
    }

    public void setLoadEndView(int loadEndId) {
        setLoadEndView(mInflater.inflate(loadEndId, null));
    }

    public void loadEnd() {
        if (mLoadEndView != null) {
            addFooterView(mLoadEndView);
        }
    }

    public void loadFailed() {
        addFooterView(mLoadFailedView);
        mLoadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFooterView(mLoadingView);
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore(true);
                }
            }
        });
    }

    private void addFooterView(View footerView) {
        if (footerView == null) {
            return;
        }

        if (mFooterLayout == null) {
            mFooterLayout = new RelativeLayout(mContext);
        }
        removeFooterView();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                             ViewGroup.LayoutParams.WRAP_CONTENT);
        mFooterLayout.addView(footerView, params);
    }

    private void removeFooterView() {
        mFooterLayout.removeAllViews();
    }

    public void reset() {
        if (mLoadingView != null) {
            addFooterView(mLoadingView);
        }
        isAutoLoadMore = true;
        mDatas.clear();
    }

    private boolean isFirstInGroup(int position) {
        if (position == 0) {
            return true;
        } else {
            String prevGroupId = getGroupId(mDatas.get(position - 1));
            String groupId = getGroupId(mDatas.get(position));
            return prevGroupId != null && groupId != null && !prevGroupId.equals(groupId);
        }
    }

    private boolean isLastInGroup(int position) {
        if (position == mDatas.size() - 1) {
            return true;
        } else {
            String groupId = getGroupId(mDatas.get(position));
            String afterGroupId = getGroupId(mDatas.get(position + 1));
            return afterGroupId != null && groupId != null && !groupId.equals(afterGroupId);
        }
    }

    protected abstract String getGroupId(T t);

}
