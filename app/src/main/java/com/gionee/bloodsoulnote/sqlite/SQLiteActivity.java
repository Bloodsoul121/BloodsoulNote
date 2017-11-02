package com.gionee.bloodsoulnote.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.sqlite.bean.NewsChannelBean;
import com.gionee.bloodsoulnote.sqlite.db.ChannelDBDao;

import java.util.ArrayList;
import java.util.List;

import static com.gionee.bloodsoulnote.sqlite.bean.NewsChannelBean.NEW_ONE;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_POSITION;

public class SQLiteActivity extends AppCompatActivity {

    private ChannelDBDao mChannelDBDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        mChannelDBDao = new ChannelDBDao();
    }

    public void channelInsert(View view) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            NewsChannelBean bean = new NewsChannelBean();
            bean.setIsOpened(true);
            bean.setName("推荐");
            bean.setIsSelected(true);
            bean.setPosition(i);
            bean.setStatus(NEW_ONE);
            mChannelDBDao.insert(bean);
        }
        long end = System.currentTimeMillis();
        Log.i("SQLiteActivity", "insert end --> " + (end - start));  // 1008


        // 开启事务的
        List<NewsChannelBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            NewsChannelBean bean = new NewsChannelBean();
            bean.setIsOpened(true);
            bean.setName("推荐");
            bean.setIsSelected(true);
            bean.setPosition(i);
            bean.setStatus(NEW_ONE);
            list.add(bean);
        }
        long start2 = System.currentTimeMillis();
        mChannelDBDao.insert(list);
        long end2 = System.currentTimeMillis();
        Log.i("SQLiteActivity", "insert end --> " + (end2 - start2));  // 18
    }

    public void channelQueryAsc(View view) {
        long start = System.currentTimeMillis();
        List<NewsChannelBean> channels = mChannelDBDao.query(null, null, null, COLUMN_POSITION + " ASC", null);
        Log.i("SQLiteActivity", "query end --> " + (System.currentTimeMillis() - start));

        if (channels != null) {
            Log.i("SQLiteActivity", "query size : " + channels.size());
        }

        for (NewsChannelBean channel : channels) {
            Log.i("SQLiteActivity", "channel --> " + channel.toString());
        }
    }

    public void channelQueryDesc(View view) {
        List<NewsChannelBean> channels = mChannelDBDao.query(null, null, null, COLUMN_POSITION + " DESC", null);
        for (NewsChannelBean channel : channels) {
            Log.i("SQLiteActivity", "channel --> " + channel.toString());
        }
    }

    public void channelUpdate(View view) {
        NewsChannelBean bean = new NewsChannelBean();
        bean.setIsOpened(true);
        bean.setName("推荐");
        bean.setIsSelected(true);
        bean.setPosition(50);
        bean.setStatus(NEW_ONE);

        String whereClause = "position = ?";
        String[] whereArgs = new String[]{"5"};
        mChannelDBDao.update(bean, whereClause, whereArgs);
    }

    public void channelDelete(View view) {
        mChannelDBDao.delete(null, null);
    }

}
