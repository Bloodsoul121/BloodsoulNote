package com.gionee.bloodsoulnote.stepdownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpModel {

    private Platform platform;
    private Call call = null;

    public OkhttpModel() {
        platform = Platform.get();
    }

    public void onStart(String url, String fileName, onStartDownListener onStartDownListener) {
        downLoad(url, fileName, onStartDownListener);
    }

    public void onStop(String fileName, onStartDownListener onStartDownListener) {
        if (call != null) {
            if (!call.isCanceled()) {
                call.cancel();
            }
            File f = new File(fileName);
            if (f != null && f.exists()) {
                f.delete();
                sendLoadProgressCallback(onStartDownListener,0,100);
                sendInfoCallback(onStartDownListener,"停止下载");
            }
        } else {
            sendLoadProgressCallback(onStartDownListener,0,100);
            sendInfoCallback(onStartDownListener,"call出现错误");
        }

    }

    private void downLoad(String url, final String fileName, final onStartDownListener onStartDownListener) {
        final OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();
        call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendInfoCallback(onStartDownListener, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendInfoCallback(onStartDownListener, "已经开始下载");
                InputStream is = response.body().byteStream();
                long total = response.body().contentLength();
                int current = 0;
                byte[] by = new byte[1024 * 8];
                FileOutputStream fos = new FileOutputStream(new File(fileName));
                int len;
                while ((len = is.read(by)) != -1) {
                    fos.write(by, 0, len);
                    current += len;
                    sendLoadProgressCallback(onStartDownListener, current, total);
                }
                is.close();
                fos.close();
                sendInfoCallback(onStartDownListener, "下载完成");
            }
        });
    }

    private void sendLoadProgressCallback(final onStartDownListener onStartDownListener, final int current, final long total) {
        if (onStartDownListener == null) return;
        platform.execute(new Runnable() {
            @Override
            public void run() {
                onStartDownListener.onLoading(current, (int) total);
            }
        });
    }

    private void sendInfoCallback(final onStartDownListener onStartDownListener, final String message) {
        if (onStartDownListener == null) return;
        platform.execute(new Runnable() {
            @Override
            public void run() {
                onStartDownListener.onFailed(message);
            }
        });
    }

    interface onStartDownListener {

        void onFailed(String message);

        void onLoading(int current, int total);
    }

}
