package com.gionee.bloodsoulnote.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gionee.bloodsoulnote.R;

public class HorizontalCustomActivity
        extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_custom);
        initView();
    }

    private void initView() {
        ListView lv_one = (ListView) findViewById(R.id.lv_one);
        ListView lv_two = (ListView) findViewById(R.id.lv_two);

        String[] strs1 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs1);
        lv_one.setAdapter(adapter1);

        String[] strs2 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs2);
        lv_two.setAdapter(adapter2);
    }

    private static class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(@NonNull Context context, @LayoutRes int resource)
        {
            super(context, resource);
        }

        public MyAdapter(@NonNull Context context,
                         @LayoutRes int resource,
                         @IdRes int textViewResourceId)
        {
            super(context, resource, textViewResourceId);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }

}
