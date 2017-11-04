package com.gionee.bloodsoulnote.listview.customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.listview.testlistview.ViewHolderAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewActivity
        extends AppCompatActivity
{

    private ListView mListView;

    private List<String> mList = new ArrayList<>();
    private ViewHolderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        mListView = (ListView) findViewById(R.id.listview);
        initView();
        initData();
    }

    private void initView() {
        mAdapter = new ViewHolderAdapter(this);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add(" item - " + i);
        }
        mAdapter.setData(mList);
    }
}
