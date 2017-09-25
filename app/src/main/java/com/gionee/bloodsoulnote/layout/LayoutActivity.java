package com.gionee.bloodsoulnote.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

import java.util.ArrayList;
import java.util.List;

public class LayoutActivity extends AppCompatActivity {

    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gionee.bloodsoulnote.R.layout.activity_layout);

        ImageView img = (ImageView) findViewById(com.gionee.bloodsoulnote.R.id.img);

        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.listview);
        mList = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            mList.add("item - " + i);
        }
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView tv = new TextView(LayoutActivity.this);
                tv.setText(mList.get(i));
                return tv;
            }
        });
    }
}
