package com.gionee.bloodsoulnote.webviewdetail.adapter;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.webviewdetail
 *  @文件名:   CommentAdapter
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/12 12:36
 *  @描述：    TODO
 */

import android.content.Context;

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
    protected void convert(ViewHolder holder, CommentBean data) {
        this.mData = data;
        holder.setText(R.id.item_recy_comment_content, data.getComment());

        addOnItemChildClickListener(R.id.item_recy_comment_discuss, this);
        addOnItemChildClickListener(R.id.item_recy_comment_delete, this);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_recy_comment;
    }

    @Override
    public void onItemChildClick(ViewHolder viewHolder, CommentBean data, int position, int id) {
        if (mOnItemChildClickListener == null) {
            return;
        }
        switch (id) {
            case R.id.item_recy_comment_discuss:
                mOnItemChildClickListener.onDiscussClick(viewHolder, mData, position);
                break;
            case R.id.item_recy_comment_delete:
                mOnItemChildClickListener.onDeleteClick(viewHolder, mData, position);
                break;
        }
    }

    public interface OnItemChildClickListener {
        void onDiscussClick(ViewHolder viewHolder, CommentBean data, int position);
        void onDeleteClick(ViewHolder viewHolder, CommentBean data, int position);
    }

    public void setOnItemChildClickListener(OnItemChildClickListener itemChildClickListener) {
        mOnItemChildClickListener = itemChildClickListener;
    }

}
