package com.gionee.bloodsoulnote.button;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;

import java.io.IOException;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    public void onclick1(View view) {
        try
        {
            Runtime.getRuntime().exec("su");
            Runtime.getRuntime().exec("reboot");
        }
        catch (IOException e)
        {
            Log.i(getClass().getSimpleName(), "Exception : " + e);
        }
    }

    public void onclick2(View view) {
        try {
            PowerManager pm = (PowerManager)getApplicationContext().getSystemService(Context.POWER_SERVICE);
            pm.reboot(null);
        } catch (Exception e) {
            Log.i(getClass().getSimpleName(), "Exception : " + e);
        }
    }

    // 以上需要系统root权限，adb push xxx.apk system/app安装，一定要在这个目录下才能成为系统应用，来获取更大的操作权限

}
