package com.gionee.bloodsoulnote.toast;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.gionee.bloodsoulnote.R;

import java.util.ArrayList;
import java.util.List;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        // SYSTEM_ALERT_WINDOW 的权限申请问题，需要用户打开
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
        }
    }

    public void toast1(View view) {
        new ToastUtil.Builder(this).setMessage("message").build();
    }

    // build模式
    public void toast2(View view) {
        new ToastUtil.Builder(this)
                .setMessage("123456")
                .setTextColor("#F2F2FF")
                .setBackgroudColor(R.color.colorPrimary)
                .setTextSise(48)
                .setIcon(R.mipmap.ic_launcher)
                .setImageSize(128)
                .setGrivaty(Gravity.CENTER)
                .build();
    }

    public void toast3(View view) {
        DownloadNotifyToast toast = new DownloadNotifyToast(this);
        toast.addToastText(new DownloadNotifyToast.ToastText(R.string.toast_downloaded_File));
        DownloadNotifyToast.ToastText toastText = new DownloadNotifyToast.ToastText(R.string.toast_downloaded_click);
        toastText.setTextColorRes(R.color.toast_click_color);
        toastText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // // TODO: 17-9-23
            }
        });
        toast.addToastText(toastText);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(this,"not granted",Toast.LENGTH_SHORT);
                    finish();
                }
            }
        }
    }

    public void toast4(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.show();

        List<String> list = new ArrayList<>();
        list.add("hahah");
        List<String> subList = list.subList(0, 0);
        Toast.makeText(this, "size " + subList.size(), Toast.LENGTH_SHORT).show();
    }

}
