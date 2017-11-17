package com.gionee.bloodsoulnote.webviewdetail.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.base.CommonRecyAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.view.CircularImageView;

import java.util.List;

public class CommentAdapter extends CommonRecyAdapter<CommentBean>
        implements CommonRecyAdapter.OnItemChildClickListener<CommentBean>
{

    private CommentBean mData;

    private OnItemChildClickListener mOnItemChildClickListener;

    private TextView mLikeNum;

    private TextView mCommentContent;

    private boolean isHasLiked;

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
    protected void convert(ViewHolder holder, CommentBean data, int position, boolean isFirstInGroup, boolean isLastInGroup) {
        this.mData = data;
        // 初始状态
        holder.setVisibility(R.id.group_title, isFirstInGroup ? View.VISIBLE : View.GONE);
        holder.setVisibility(R.id.comment_divider, isLastInGroup ? View.GONE : View.VISIBLE);
        holder.setTextLine(R.id.comment_content_more);
        // 点击事件
        addOnItemChildClickListener(R.id.like_img, this);
        addOnItemChildClickListener(R.id.comment_content_more, this);
        addOnItemChildClickListener(R.id.comment_reply, this);
        // 初始数据
        CircularImageView userImg = holder.getView(R.id.user_img);
//        TextView groupTitle = holder.getView(R.id.group_title);
//        TextView userName = holder.getView(R.id.user_name);
//        TextView likeNum = holder.getView(R.id.like_num);
//        TextView commentContent = holder.getView(R.id.comment_content);
//        TextView commentTime = holder.getView(R.id.comment_time);
        holder.setText(R.id.comment_content, data.getComment());
//        holder.setText(R.id.user_name, data.getComment());
//        holder.setText(R.id.like_num, data.getComment());
//        holder.setText(R.id.comment_time, data.getComment());
        holder.setText(R.id.group_title, data.getGroupId());
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
        switch (id) {
            case R.id.like_img:
                // 点赞数 加减1
                clickLike(viewHolder);

                // 回调
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildLikeClick(viewHolder, data, position);
                }
                break;
            case R.id.comment_content_more:
                // 查看更多
                viewHolder.setMaxLine(R.id.comment_content, 10);
                mCommentContent = viewHolder.getView(R.id.comment_content);
                String content = mCommentContent.getText() + data.getComment();
                mCommentContent.setText(content);
                break;
            case R.id.comment_reply:
                // 回复
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildReplyClick(viewHolder, data, position);
                }
                break;
        }
    }

    private void clickLike(ViewHolder viewHolder) {
        mLikeNum = viewHolder.getView(R.id.like_num);
        if (isHasLiked) {
            String num = mLikeNum.getText().toString();
            int newNum = Integer.valueOf(num) - 1;
            mLikeNum.setText(String.valueOf(newNum));
        } else {
            String num = mLikeNum.getText().toString();
            int newNum = Integer.valueOf(num) + 1;
            mLikeNum.setText(String.valueOf(newNum));
        }
        isHasLiked = !isHasLiked;
    }

    public interface OnItemChildClickListener {
        void onItemChildLikeClick(ViewHolder viewHolder, CommentBean data, int position);
        void onItemChildReplyClick(ViewHolder viewHolder, CommentBean data, int position);
    }

    public void setOnItemChildClickListener(OnItemChildClickListener itemChildClickListener) {
        mOnItemChildClickListener = itemChildClickListener;
    }

}
