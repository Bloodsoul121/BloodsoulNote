package com.gionee.bloodsoulnote.mvprxpicture.presenter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import com.gionee.bloodsoulnote.mvprxpicture.adapter.CommonBaseAdapter;
import com.gionee.bloodsoulnote.mvprxpicture.contract.MainContract;
import com.gionee.bloodsoulnote.mvprxpicture.other.RVItemDecoration;
import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.mvprxpicture.adapter.RecyAdapter;
import com.gionee.bloodsoulnote.mvprxpicture.model.MainModel;

/**
 * Created by cgz on 17-9-21.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainModel model;
    private MainContract.View view;
    private RecyAdapter mAdapter;

    private MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.bindPresenter(this);
        this.view.initView();
        this.model = new MainModel();
    }

    public static MainPresenter newInstance(MainContract.View view) {
        return new MainPresenter(view);
    }

    @Override
    public void getRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addItemDecoration(new RVItemDecoration(16));

        mAdapter = new RecyAdapter(recyclerView, R.layout.recy_item_layout);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setItemListener(new CommonBaseAdapter.onRecyclerItemClickerListener() {
            @Override
            public void onRecyclerItemClick(View view, Object data, int position) {
                if (data != null) {
                    String picPath = (String) data;
                    // // TODO: 17-9-21
                }
            }
        });
    }

    @Override
    public void load(Activity activity) {
        if (view == null) {
            return;
        }
        this.model.loadPic(activity, new MainContract.Model.onLoadPicListener() {
            @Override
            public void onLoadBefore() {
                view.showProgress();
            }

            @Override
            public void onLoadAfter() {
                view.closeProgress();
            }

            @Override
            public void onResult(List<String> picPathList) {
                Log.i("getpicture path", picPathList.toString());
                if (mAdapter != null) {
                    mAdapter.setData(picPathList);
                }
            }

            @Override
            public void onFailed(String info) {
                view.showInfo(info);
            }
        });


    }

    @Override
    public void recycle() {
        if (model != null) model = null;
        if (view != null) view = null;
    }
}
