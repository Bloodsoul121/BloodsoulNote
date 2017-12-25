package com.gionee.bloodsoulnote.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class UtilActivity extends AppCompatActivity {

    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);

        mContent = (TextView) findViewById(R.id.content);

    }

    public void clickBtn1(View view) {
        String str = mContent.getText().toString();
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        mContent.setText(sb.toString().trim());
    }

    public void clickBtn2(View view) {
        String hexStr = mContent.getText().toString().trim();
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        mContent.setText(new String(bytes));
    }

    public void clickBtn3(View view) {
        String str = mContent.getText().toString().trim(); // 不能超过int的取值范围
        long result = Integer.parseInt("6E74", 16);
        mContent.setText(String.valueOf(result));
    }
}
