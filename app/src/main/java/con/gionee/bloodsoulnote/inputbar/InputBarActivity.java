package con.gionee.bloodsoulnote.inputbar;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import con.gionee.bloodsoulnote.R;
import con.gionee.bloodsoulnote.openfile.AndroidUtils;

public class InputBarActivity extends Activity {

    private EditText mEt;

    private ShortcutInputView mShortcutInputView;

    private static final int KEYBOARD_STATE_SHOW = 1;
    private static final int KEYBOARD_STATE_HIDE = 0;

    private boolean mLastKeybordState = false; // true: show; false:hide

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bar);
        init();
    }

    private void init() {
        mEt = (EditText) findViewById(R.id.et);
        mShortcutInputView = (ShortcutInputView) findViewById(R.id.shortcut_input_view);
        mShortcutInputView.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
        mShortcutInputView.addOnAddTextListener(new ShortcutInputView.CallBack() {
            @Override
            public void onAddText(String text) {
                setShortcutInputText(text);
            }
        });
    }

    private void setShortcutInputText(String character) {
        if (mEt.hasSelection()) {
            mEt.setText(character);
            mEt.setSelection(character.length());
            return;
        }

        int index = mEt.getSelectionStart();
        Editable edit = mEt.getEditableText();
        if (index < 0 || index >= edit.length()) {
            edit.append(character);
        } else {
            edit.insert(index, character);
        }
    }

    private OnGlobalLayoutListener mOnGlobalLayoutListener = new OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            // 应用的可见高度
            int visibleFrameHeight = (rect.bottom - rect.top);
            // 手机的屏幕高度
            int screenHeight = AndroidUtils.getScreenHeight();
            // 输入法的高度
            int heightDiff = screenHeight - (rect.bottom - rect.top);
            // 如果输入法的高度大于屏幕的1/3, 那就表示输入法弹出来了
            boolean visible = Math.abs(heightDiff) > screenHeight / 3;
            if (mLastKeybordState != visible) {
                mLastKeybordState = visible;
                onKeyboardStateChanged(visible ? KEYBOARD_STATE_SHOW : KEYBOARD_STATE_HIDE, visibleFrameHeight);
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

    private void showShortcutInputView(int visibleFrameHeight) {
        mShortcutInputView.setVisibility(View.VISIBLE);
        mShortcutInputView.updatePasteItemView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mShortcutInputView.getLayoutParams());
        int marginTop = calculateAdjustHeight(visibleFrameHeight);
        params.setMargins(0, marginTop, 0, 0);
        mShortcutInputView.setLayoutParams(params);
    }

    private int calculateAdjustHeight(int visibleFrameHeight) {
        // 父类视图之外，顶部的高度
        int topViewHight = getResources().getDimensionPixelOffset(R.dimen.topbar_height);
        // 辅助栏的高度
        int shortInputViewHeight = getResources().getDimensionPixelOffset(R.dimen.shortcut_input_view_height);
        // 相减，就是辅助栏的top
        return visibleFrameHeight - topViewHight - shortInputViewHeight;
    }

    private void hideShortcutInputView() {
        mShortcutInputView.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeGlobalOnLayoutListener();
    }

    private void removeGlobalOnLayoutListener() {
        if (AndroidUtils.isAboveSpecifiedVersion(AndroidUtils.JELLY_BEAN)) {
            mShortcutInputView.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
        } else {
            mShortcutInputView.getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
        }
    }

}
