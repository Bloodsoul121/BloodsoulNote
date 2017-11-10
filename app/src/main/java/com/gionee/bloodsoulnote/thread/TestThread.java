package com.gionee.bloodsoulnote.thread;

import android.util.Log;

/**
 * Created by cgz on 17-11-10.
 */

public class TestThread {

    public void test1() {
        Thread t1 = new Thread() {
            public void run() {
                for(int i = 0; i < 20; i++) {
                    Log.i("TestThread", getName() + "...t1");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for(int i = 0; i < 5; i++) {
                    Log.i("TestThread", getName() + "...t2");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                for(int i = 0; i < 5; i++) {
                    Log.i("TestThread", getName() + "...t3");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.setDaemon(true);						//将t1设置为守护线程

        t1.start();
        t2.start();
        t3.start();
    }

    public void test2() {
        final Thread t4 = new Thread() {
            public void run() {
                for(int i = 0; i < 20; i++) {
                    Log.i("TestThread", getName() + "...t4");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t5 = new Thread() {
            public void run() {
                for(int i = 0; i < 20; i++) {
                    if(i == 2) {
                        try {
                            t4.join();						//插队,加入
//                            t4.join(30);						//加入,有固定的时间,过了固定时间,继续交替执行
                            Thread.sleep(10);
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }
                    Log.i("TestThread", getName() + "...t5");

                }
            }
        };

        t4.start();
        t5.start();
    }

}
