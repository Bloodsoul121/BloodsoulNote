package com.gionee.bloodsoulnote.vieweffects.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.gionee.bloodsoulnote.R;

public class AutoCompleteTextViewActivity
        extends AppCompatActivity
{

    private static final String[] countries = new String[]{"拉拉了", "拉拉囧事化工", "拉拉似乎性", "拉拉护送",
                                                           "拉拉护送2", "拉拉护送3", "美誉拉"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        init();
    }

    private void init() {
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        actv.setAdapter(adapter);
    }
}
