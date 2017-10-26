package com.gionee.bloodsoulnote.viewstub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class ViewStubActivity extends AppCompatActivity {

    private ViewStub mViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);

        mViewStub = (ViewStub) findViewById(R.id.viewstub);
    }

    public void onclick1(View view) {
        TextView tv = (TextView) mViewStub.findViewById(R.id.viewstub_tv);
        Log.i("ViewStubActivity", " --> " + (tv == null));
    }

    public void onclick2(View view) {
        // inflate 只能调用一次，否则，会报错
        mViewStub.inflate();
        TextView tv = (TextView) mViewStub.findViewById(R.id.viewstub_tv);
        Log.i("ViewStubActivity", " --> " + (tv == null));
    }

    public void onclick3(View view) {
        mViewStub.setVisibility(View.VISIBLE);
        TextView tv = (TextView) mViewStub.findViewById(R.id.viewstub_tv);
        Log.i("ViewStubActivity", " --> " + (tv == null));
    }

    public void onclick4(View view) {
        mViewStub.setVisibility(View.GONE);
    }

    public void onclick5(View view) {
        TextView tv = (TextView) findViewById(R.id.viewstub_tv);
        Log.i("ViewStubActivity", " --> " + (tv == null));  // 返回为 false
        if (tv != null) {
            tv.setText("来咬我啊");
        }
    }
}
