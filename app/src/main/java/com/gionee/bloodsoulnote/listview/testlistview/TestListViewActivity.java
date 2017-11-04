package com.gionee.bloodsoulnote.listview.testlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.gionee.bloodsoulnote.R;

import java.util.ArrayList;
import java.util.List;

public class TestListViewActivity
        extends AppCompatActivity
{

    private ListView mListView;

    private List<String> mList = new ArrayList<>();
    private ViewHolderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list_view);

        mListView = (ListView) findViewById(R.id.listview);
        initData();
        initView();
    }

    public void clickBtn1(View view) {
        mListView.setSelection(100);
    }

    public void clickBtn2(View view) {
        mListView.smoothScrollToPosition(100);
    }

    public void clickBtn3(View view) {
        mListView.smoothScrollByOffset(50);
    }

    public void clickBtn4(View view) {
        mListView.smoothScrollBy(200, 200);
    }

    private void initView() {
        mAdapter = new ViewHolderAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setEmptyView(findViewById(R.id.empty_view));
    }

    private void initData() {
        for (int i = 0; i < 150; i++) {
            mList.add(" item - " + i);
        }
    }

    public void clickBtn5(View view) {
        mAdapter.setData(mList);
    }
}
