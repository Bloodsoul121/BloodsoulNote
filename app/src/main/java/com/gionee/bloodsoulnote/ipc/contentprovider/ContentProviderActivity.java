package com.gionee.bloodsoulnote.ipc.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;

import static com.gionee.bloodsoulnote.ipc.contentprovider.GameProvider.GAME_CONTENT_URI;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    public void insert(View view) {
        // 插入
        ContentValues values = new ContentValues();
        values.put("_id", 2);
        values.put("name", "cgz");
        values.put("describe", "zhuai");
        getContentResolver().insert(GAME_CONTENT_URI, values);

        // 查询
        Cursor cursor = getContentResolver().query(GAME_CONTENT_URI, new String[]{"name", "describe"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String describe = cursor.getString(1);
                Log.i("ContentProviderActivity", name + ", " + describe);
            }
            cursor.close();
        }

    }
}
