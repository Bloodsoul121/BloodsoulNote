package con.gionee.bloodsoulnote.stepdownload;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpManager {

    private static HttpManager sHttpManager;

    private static String mDownloadUrl;

    private static ExecutorService mExecutorService;

    private static File mTargetFile;

    private static ProgressCallback mProgressCallback;

    private static long sTotalLength;

    private HttpManager(String downloadUrl){
        mDownloadUrl = downloadUrl;
        mExecutorService = Executors.newFixedThreadPool(5);
    }

    public static HttpManager getInstance(String downloadUrl) {
        if (sHttpManager == null) {
            synchronized (HttpManager.class) {
                if (sHttpManager == null) {
                    sHttpManager = new HttpManager(downloadUrl);
                }
            }
        }
        return sHttpManager;
    }

    public HttpManager into(File file) {
        if (file != null) {
            mTargetFile = file;
        }
        return sHttpManager;
    }

    public void downLoad(ProgressCallback progressCallback) {
        if (mExecutorService == null) {
            return;
        }
        mProgressCallback = progressCallback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                down();
            }
        }).start();
    }

    private static void down() {
        try {
            URL url = new URL(mDownloadUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            sTotalLength = urlConnection.getContentLength();
            Log.i("sTotalLength", " -- " + sTotalLength);

            //每部分的大小
            final long part = sTotalLength / 5;

            for (int i = 0; i < 5; i++) {
                final long startPosition = i * part;
                final int n = i;
                final long finalTotalLength = sTotalLength;
                final URL finalUrl = url;

                mExecutorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        long endLength = n == 4 ? finalTotalLength : (startPosition + part);
                        downData(finalUrl, mTargetFile, startPosition, endLength);
                    }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Handler handler = new Handler() {
        long current;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 101) {
                current += (long) (int) msg.obj;
                mProgressCallback.onProgressChanged(current, sTotalLength);
                Log.i("current", "--" + current);
            }
        }
    };

    // 回调
    interface ProgressCallback{
        abstract void onProgressChanged(long current, long totalLength);
    }

    private static void downData(URL downLoadUrl, File targetFile, long startPosition, long endPosition) {
        RandomAccessFile raf = null;
        InputStream is = null;
        try {
            URLConnection urlConnection = downLoadUrl.openConnection();
            urlConnection.setAllowUserInteraction(true);
            //设置当前线程下载的起点、终点
            urlConnection.setRequestProperty("Range", "bytes=" + startPosition + "-" + endPosition);
            //  byte[] by = new byte[1024 * 8];
            byte[] by = new byte[1024];

            is = urlConnection.getInputStream();
            raf = new RandomAccessFile(targetFile, "rw");
            int len = 0;
            raf.seek(startPosition);
            while ((len = is.read(by)) != -1) {
                raf.write(by, 0, len);
                // 发消息通知
                sendMessage(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null )
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                is = null;
            }
            try {
                if (raf != null)
                    raf.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                raf = null;
            }
        }
    }

    private static void sendMessage(int len) {
        Message message = handler.obtainMessage();
        message.what = 101;
        message.obj = len;
        handler.sendMessage(message);
    }

}
