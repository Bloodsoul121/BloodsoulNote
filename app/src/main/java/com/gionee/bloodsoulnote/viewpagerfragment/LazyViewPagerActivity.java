package com.gionee.bloodsoulnote.viewpagerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.LinePageIndicator;

public class LazyViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gionee.bloodsoulnote.R.layout.activity_lazy_view_pager);

        initView();
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(com.gionee.bloodsoulnote.R.id.viewpager);
        viewPager.setAdapter(new LazyAdapter(getSupportFragmentManager()));

        LinePageIndicator indicator = (LinePageIndicator) findViewById(com.gionee.bloodsoulnote.R.id.indicator);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class LazyAdapter extends FragmentPagerAdapter {

        public LazyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new CategoryFragment();
        }

        @Override
        public int getCount() {
            return 5;
        }

    }

}
