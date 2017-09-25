package com.gionee.bloodsoulnote.mvprxpicture;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.gionee.bloodsoulnote.mvprxpicture.contract.MainContract;
import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.mvprxpicture.other.ToastUtils;
import com.gionee.bloodsoulnote.mvprxpicture.presenter.MainPresenter;

public class MvpRxPictureActivity extends AppCompatActivity implements MainContract.View {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_rx_picture);
        init();
    }

    private void init() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mSwipeRefreshLayout.setColorSchemeColors(Color.RED);
        mSwipeRefreshLayout.setEnabled(false);

        MainPresenter.newInstance(this);

        if (presenter != null) {
            presenter.load(this);
        }
    }

    @Override
    public void bindPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        if (presenter != null && mRecyclerView != null) {
            presenter.getRecyclerView(mRecyclerView);
        }
    }

    @Override
    public void showProgress() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void closeProgress() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showInfo(String info) {
        ToastUtils.show(this, info);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.recycle();
        if (mRecyclerView != null) mRecyclerView.setAdapter(null);
    }
}
