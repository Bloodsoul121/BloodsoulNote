package con.gionee.bloodsoulnote.okhttp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import con.gionee.bloodsoulnote.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    private static final String TAG = "OkhttpActivity";

    private TextView mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        mShow = (TextView) findViewById(R.id.show);
    }

    private void show(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mShow.setText(content);
            }
        });
    }

    // get
    public void onClick1(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://github.com/hongyangAndroid").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                show("onFailure ： " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 这里是子线程回调
                show("onResponse ： " +response.body().string());
            }
        });
    }

    // post
    public void onClick2(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("username","张鸿洋");
        FormBody requestBody = builder.build();
        Request request = new Request.Builder().url("https://github.com/hongyangAndroid").post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                show("onFailure ： " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                show("onResponse ： " + response.body().string());
            }
        });
    }

    // 上传文件
    public void onClick3(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File(Environment.getExternalStorageDirectory(), "lala.jpg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"username\""), RequestBody.create(null, "张鸿洋"))
//                .addPart(Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"lala.jpg\""), fileBody)
                .build();

        Request request = new Request.Builder().url("http://192.168.31.242:8888/okHttpServer/user!postFile")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                show("onFailure ： " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                show("onResponse ： " + response.body().string());
            }
        });
    }

}
