package com.gionee.bloodsoulnote.designmode.singleton;

/**
 * 静态内部类单例模式
 */

public class Singleton5 {

    private Singleton5() {}

    public static Singleton5 getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final Singleton5 instance = new Singleton5();

    }

}
