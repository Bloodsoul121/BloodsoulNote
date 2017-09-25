package com.gionee.bloodsoulnote.inputbar;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class ShortcutInputView extends LinearLayout {

    private Context mContext;

    private TextView mPasterView;

    private ClipboardManager mClipboardMgr;
    private CallBack mCallBack;

    public ShortcutInputView(Context context) {
        super(context);
        init(context);
    }

    public ShortcutInputView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public ShortcutInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mClipboardMgr = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);

        View view = LayoutInflater.from(mContext).inflate(R.layout.shortcut_input_view_layout, this);
        initShortcutItemView(view);
        initPasteItemView(view);
    }

    private void initShortcutItemView(View view) {
        LinearLayout group = (LinearLayout) view.findViewById(R.id.text_item_parent);
        int count = group.getChildCount() - 1;
        for (int i = 0; i < count; i++) {
            TextView itemView = (TextView) group.getChildAt(i);
            itemView.setOnClickListener(mOnClickListener);
        }
    }

    private void initPasteItemView(View view) {
        mPasterView = (TextView) view.findViewById(R.id.shortcut_paste);
        mPasterView.setOnClickListener(mOnClickListener);
    }

    public void updatePasteItemView() {
        mPasterView.setEnabled(hasTextInClipboard());
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            final int id = v.getId();
            switch (id) {
            case R.id.shortcut_www:
            case R.id.shortcut_slash:
            case R.id.shortcut_point:
            case R.id.shortcut_com:
            case R.id.shortcut_cn:
                inputShortcutKeyword(v);
                break;
            case R.id.shortcut_paste:
                inputClipboardContent();
                break;
            default:
                break;
            }
        }
    };

    private void inputShortcutKeyword(View v) {
        TextView itemView = (TextView) v;
        String keyword = itemView.getText().toString();

        startInputContent(keyword);
    }

    /**
     * 将辅助栏的字符添加到搜索框中去
     * @param keyword
     */
    private void startInputContent(String keyword) {
        if (mCallBack != null) {
            mCallBack.onAddText(keyword);
        }
    }

    private void inputClipboardContent() {
        String content = getTextFromClipboard();
        if (TextUtils.isEmpty(content)) {
            return;
        }
        startInputContent(content);
    }

    private String getTextFromClipboard() {
        String content = "";
        if (mClipboardMgr == null) {
            return content;
        }

        try {
            if (mClipboardMgr.hasPrimaryClip()) {
                content = mClipboardMgr.getPrimaryClip().getItemAt(0).getText()
                        .toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    private boolean hasTextInClipboard() {
        String content = getTextFromClipboard();
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        return true;
    }

    interface CallBack {
        abstract void onAddText(String text);
    }

    public void addOnAddTextListener(CallBack callBack) {
        mCallBack = callBack;
    }

}
