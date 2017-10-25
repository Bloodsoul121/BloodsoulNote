package com.gionee.bloodsoulnote.ipc.socket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.openfile.AndroidUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientActivity extends Activity {

    private Button bt_send;

    private EditText et_receive;

    private TextView tv_message;

    private LinearLayout mInputLine;

    private Socket mClientSocket;

    private PrintWriter mPrintWriter;

    private Intent mServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);

        initView();
        initService();
    }

    private void initView() {
        et_receive = (EditText) findViewById(R.id.et_receive);
        bt_send = (Button) findViewById(R.id.bt_send);
        tv_message = (TextView) findViewById(R.id.tv_message);
        mInputLine = (LinearLayout) findViewById(R.id.inputline);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = et_receive.getText().toString();
                //向服务器发送信息
                if(!TextUtils.isEmpty(msg) && null != mPrintWriter) {
                    mPrintWriter.println(msg);
                    tv_message.setText(tv_message.getText() + "\n" + "client ： " + msg);
                    et_receive.setText("");
                }
            }
        });

//        mInputLine.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
    }

    private void initService() {
        mServer = new Intent(this, SocketService.class);
        startService(mServer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectSocketServer();
            }
        }).start();
    }

    private void connectSocketServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", SocketService.PORT);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            } catch (IOException e) {
                e.printStackTrace();
                SystemClock.sleep(1000);
            }
        }

        // 接收服务端消息
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
            while (!isFinishing()) {
                final String s = br.readLine();
                if (!TextUtils.isEmpty(s)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_message.setText(tv_message.getText() + "\n" + "server： " + s);
                        }
                    });
                }
            }

            mClientSocket.close();
            socket.close();
            br.close();
            mPrintWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean mLastKeybordState = false; // true: show; false:hide
    private static final int KEYBOARD_STATE_SHOW = 1;
    private static final int KEYBOARD_STATE_HIDE = 0;

    private OnGlobalLayoutListener mOnGlobalLayoutListener = new OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

            int screenHeight = AndroidUtils.getScreenHeight();
            int heightDiff = screenHeight - (rect.bottom - rect.top);
            int visibleFrameHeight = (rect.bottom - rect.top);
            boolean visible = Math.abs(heightDiff) > screenHeight / 3;
            if (mLastKeybordState != visible) {
                mLastKeybordState = visible;
//                onKeyboardStateChanged(visible ? KEYBOARD_STATE_SHOW : KEYBOARD_STATE_HIDE, visibleFrameHeight);
                showShortcutInputView(visibleFrameHeight);
            }
        }

        private void onKeyboardStateChanged(int state, int visibleFrameHeight) {
            switch (state) {
                case KEYBOARD_STATE_SHOW:
                    showShortcutInputView(visibleFrameHeight);
                    break;
                case KEYBOARD_STATE_HIDE:
                    hideShortcutInputView();
                    break;
                default:
                    break;
            }
        }
    };

    public void showShortcutInputView(int visibleFrameHeight) {
        mInputLine.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mInputLine.getLayoutParams());
        int marginTop = calculateAdjustHeight(visibleFrameHeight);
        params.setMargins(0, marginTop, 0, 0);
        mInputLine.setLayoutParams(params);
    }

    private int calculateAdjustHeight(int visibleFrameHeight) {
        int topViewHight = getResources().getDimensionPixelOffset(R.dimen.topbar_height);
        int shortInputViewHeight = getResources().getDimensionPixelOffset(R.dimen.shortcut_input_view_height);
        int marginTopValue = visibleFrameHeight - topViewHight - shortInputViewHeight;
        return marginTopValue;
    }

    public void hideShortcutInputView() {
        mInputLine.setVisibility(View.GONE);
    }

    private void removeGlobalOnLayoutListener() {
        if (AndroidUtils.isAboveSpecifiedVersion(AndroidUtils.JELLY_BEAN)) {
            mInputLine.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
        } else {
            mInputLine.getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeGlobalOnLayoutListener();
        stopService(mServer);
    }
}
