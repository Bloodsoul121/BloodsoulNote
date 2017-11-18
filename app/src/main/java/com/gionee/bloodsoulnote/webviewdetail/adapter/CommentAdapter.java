package com.gionee.bloodsoulnote.webviewdetail.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.base.CommonRecyAdapter;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.util.GNRegexUtils;
import com.gionee.bloodsoulnote.webviewdetail.view.CircularImageView;

import java.util.List;

public class CommentAdapter extends CommonRecyAdapter<CommentBean>
        implements CommonRecyAdapter.OnItemChildClickListener<CommentBean>
{

    private OnItemChildClickListener mOnItemChildClickListener;

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
    protected void convert(final ViewHolder holder, CommentBean data, int position, boolean isFirstInGroup, boolean isLastInGroup) {
        // 初始状态
        holder.setVisibility(R.id.group_title, isFirstInGroup ? View.VISIBLE : View.GONE);
        holder.setVisibility(R.id.group_cursor, isFirstInGroup ? View.VISIBLE : View.GONE);
        holder.setVisibility(R.id.comment_divider, isLastInGroup ? View.GONE : View.VISIBLE);
        holder.setVisibility(R.id.comment_divider_half_whole, isLastInGroup ? View.VISIBLE : View.GONE);

        // 点击事件
        addOnItemChildClickListener(R.id.like_img, this);
        addOnItemChildClickListener(R.id.comment_content_more, this);
        addOnItemChildClickListener(R.id.comment_reply, this);
        // 初始数据
        CircularImageView userImg = holder.getView(R.id.user_img);
        holder.setText(R.id.group_title, data.getGroupId());
        holder.setText(R.id.comment_content, data.getComment());
        holder.setText(R.id.user_name, GNRegexUtils.isMobileNO(data.getName()) ?
                GNRegexUtils.formatPhoneNum(data.getName()) : data.getName());
//        holder.setText(R.id.like_num, data.getComment());
//        holder.setText(R.id.comment_time, data.getComment());

        // 查看更多
        final TextView contentView = holder.getView(R.id.comment_content);
        contentView.post(new Runnable() {
            @Override
            public void run() {
                int ellipsisCount = contentView.getLayout().getEllipsisCount(contentView.getLineCount() - 1);
                holder.setVisibility(R.id.comment_content_more, ellipsisCount > 0 ? View.VISIBLE : View.GONE);
            }
        });
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
                clickLike(viewHolder, data);
                // 回调
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildLikeClick(viewHolder, data, position);
                }
                break;
            case R.id.comment_content_more:
                // 查看更多
                viewHolder.setMaxLine(R.id.comment_content, 10);
                viewHolder.setText(R.id.comment_content, data.getComment());
                viewHolder.setVisibility(R.id.comment_content_more, View.GONE);
                break;
            case R.id.comment_reply:
                // 回复
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildReplyClick(viewHolder, data, position);
                }
                break;
        }
    }

    private void clickLike(ViewHolder viewHolder, CommentBean data) {
        TextView likeNum = viewHolder.getView(R.id.like_num);
        ImageView likeImg = viewHolder.getView(R.id.like_img);
        if (data.isHasLiked()) {
            String num = likeNum.getText().toString();
            int newNum = Integer.valueOf(num) - 1;
            likeNum.setText(String.valueOf(newNum));
            likeNum.setTextColor(mContext.getResources().getColor(R.color.web_comment_item_like_num_color));
            likeImg.setImageLevel(0);
        } else {
            String num = likeNum.getText().toString();
            int newNum = Integer.valueOf(num) + 1;
            likeNum.setText(String.valueOf(newNum));
            likeNum.setTextColor(mContext.getResources().getColor(R.color.web_comment_item_like_num_like_color));
            likeImg.setImageLevel(1);
        }
        data.setHasLiked(!data.isHasLiked());
    }

    public interface OnItemChildClickListener {
        void onItemChildLikeClick(ViewHolder viewHolder, CommentBean data, int position);
        void onItemChildReplyClick(ViewHolder viewHolder, CommentBean data, int position);
    }

    public void setOnItemChildClickListener(OnItemChildClickListener itemChildClickListener) {
        mOnItemChildClickListener = itemChildClickListener;
    }

    public void addNewCommentData(List<CommentBean> datas) {
        if (datas == null || datas.size() <= 0) {
            return;
        }
        // 如果是 most new
        addMostNewData(null);
        // most hot
        addMostHotData(null);
        notifyDataSetChanged();
    }

    private void addMostNewData(List<CommentBean> datas) {
        mDatas.addAll(0, datas);
    }

    private void addMostHotData(List<CommentBean> datas) {
        mDatas.addAll(datas);
    }

}
