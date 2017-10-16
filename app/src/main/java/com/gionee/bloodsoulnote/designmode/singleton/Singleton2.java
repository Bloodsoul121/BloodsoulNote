package com.gionee.bloodsoulnote.designmode.singleton;

/**
 * 懒汉式（线程不安全）
 */

public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (instance == null) {
            return new Singleton2();
        }
        return instance;
    }


}
