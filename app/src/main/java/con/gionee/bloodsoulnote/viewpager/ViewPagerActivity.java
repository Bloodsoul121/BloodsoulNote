package con.gionee.bloodsoulnote.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import con.gionee.bloodsoulnote.R;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private ViewPager mViewPager2;

    private int[] imgRes = {R.drawable.showimg, R.drawable.showimg, R.drawable.showimg, R.drawable.showimg, R.drawable.showimg};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        initViewPager();
        mViewPager2 = (ViewPager) findViewById(R.id.viewpager2);
        initViewPager2();
    }

    private void initViewPager2() {
        // 设置 Page 间间距
        mViewPager2.setPageMargin(20);
        // 设置缓存的页面数量
        mViewPager2.setOffscreenPageLimit(3);
        MultipleAdapter adapter = new MultipleAdapter();
        mViewPager2.setAdapter(adapter);
        // 第一个参数, true 为 倒序, false 为 正序
        mViewPager2.setPageTransformer(true, new AlphaPageTransformer());
    }

    private void initViewPager() {
        // 设置 Page 间间距
        mViewPager.setPageMargin(20);
        // 设置缓存的页面数量
        mViewPager.setOffscreenPageLimit(3);
        MultipleAdapter adapter = new MultipleAdapter();
        mViewPager.setAdapter(adapter);
        // 第一个参数, true 为 倒序, false 为 正序
        mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
    }

    private class MultipleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgRes.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(ViewPagerActivity.this);
            iv.setImageResource(imgRes[position]);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
