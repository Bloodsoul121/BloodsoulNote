package com.gionee.bloodsoulnote.designmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gionee.bloodsoulnote.R;

import java.util.ArrayList;
import java.util.List;

public class DesignModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_mode);

        init();
    }

    private void init() {
        ListView listView = (ListView) findViewById(R.id.listview);
        List<String> list = new ArrayList<>();
        list.add("设计模式");
        list.add("1 - 单例模式");
        list.add("2 - 建造者模式");
        list.add("3 - 简单工厂模式");
        list.add("4 - 工厂方法模式");
        list.add("5 - 抽象工厂模式");
        list.add("6 - 代理模式");
        list.add("7 - 装饰模式");
        list.add("8 - 外观模式");
        list.add("9 - 享元模式");
        list.add("10 - 观察者模式");
        list.add("11 - 策略模式");
        list.add("12 - 状态模式");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                }
            }
        });
    }
}
