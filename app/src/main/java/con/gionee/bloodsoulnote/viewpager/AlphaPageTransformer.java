package con.gionee.bloodsoulnote.viewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class AlphaPageTransformer implements ViewPager.PageTransformer {

    private static float mMinAlpha = 0.3f;

    @Override
    public void transformPage(View view, float position) {

        Log.i("AlphaPageTransformer", "position : " + position);

        // 参数一 当前页面

        // 参数二 页面相对于中间页面的位置参数，根据位置不同。0的时候在中间；-1的时候在左边；1的时候在右边

        if (position < -1) {

            view.setAlpha(mMinAlpha);

        } else if (position <= 1) { // [-1,1]

            if (position < 0) { //[0，-1]

                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                view.setAlpha(factor);

            } else { //[1，0]

                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                view.setAlpha(factor);

            }
        } else { // (1,+Infinity]

            view.setAlpha(mMinAlpha);

        }
    }

}
