// IGameManager.aidl
package com.gionee.bloodsoulnote.ipc.aidl;

// Declare any non-default types here with import statements

import com.gionee.bloodsoulnote.ipc.aidl.Game;

interface IGameManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void addGame(in Game game);
    List<Game> getGameList();
}
