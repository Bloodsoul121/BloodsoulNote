package com.gionee.bloodsoulnote.androidview.editview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.gionee.bloodsoulnote.R;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTextViewActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_view);

        mEditText = (EditText) findViewById(R.id.edittext);
    }

    public void clickBtn1(View view) {
        int randomId = 1 + new Random().nextInt(5); //取得随机数randomId 范围在[1,6)之间
        try {
            //利用反射机制:根据随机产生的1至5的整数从R.drawable类中获得相应资源ID（静态变量）的Field对象
            Field field = R.drawable.class.getDeclaredField("face" + randomId); //取得图片的名称+1个随机数
            //获得资源ID的值，也就是静态变量的值
            int resourceId = Integer.parseInt(field.get(null).toString());

            /*
             * 在android重要要显示图片信息，必须使用Bitmap位图的对象来装载。
             * 查看Android 的BitmapFactory的API文档：Public Methods，这些方法描述了如何讲一些字符串，字节数组转化为字节对象
             */
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);

            // 要让图片替代指定的文字就要用ImageSpan
            ImageSpan imageSpan = new ImageSpan(EditTextViewActivity.this, bitmap);
            // 创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
            SpannableString spannableString = new SpannableString("face");
            // 用ImageSpan对象替换face
            spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 将随机获得的图像追加到EditText控件的最后
            mEditText.append(spannableString);

        } catch (Exception e) {
            // TODO: handle exception
            Log.i("EditTextViewActivity", "Exception --> ");
            e.printStackTrace();
        }
    }

    public void clickBtn2(View view) {
        String str = mEditText.getText().toString();

        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            try {
                matcher.appendReplacement(sb, "[[" + URLEncoder.encode(matcher.group(1), "UTF-8") + "]]");
            } catch(UnsupportedEncodingException e) {

            }
        }
        matcher.appendTail(sb);
        Log.i("EditTextViewActivity", "emojiConvert " + str + " to " + sb.toString() + ", len：" + sb.length());
        Log.i("EditTextViewActivity", "--> " + sb.toString());

        mEditText.setText(sb.toString());
    }

    public void clickBtn3(View view) {
        String str = mEditText.getText().toString();

        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            try {
                matcher.appendReplacement(sb, URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch(UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        matcher.appendTail(sb);
        Log.i("EditTextViewActivity", "emojiRecovery " + str + " to " + sb.toString());
        Log.i("EditTextViewActivity", "--> " + sb.toString());

        mEditText.setText(sb.toString());
    }
}
