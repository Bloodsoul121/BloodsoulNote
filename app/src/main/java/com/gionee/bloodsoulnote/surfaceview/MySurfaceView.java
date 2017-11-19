package com.gionee.bloodsoulnote.surfaceview;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.surfaceview
 *  @文件名:   MySurfaceView
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/16 22:21
 *  @描述：    TODO
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;

    private Canvas mCanvas;

    private boolean mIsDrawing;

    private Path mPath;
    private Paint mPaint;

    public MySurfaceView(Context context) {
        super(context);
        initView(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        // 开始绘制
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
            draw();
        }
        long end = System.currentTimeMillis();

        // 50-100
        if (end - start < 100) {
            try {
                Thread.sleep(100 - (end - start));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
//                draw();
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
//                draw();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();

            // 画东西
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);

        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    //清理画布
    public  void clear(){
        //路径重置
        mPath.reset();
        //重新锁定，否则不能再次绘画
        draw();
    }
}
