package com.gionee.bloodsoulnote.ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;

public class MessengerActivity extends AppCompatActivity {

    private Messenger sMessenger;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.FROM_SERVICE:
                    Log.i("bloodosul", "接收服务端信息 ---> " + msg.getData().getString("rep"));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }

    public void startMessengerService(View view) {
        Intent service = new Intent(this, MessengerService.class);
        bindService(service, conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("bloodosul", "onServiceConnected");
            sMessenger = new Messenger(service);

            Message msg = Message.obtain();
            msg.what = MessengerService.FROM_CLIENT;

            msg.replyTo = new Messenger(mHandler);

            Bundle bundle = new Bundle();
            bundle.putString("msg", "我是client, 收到请回复");
            msg.setData(bundle);

            try {
                sMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
