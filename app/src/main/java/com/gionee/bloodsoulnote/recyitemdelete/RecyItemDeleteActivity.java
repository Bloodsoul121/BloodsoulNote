package com.gionee.bloodsoulnote.recyitemdelete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.gionee.bloodsoulnote.mvprxpicture.adapter.CommonBaseAdapter;
import com.gionee.bloodsoulnote.mvprxpicture.other.BaseViewHolder;

public class RecyItemDeleteActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DBManager mDbManager;
    private RecyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gionee.bloodsoulnote.R.layout.activity_recy_item_delete);

        initView();
        initData();
        bindItemTouch();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(com.gionee.bloodsoulnote.R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mDbManager = new DBManager(this);
        mAdapter = new RecyAdapter(mRecyclerView, com.gionee.bloodsoulnote.R.layout.recy_item_delete_layout, mDbManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void bindItemTouch() {
        RVItemTouchHelper callback = new RVItemTouchHelper(mAdapter);
        callback.setItemViewSwipeEnabled(true);//设置可以侧滑
        callback.setLongPressDragEnabled(true);//不可以拖拽
        ItemTouchHelper rvItemTouchHelper = new ItemTouchHelper(callback);
        rvItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initData() {
        List<Person> data = new ArrayList<>();
        for (int i = 0; i <300; i++) {
            data.add(new Person("name " + i, "money " + i * 123));
        }
        mAdapter.setData(data);
    }

    private class RecyAdapter extends CommonBaseAdapter<Person> implements ItemTouchHelperAdapter{

        private DBManager dbManager;

        public RecyAdapter(RecyclerView rv, int itemLayoutId, DBManager dbManager) {
            super(rv, itemLayoutId);
            this.dbManager = dbManager;
        }

        @Override
        public void bindViewData(BaseViewHolder holder, Person item, int position) {
            TextView tv = holder.getView(com.gionee.bloodsoulnote.R.id.recy_item_tv);
            Log.i("RecyAdapter", "width " + tv.getMeasuredWidth() + ", height " + tv.getMeasuredHeight());
            tv.setText(item.getName() + " ---> " + item.getMoney());
        }

        @Override
        public void onItemMove(int formPosition, int toPosition) {}

        @Override
        public void onItemDelete(int position) {
            if (dbManager !=  null){
                boolean b = dbManager.deleteByPerson(data.get(position));
                if (b){
                    //删除集合中的对应item的数据
                    data.remove(position);
                    //删除RecyclerView中对应item的子View
                    notifyItemRemoved(position);
                }
            }
        }
    }

    // 接口
    public interface ItemTouchHelperAdapter {
        void onItemMove(int formPosition, int toPosition);
        void onItemDelete(int position);
    }

}
