package con.gionee.bloodsoulnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import con.gionee.bloodsoulnote.addBookMark.AddBookMarkActivity;
import con.gionee.bloodsoulnote.bitmap.ClipPatchActivity;
import con.gionee.bloodsoulnote.halfitem.HalfItemActivity;
import con.gionee.bloodsoulnote.immersive.ImmersiveActivity;
import con.gionee.bloodsoulnote.inputbar.InputBarActivity;
import con.gionee.bloodsoulnote.okhttp.OkhttpActivity;
import con.gionee.bloodsoulnote.openfile.OpenFileActivity;
import con.gionee.bloodsoulnote.recyclerview.RecyclerViewActivity;
import con.gionee.bloodsoulnote.requestpermission.RequestPermissionActivity;
import con.gionee.bloodsoulnote.retrofit2.Retrofit2Activity;
import con.gionee.bloodsoulnote.silentinstall.SilentInstallActivity;
import con.gionee.bloodsoulnote.stepdownload.StepDownloadActivity;
import con.gionee.bloodsoulnote.viewpager.ViewPagerActivity;
import con.gionee.bloodsoulnote.viewpagerfragment.LazyViewPagerActivity;
import con.gionee.bloodsoulnote.viewpagerfragment.TeacherStudentActivity;
import con.gionee.bloodsoulnote.viewpagerfragment.ViewPagerFragmentActivity;

public class MainActivity extends AppCompatActivity {

    private List<String> mDatas = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mDatas.add("num - title - sub");
        mDatas.add("1 - retrofit 2.0 - 用法示例");
        mDatas.add("2 - okhttp3 - 用法示例");
        mDatas.add("3 - item在手机屏幕超过一半");
        mDatas.add("4 - RecyclerView 测试");
        mDatas.add("5 - 静默安装");
        mDatas.add("6 - 沉浸式 和 半透明");
        mDatas.add("7 - 公共控件测试");
        mDatas.add("8 - 添加书签页面");
        mDatas.add("9 - 打开图片");
        mDatas.add("10 - canvas 的 clip 方法演示");
        mDatas.add("11 - Android 6.0 动态申请权限");
        mDatas.add("12 - 输入法辅助栏");
        mDatas.add("13 - 多样的 viewpager 效果");
        mDatas.add("14 - fragment viewpager 分析生命周期");
        mDatas.add("15 - fragment viewpager 学生管理演示");
        mDatas.add("16 - fragment viewpager 懒加载");
        mDatas.add("17 - 多线程分布下载");
    }

    private void clickRecyclerItem(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                startActivity(new Intent(this, Retrofit2Activity.class));
                break;
            case 2:
                startActivity(new Intent(this, OkhttpActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, HalfItemActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, SilentInstallActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, ImmersiveActivity.class));
                break;
            case 7:
                break;
            case 8:
                startActivity(new Intent(this, AddBookMarkActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, OpenFileActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, ClipPatchActivity.class));
                break;
            case 11:
                startActivity(new Intent(this, RequestPermissionActivity.class));
                break;
            case 12:
                startActivity(new Intent(this, InputBarActivity.class));
                break;
            case 13:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case 14:
                startActivity(new Intent(this, ViewPagerFragmentActivity.class));
                break;
            case 15:
                startActivity(new Intent(this, TeacherStudentActivity.class));
                break;
            case 16:
                startActivity(new Intent(this, LazyViewPagerActivity.class));
                break;
            case 17:
                startActivity(new Intent(this, StepDownloadActivity.class));
                break;
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_item, null);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            holder.mTextView.setText(mDatas.get(position));
            final int clickPosition = holder.getAdapterPosition();
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerItem(clickPosition);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mDatas == null) {
                return 0;
            }
            return mDatas.size();
        }

        class RecyclerHolder extends RecyclerView.ViewHolder {

            TextView mTextView;

            public RecyclerHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.tv);
            }
        }

    }

}
