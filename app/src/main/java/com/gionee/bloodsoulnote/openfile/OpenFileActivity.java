package com.gionee.bloodsoulnote.openfile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class OpenFileActivity extends AppCompatActivity {

    private static final String SD_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String DOWNLOAD_FOLDER = SD_FOLDER + File.separator + Environment.DIRECTORY_DOWNLOADS;
    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gionee.bloodsoulnote.R.layout.activity_open_file);

        mEt = (EditText) findViewById(com.gionee.bloodsoulnote.R.id.et);
    }

    public void openImg(View view) {
        String fileName = mEt.getText().toString();
        String path = getSettingDownloadPath() + File.separator + fileName;
        Intent intent = OpenFileUtil.openFile(path);
        startActivity(intent);
    }

    public static String getSettingDownloadPath() {
        if (isStorageAvaliable()) {
            File file = new File(DOWNLOAD_FOLDER);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    Log.e("TAG", "failed to mkdir " + DOWNLOAD_FOLDER);
                }
            }
        }
        return DOWNLOAD_FOLDER;
    }

    public static boolean isStorageAvaliable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
