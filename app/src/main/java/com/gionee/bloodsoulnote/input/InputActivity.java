package com.gionee.bloodsoulnote.input;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.gionee.bloodsoulnote.R;

public class InputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        int softInputMode = getWindow().getAttributes().softInputMode;
        Log.i("InputActivity", "softInputMode " + softInputMode);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        int softInputMode2 = getWindow().getAttributes().softInputMode;
        Log.i("InputActivity", "softInputMode " + softInputMode2);
    }
}
