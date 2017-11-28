package com.gionee.bloodsoulnote.button;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by cgz on 17-11-27.
 */

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("MyButton", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("MyButton", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("MyButton", "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
