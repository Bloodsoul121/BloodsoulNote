package com.gionee.bloodsoulnote.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cgz on 17-10-25.
 */

public class SocketService extends Service {

    public static int PORT = 8868;

    private boolean isServerDestroyed = false;
    private Socket mClient;
    private BufferedReader in;
    private PrintWriter out;

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {

            ServerSocket socket;

            try {
                socket = new ServerSocket(PORT);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }


            while (!isServerDestroyed) {
                try {
                    // 拿到客户端请求, 并且阻塞直到接收到消息
                    mClient = socket.accept();

                    // 接收客户端信息
                    in = new BufferedReader(new InputStreamReader(mClient.getInputStream()));
                    // 向客户端发送消息
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mClient.getOutputStream())), true);
                    out.println("您好, 我是服务端");
                    while (!isServerDestroyed) {
                        String s = in.readLine();
                        Log.i("bloodsoul", "server : 收到客户端发来的信息 --> " + s);
                        if (TextUtils.isEmpty(s)) {
                            // 客户端断开了连接
                            Log.i("bloodsoul", "客户端断开连接");
                            break;
                        }

                        // 从客户端收到的消息加工再发送给客户端
                        String message = "我接收到了你的信息, 如：" + s;
                        out.println(message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServerDestroyed = true;
        Log.i("bloodsoul", "socket server 已经关闭");

        try {
            Log.i("bloodsoul", "资源 close 1");

            if (mClient != null) {
                mClient.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }

            Log.i("bloodsoul", "资源 close");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
