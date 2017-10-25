package com.gionee.bloodsoulnote.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by cgz on 17-10-25.
 */

public class AidlService extends Service {

    private CopyOnWriteArrayList<Game> mGamesList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IGameManager.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void addGame(Game game) throws RemoteException {
            mGamesList.add(game);
        }

        @Override
        public List<Game> getGameList() throws RemoteException {
            return mGamesList;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mGamesList.add(new Game("九阴真经ol", "最好玩的武侠网游"));
        mGamesList.add(new Game("大航海时代ol","最好玩的航海网游"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
