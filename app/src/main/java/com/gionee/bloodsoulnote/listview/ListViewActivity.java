package com.gionee.bloodsoulnote.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.listview.customlistview.CustomListViewActivity;
import com.gionee.bloodsoulnote.listview.testlistview.TestListViewActivity;
import com.gionee.bloodsoulnote.listview.testlistview.ViewHolderAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity
        extends AppCompatActivity
{

    private ListView mListView;

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = (ListView) findViewById(R.id.listview);
        initData();
        initView();
    }

    private void initView() {
        ViewHolderAdapter adapter = new ViewHolderAdapter(this);
        adapter.setData(mList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(ListViewActivity.this, TestListViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ListViewActivity.this, CustomListViewActivity.class));
                        break;
                }
            }
        });
    }

    private void initData() {
        mList.add(" ListView");
        mList.add(" item - 测试普通的 listview 功能");
        mList.add(" item - 可以上下拉伸");
        mList.add(" item - ");
    }
}
