package com.gionee.bloodsoulnote.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2Activity extends AppCompatActivity {

    private static final String TAG = "Retrofit2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
    }

    public void onclick(View view) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://xj3gosapi.3gtest2.gionee.com/comment/index/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUserBiz iUserBiz = retrofit.create(IUserBiz.class);
        Call<User> call = getCall(iUserBiz);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(TAG, "" + Thread.currentThread().getName());
                Log.i(TAG, "onResponse : " + response.body().getData() + response.body().getMsg() + response.body().isSuccess());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i(TAG, "onFailure : " + t.toString());
            }
        });
    }

    private Call<User> getCall(IUserBiz iUserBiz) {
//        Call<User> call = iUserBiz.getUsers();
//        Call<User> call = iUserBiz.getUsers2("9dac6633be895da152187b9c1a5c0042", "topic", "dc13c4012caeb48a6b05a961397d3318");
        Call<User> call = iUserBiz.getUsers3("9dac6633be895da152187b9c1a5c0042", "topic", "dc13c4012caeb48a6b05a961397d3318");
        return call;
    }

    private Call<User> getCall2(IUserBiz iUserBiz) {
        RequestBody.create(null, "hahaha");
        return null;
    }

}
