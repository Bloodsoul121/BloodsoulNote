package com.gionee.bloodsoulnote.button;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gionee.ad.sdkbase.common.utils.UIUtils;
import com.gionee.bloodsoulnote.R;
import com.google.gson.Gson;

import java.io.IOException;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    public void onclick1(View view) {
        try
        {
            Runtime.getRuntime().exec("su");
            Runtime.getRuntime().exec("reboot");
        }
        catch (IOException e)
        {
            Log.i(getClass().getSimpleName(), "Exception : " + e);
        }
    }

    public void onclick2(View view) {
        try {
            PowerManager pm = (PowerManager)getApplicationContext().getSystemService(Context.POWER_SERVICE);
            pm.reboot(null);
        } catch (Exception e) {
            Log.i(getClass().getSimpleName(), "Exception : " + e);
        }
    }

    // 以上需要系统root权限，adb push xxx.apk system/app安装，一定要在这个目录下才能成为系统应用，来获取更大的操作权限

    public void onclick3(View view) {
        BrowserExposure.Builder builder = new BrowserExposure.Builder();
        BrowserExposure exposure = builder.setDisposeRepeat(false).build();
        exposure.record();
    }

    public void onclick4(View view) {
        Son son = new Son();
        son.show();
        Son2 son2 = new Son2();
        son2.show();
    }

    public void onclick5(View view) {
        Log.i("ButtonActivity", "time : " + System.currentTimeMillis());

        AlertDialog dialog = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            dialog = new AlertDialog.Builder(this)
                    .setView(LayoutInflater.from(this).inflate(R.layout.dialog_test, null))
                    .create();
            dialog.show();
        }
    }

    public void clickBtn6(View view) {
        showAlertCardDialog(this);
    }

    private boolean showAlertCardDialog(Context context) {
        final AlertDialog alertDialog = createDialog(context);
        LinearLayout cartDialogView = new LinearLayout(context);
        cartDialogView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 280));
        cartDialogView.setOrientation(LinearLayout.VERTICAL);
        cartDialogView.setGravity(Gravity.CENTER);

        Button button= new Button(context);
        button.setText("222");
        cartDialogView.addView(button);
        cartDialogView.setBackgroundResource(R.drawable.dialog_bg2222); //设置.9的背景图片
        alertDialog.show();
        setContentViewToDialog(cartDialogView, alertDialog, Gravity.CENTER, 50, 280);
        return true;
    }

    private void setContentViewToDialog(View dialogView,AlertDialog alertDialog, int gravity, int margin, int dialogHeight) {
        Window window = alertDialog.getWindow();
        window.setGravity(gravity);// 底部出现
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = window.getWindowManager().getDefaultDisplay().getWidth() - UIUtils.dip2px(this, margin);
        layoutParams.height = UIUtils.dip2px(this, dialogHeight);
        alertDialog.setContentView(dialogView, layoutParams);
    }

    private AlertDialog createDialog(Context context) {
        if (!(context instanceof Activity)||((Activity) context).isFinishing()) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public void clickBtn7(View view) {
        Gson gson = new Gson();
        String json = " {\"tips\":{\n" +
                "            \"1\":\"弹指一挥，不吐不快…\",\n" +
                "            \"2\":\"理越辨越明，道越论越清…\",\n" +
                "            \"3\":\"暂无评论，快抢沙发…\"\n" +
                "        }}";
        TipFa tipFa = gson.fromJson(json, TipFa.class);
        Log.i("bloodsoul", "tips --> " + tipFa.toString());
    }
}
