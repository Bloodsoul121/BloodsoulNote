package com.gionee.bloodsoulnote.materialdesign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.materialdesign.statusbar.StatusBarActivity;

import java.util.ArrayList;
import java.util.List;

public class MaterialDesignActivity
        extends Activity
{

    private ListView     mListView;
    private List<String> mDatas;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        mListView = (ListView) findViewById(R.id.listview);
        mContext = this;
        initData();
        initView();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add("Material Design");
        mDatas.add("状态栏颜色");
    }

    private void initView(){
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mDatas.size();
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
                ViewHolder holder = null;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
                    holder.tvName = (TextView) convertView.findViewById(R.id.name);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.tvName.setText(mDatas.get(position));
                return convertView;
            }

            class ViewHolder {
                ImageView ivIcon;
                TextView  tvName;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(mContext, StatusBarActivity.class));
                        break;
                }
            }
        });
    }
}
