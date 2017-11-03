package com.gionee.bloodsoulnote.threadpool;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by cgz on 17-11-3.
 */

public class ThreadPoolUtil {

    private static final String TAG = "ThreadPoolUtil";

    //设置核心池大小
    private int corePoolSize = 4;

    //设置线程池最大能接受多少线程
    private int maximumPoolSize = 10;

    //当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
    private long keepActiveTime = 200;

    //设置时间单位，秒
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    //设置线程池缓存队列的排队策略为FIFO，并且指定缓存队列大小为5
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);

    private BlockingQueue<Runnable> linkedQueue = new LinkedBlockingQueue<Runnable>();

    private final ThreadPoolExecutor mThreadPoolExecutor;

    public ExecutorService fixedExecutorService = Executors.newFixedThreadPool(5);
    public ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
    public ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();

    private ThreadPoolUtil(){
        mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepActiveTime, timeUnit, workQueue);
    }

    public static ThreadPoolUtil getInstance() {
        return ThreadPoolUtilHolder.sThreadPoolUtil;
    }

    private static class ThreadPoolUtilHolder {
        private static ThreadPoolUtil sThreadPoolUtil = new ThreadPoolUtil();
    }

    public void execute() {

        //往线程池中循环提交线程
        for (int i = 0; i < 15; i++) {
            //创建线程类对象
            MyTask myTask = new MyTask(i);
            //开启线程
            mThreadPoolExecutor.execute(myTask);
            //获取线程池中线程的相应参数
            Log.i(TAG, "线程池中线程数目：" + mThreadPoolExecutor.getPoolSize() + "，队列中等待执行的任务数目："
                    + mThreadPoolExecutor.getQueue().size() + "，已执行完的任务数目：" + mThreadPoolExecutor.getCompletedTaskCount());
        }

    }

    public void recycle() {
        if (!mThreadPoolExecutor.isShutdown()) {
            mThreadPoolExecutor.shutdown();
        }
    }

    private static class MyTask implements Runnable {

        private final int num;

        public MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            Log.i(TAG, "正在执行task " + num );
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "task " + num + " 执行完毕");
        }
    }

}
