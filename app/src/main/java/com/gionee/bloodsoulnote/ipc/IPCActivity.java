package com.gionee.bloodsoulnote.ipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.ipc.aidl.AidlActivity;
import com.gionee.bloodsoulnote.ipc.contentprovider.ContentProviderActivity;
import com.gionee.bloodsoulnote.ipc.messenger.MessengerActivity;
import com.gionee.bloodsoulnote.ipc.socket.SocketClientActivity;

public class IPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
    }

    public void startMessenger(View view) {
        startActivity(new Intent(this, MessengerActivity.class));
    }

    public void startAidl(View view) {
        startActivity(new Intent(this, AidlActivity.class));
    }

    public void startContentProvider(View view) {
        startActivity(new Intent(this, ContentProviderActivity.class));
    }

    public void startSocket(View view) {
        startActivity(new Intent(this, SocketClientActivity.class));
    }
}
