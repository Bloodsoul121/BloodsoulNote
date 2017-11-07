package com.gionee.bloodsoulnote.listview.testlistview;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.listview.testlistview
 *  @文件名:   ViewHolderAdapter
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/4 17:24
 *  @描述：    TODO
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

import java.util.List;

public class ViewHolderAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDatas;
    private final LayoutInflater mInflater;

    public ViewHolderAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setBackgroundResource(R.drawable.marker);
        if (mDatas != null) {
            holder.tv.setText(mDatas.get(position));
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView img;
        public TextView tv;
        private ViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.img);
            tv = (TextView) view.findViewById(R.id.name);
        }
    }

}
