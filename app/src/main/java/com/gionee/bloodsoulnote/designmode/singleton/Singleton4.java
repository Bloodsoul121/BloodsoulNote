package com.gionee.bloodsoulnote.designmode.singleton;

/**
 * 双重检查模式（DCL）
 */

public class Singleton4 {

    private volatile static Singleton4 instance;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    return new Singleton4();
                }
            }
        }
        return instance;
    }

}
