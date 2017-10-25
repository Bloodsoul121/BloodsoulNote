package com.gionee.bloodsoulnote.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;

import java.util.List;

public class AidlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    public void startAidlService(View view) {
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IGameManager gameManager = IGameManager.Stub.asInterface(service);

            Game game = new Game("aaa", "bbb");

            try {
                gameManager.addGame(game);

                List<Game> gameList = gameManager.getGameList();
                for (Game gm : gameList) {
                    Log.i("AidlActivity", gm.gameName + ", " + gm.gameDescribe);
                }

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
