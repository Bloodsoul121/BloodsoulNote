package com.gionee.bloodsoulnote.customview3.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.gionee.bloodsoulnote.R;

public class ColorMatrixActivity
        extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener
{

    private static final int MID_VALUE = 100;

    private ImageView mIv;
    private SeekBar mSeekBar1;
    private SeekBar mSeekBar2;
    private SeekBar mSeekBar3;
    private float mHue = 10;
    private float mStauration = 10;
    private float mLum = 10;

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        mIv = (ImageView) findViewById(R.id.img);
        mSeekBar1 = (SeekBar) findViewById(R.id.seekbar1);
        mSeekBar2 = (SeekBar) findViewById(R.id.seekbar2);
        mSeekBar3 = (SeekBar) findViewById(R.id.seekbar3);

        initEvent();

    }

    private void initEvent() {
        mSeekBar1.setOnSeekBarChangeListener(this);
        mSeekBar2.setOnSeekBarChangeListener(this);
        mSeekBar3.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i("ColorMatrixActivity", "progress -- " + progress);
        switch (seekBar.getId()) {
            case R.id.seekbar1:
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seekbar2:
                mStauration = progress * 1.0f / MID_VALUE;
                break;
            case R.id.seekbar3:
                mLum = progress * 1.0f / MID_VALUE;
                break;
        }

        Log.i("ColorMatrixActivity", "-- " + mHue + " - " + mStauration + " - " + mLum);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a9);

//        mIv.setImageBitmap(bitmap);

        mIv.setImageBitmap(handleImageEffect(bitmap, mHue, mStauration, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(),
                                             bm.getHeight(),
                                             Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);

        return bitmap;
    }
}
