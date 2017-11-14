package com.gionee.bloodsoulnote.webviewdetail.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.base.CommonRecyAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;

import java.util.List;

public class CommentAdapter extends CommonRecyAdapter<CommentBean>
        implements CommonRecyAdapter.OnItemChildClickListener<CommentBean>
{

    private CommentBean mData;

    private OnItemChildClickListener mOnItemChildClickListener;

    private TextView mLikeNum;

    private TextView mCommentContent;

    public CommentAdapter(Context context) {
        super(context);
    }

    public CommentAdapter(Context context, List<CommentBean> datas) {
        super(context, datas);
    }

    public CommentAdapter(Context context, List<CommentBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, CommentBean data, boolean isFirstInGroup, boolean isLastInGroup) {
        this.mData = data;
        // 初始状态
        holder.setVisibility(R.id.group_title, isFirstInGroup ? View.VISIBLE : View.GONE);
        holder.setVisibility(R.id.comment_divider, isLastInGroup ? View.GONE : View.VISIBLE);
        holder.setTextLine(R.id.comment_content_more);
        // 点击事件
        addOnItemChildClickListener(R.id.like_img, this);
        addOnItemChildClickListener(R.id.comment_content_more, this);
        addOnItemChildClickListener(R.id.comment_reply, this);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_recy_comment;
    }

    @Override
    protected String getGroupId(CommentBean commentBean) {
        return commentBean.getGroupId();
    }

    @Override
    public void onItemChildClick(ViewHolder viewHolder, CommentBean data, int position, int id) {
        if (mOnItemChildClickListener == null) {
            return;
        }
        switch (id) {
            case R.id.like_img:
                // // TODO: 17-11-13
                // 点赞数 加减1
                mLikeNum = viewHolder.getView(R.id.like_num);

                // 回调
                mOnItemChildClickListener.onLikeClick(viewHolder, mData, position);
                break;
            case R.id.comment_content_more:
                // 查看更多
                mCommentContent = viewHolder.getView(R.id.comment_content);
                mCommentContent.setMaxLines(10);
                String content = mCommentContent.getText() + data.getComment();
                mCommentContent.setText(content);
                break;
            case R.id.comment_reply:
                // 回复
                mOnItemChildClickListener.onReplyClick(viewHolder, mData, position);
                break;
        }
    }

    public interface OnItemChildClickListener {
        void onLikeClick(ViewHolder viewHolder, CommentBean data, int position);
        void onReplyClick(ViewHolder viewHolder, CommentBean data, int position);
    }

    public void setOnItemChildClickListener(OnItemChildClickListener itemChildClickListener) {
        mOnItemChildClickListener = itemChildClickListener;
    }

}
