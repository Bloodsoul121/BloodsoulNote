package con.gionee.bloodsoulnote.stepdownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;

import con.gionee.bloodsoulnote.R;

public class StepDownloadActivity extends AppCompatActivity {

    private final String url = "http://images.csdn.net/20150817/1.jpg";

    private final String location = Environment.getExternalStorageDirectory() + File.separator + "qq.jpg";

    private ProgressBar mProgressBar;

    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_download);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mImg = (ImageView) findViewById(R.id.img);
    }

    public void download1(View view) {
        File file = new File(location);
        if (file.exists()) {
            file.delete();
        }

        HttpManager.getInstance(url).into(file).downLoad(new HttpManager.ProgressCallback() {
            @Override
            public void onProgressChanged(long current, long totalLength) {
                Log.i("StepDownloadActivity", "current : " + current + " --- " + totalLength);

                int progress = (int) (current / totalLength) * 100;
                mProgressBar.setProgress(progress);

                if (progress == 100) {
                    Bitmap bitmap = BitmapFactory.decodeFile(location);
                    mImg.setImageBitmap(bitmap);
                }
            }
        });
    }

    public void download2(View view) {
        OkhttpModel model = new OkhttpModel();
        model.onStart(url, location, new OkhttpModel.onStartDownListener() {
            @Override
            public void onFailed(String message) {
                Log.i("StepDownloadActivity", "onFailed : " + message);
            }

            @Override
            public void onLoading(int current, int total) {
                Log.i("StepDownloadActivity", "onLoading current : " + current + " ---- " + total);
            }
        });
    }

}
