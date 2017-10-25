package com.gionee.bloodsoulnote.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by cgz on 17-10-25.
 */

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    public static final int FROM_CLIENT = 1000;
    public static final int FROM_SERVICE = 1001;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FROM_CLIENT:
                    Toast.makeText(getApplicationContext(), "接到客户端信息 ---> " + msg.getData().getString("msg"), Toast.LENGTH_SHORT).show();

                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain(null, FROM_SERVICE);

                    Bundle bundle = new Bundle();
                    bundle.putString("rep", "我是服务端，已接收到消息");

                    message.setData(bundle);

                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }

}
