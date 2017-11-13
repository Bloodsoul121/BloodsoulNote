package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircularImageView extends AppCompatImageView {

    private Drawable mRoundDrawble;

    public CircularImageView(Context context) {
        this(context, null);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        reSetDrawble();
        super.onDraw(canvas);
    }

    private void reSetDrawble() {
        if (mRoundDrawble == getDrawable()) {
            return;
        }

        if (getDrawable() == null) {
            return;
        }

        Drawable drawable = getDrawable();
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        bitmap = toRoundBitmap(bitmap);
        drawable = new BitmapDrawable(bitmap);
        mRoundDrawble = drawable;
        setImageDrawable(drawable);
    }

    private Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int lenth = 0;
        if (width > height) {
            lenth = height;
        } else {
            lenth = width;
        }
        Bitmap backgroundBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, lenth, lenth);
        canvas.drawCircle(lenth / 2, lenth / 2, lenth / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rect, paint);
        return backgroundBmp;
    }
}
