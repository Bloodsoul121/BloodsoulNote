package com.gionee.bloodsoulnote.viewpagerfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TeacherStudentActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mVp;
    private List<StudentFragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gionee.bloodsoulnote.R.layout.activity_teacher_student);

        mTabLayout = (TabLayout) findViewById(com.gionee.bloodsoulnote.R.id.tablayout_student_activity);
        mVp = (ViewPager) findViewById(com.gionee.bloodsoulnote.R.id.vp_student_activity);
        Toolbar toolbar = (Toolbar) findViewById(com.gionee.bloodsoulnote.R.id.toolbar_student_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getDataBean(toolbar);
    }

    private void getDataBean(Toolbar toolbar) {
        toolbar.setTitle("班级学生");
        init();
    }

    private void init() {
        for (int i = 0; i < 4; i++) {
            StudentBean bean = new StudentBean();
            bean.setName("学生" + (i + 1));
            bean.setSubject1("Subject1");
            bean.setSubject2("Subject2");
            bean.setSubject3("Subject3");
            bean.setSubject4("Subject4");
            list.add(new StudentFragment(bean));
        }
        TeacherStudentVPAdapter vp_adapter = new TeacherStudentVPAdapter(getSupportFragmentManager());
        mVp.setAdapter(vp_adapter);

        mTabLayout.setupWithViewPager(mVp);
        mTabLayout.getTabAt(0).setText("Subject1");
        mTabLayout.getTabAt(1).setText("Subject2");
        mTabLayout.getTabAt(2).setText("Subject3");
        mTabLayout.getTabAt(3).setText("Subject4");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class TeacherStudentVPAdapter extends FragmentPagerAdapter {

        public TeacherStudentVPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

    }

}
