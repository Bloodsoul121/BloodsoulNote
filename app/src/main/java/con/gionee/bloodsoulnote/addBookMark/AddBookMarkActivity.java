package con.gionee.bloodsoulnote.addBookMark;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import amigoui.widget.AmigoEditText;
import con.gionee.bloodsoulnote.R;

public class AddBookMarkActivity extends AppCompatActivity {

    public static final Pattern ACCEPTED_URI_SCHEMA = Pattern.compile("(?i)"
            + // switch
            // on
            // case
            // insensitive
            // matching
            "("
            + // begin group for schema
            "(?:http|https|file):\\/\\/" + "|(?:inline|data|about|javascript):"
            + ")" + "(.*)");

    private TextView mSaveBtn;
    private TextView mDropBtn;
    private TextView mTitleTx;
    private TextView mUrlTx;
    private AmigoEditText mBookmarkTitle;
    private EditText mBookmarkAddress;
    private TextView mAddToTx;
    private TextView mAddBookmark;
    private ImageView mAddRate;
    private TextView mAddOnlineApp;
    private TextView mAddHome;
    private TextView mFolderName;
    private RelativeLayout mChoserFolder;
    private RelativeLayout mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_mark);
        initView();
        initData();
        updateItemUIState();
        ShortCutManager.getInstance().init(this);
    }

    private void initView() {
        initTitleBar();
        changeTitleBarHeight();
        mTitleTx = (TextView) findViewById(R.id.title_tx);
        mUrlTx = (TextView) findViewById(R.id.url_tx);
        mBookmarkTitle = (AmigoEditText) findViewById(R.id.bookmark_title);
        mBookmarkAddress = (EditText) findViewById(R.id.bookmark_address);
        mAddToTx = (TextView) findViewById(R.id.add_to_text);
        mAddBookmark = (TextView) findViewById(R.id.add_bookmark);
        mAddRate = (ImageView) findViewById(R.id.add_rate);
        mAddOnlineApp = (TextView) findViewById(R.id.add_online_app);
        mAddHome = (TextView) findViewById(R.id.add_home);
        mFolderName = (TextView) findViewById(R.id.title_text);
        mChoserFolder = (RelativeLayout) findViewById(R.id.layout_chose_folder);
        mAddBookmark.setOnClickListener(mOnClickListener);
        mAddOnlineApp.setOnClickListener(mOnClickListener);
        mAddHome.setOnClickListener(mOnClickListener);
        mChoserFolder.setOnClickListener(mOnClickListener);
    }

    private void changeTitleBarHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTitleBar.getLayoutParams();
        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            params.height = getResources().getDimensionPixelOffset(R.dimen.titilebar_height_landscape);
        } else {
            params.height = getResources().getDimensionPixelOffset(R.dimen.titlebar_double_selection_height);
        }
        mTitleBar.setLayoutParams(params);
    }

    private void initTitleBar() {
        mTitleBar = (RelativeLayout) findViewById(R.id.addbookmark_titlbar);
        mSaveBtn = (TextView) findViewById(R.id.save);
        mSaveBtn.setOnClickListener(mOnClickListener);
        mSaveBtn.setText(R.string.add_bookmark_titlebar_save);

        mDropBtn = (TextView) findViewById(R.id.drop);
        mDropBtn.setOnClickListener(mOnClickListener);
        mDropBtn.setText(R.string.add_bookmark_titlebar_drop);
    }

    private void initData() {
        mBookmarkTitle.setText("title");
        mBookmarkAddress.setText("url");
        mFolderName.setText(getString(R.string.bookmark_parent));
    }

    private void updateItemUIState() {
        mAddBookmark.setSelected(true);
        mAddOnlineApp.setSelected(false);
        mAddHome.setSelected(false);
        setChoseState(true);
    }

//    @NonNull
//    private String getInputUrl() {
//        return mBookmarkAddress.getText().toString();
//    }
//
//    @NonNull
//    private String getInputTitle() {
//        return mBookmarkTitle.getText().toString();
//    }

    private void setChoseState(boolean viewSelcet) {
        if (viewSelcet) {
            mChoserFolder.setVisibility(View.VISIBLE);
            mAddRate.setVisibility(View.VISIBLE);
        } else {
            mChoserFolder.setVisibility(View.INVISIBLE);
            mAddRate.setVisibility(View.INVISIBLE);
        }
    }

    private void refreshContent(Configuration newConfig) {
        Resources res = getResources();
        if (isChangedLanguage(res.getConfiguration(), newConfig)) {
            if (null != mDropBtn) {
                mDropBtn.setText(R.string.add_bookmark_titlebar_drop);
            }
            if (null != mSaveBtn) {
                mSaveBtn.setText(R.string.add_bookmark_titlebar_save);
            }
            if (null != mAddToTx) {
                mAddToTx.setText(R.string.addbookmark_to);
            }
            if (null != mAddBookmark) {
                mAddBookmark.setText(R.string.add_bookmark);
            }
            if (null != mAddOnlineApp) {
                mAddOnlineApp.setText(R.string.add_online_page);
            }
            if (null != mAddHome) {
                mAddHome.setText(R.string.add_home);
            }
            if (null != mTitleTx) {
                mTitleTx.setText(R.string.bookmark_name);
            }
            if (null != mUrlTx) {
                mUrlTx.setText(R.string.bookmark_address);
            }
        }
    }

    public boolean isChangedLanguage(Configuration oldConfiguration,
                                     Configuration newConfiguration) {
        if (oldConfiguration == null || newConfiguration == null) {
            return false;
        }

        if (oldConfiguration.locale == null || newConfiguration.locale == null) {
            return false;
        }

        return !oldConfiguration.locale.equals(newConfiguration.locale);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            final int id = v.getId();
            switch (id) {
                case R.id.layout_chose_folder:
//                    choseFolder();
                    break;
                case R.id.drop:
                    dropBookmark();
                    break;
                case R.id.save:
                    save();
                    break;
                case R.id.add_bookmark:
                    setChoseState(!v.isSelected());
                    handleClickItem(v);
                    break;
                case R.id.add_online_app:
                    handleClickItem(v);
                    break;
                case R.id.add_home:
                    showDeskToast(v);
                    handleClickItem(v);
                    break;
                default:
                    break;
            }
        }
    };

    private void dropBookmark() {
        finish();
    }

    private void handleClickItem(View view) {
        boolean isSelcted = view.isSelected();
        view.setSelected(!isSelcted);
    }

    private void showDeskToast(View view) {
        if (!view.isSelected()) {
            Toast.makeText(this, this.getResources().getString(R.string.add_home_prompt), Toast.LENGTH_SHORT).show();
        }
    }

    private void save() {
        if (checkTitleOrAddressNull()) {
            return;
        }

//        if (isUrlInvalid()) {
//            // let GNValidatorEditText request Focus in order to show prompt
//            // box.
//            mBookmarkAddress.requestFocus();
//            return;
//        }

        if (isUnSelectedItem()) {
            Toast.makeText(this, R.string.bookmark_needs_select, Toast.LENGTH_SHORT).show();
            return;
        }

        doSave();
        showToastMessage();
        finish();
    }

    private boolean checkTitleOrAddressNull() {
        String title = "getInputTitle().trim()";
        String address = "getInputUrl().trim()";
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, R.string.bookmark_needs_title, Toast.LENGTH_SHORT).show();
            return true;
        }

        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, R.string.bookmark_needs_url, Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

//    private boolean isUrlInvalid() {
//        String url = mBookmarkAddress.getText().toString();
//
//        if (isInvalidUrl(url)) {
//            mBookmarkAddress.setHint(getResources().getString(R.string.bookmark_address_invalid));
//            return true;
//        }
//
//        return false;
//    }

    public static boolean isInvalidUrl(String url) {
        //TODO Java中进行正则匹配任意字符的时候底层存在长度限制
        try {
            if (Patterns.WEB_URL.matcher(url).matches() || ACCEPTED_URI_SCHEMA.matcher(url).matches()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean isUnSelectedItem() {
        if (mAddBookmark.isSelected() || mAddHome.isSelected() || mAddOnlineApp.isSelected()) {
            return false;
        }
        return true;
    }

    private void showToastMessage() {
        if (isUnSelectedItem()) {
            return;
        }
        Toast.makeText(this, R.string.bookmark_add_complete, Toast.LENGTH_SHORT).show();
    }

    private void doSave() {
//        saveBookmark();
//        sendBookmarkToNavigation();
        addBookmarkToLauncher();
    }

    // 添加到桌面快捷方式
    private void addBookmarkToLauncher() {
        if (!mAddHome.isSelected()) {
            return;
        }

        String title = "getInputTitle()";
        String inputUrl = "getInputUrl().toLowerCase()";
        String url = appendHttpHead(inputUrl);

        ShortCutManager.ShortCutInfo cutInfo = new ShortCutManager.ShortCutInfo();
        cutInfo.mUrl = url;
        cutInfo.mName = title;
        cutInfo.mEntrance = ShortCutManager.ShortCutInfo.Entrance.WEB_BROWSER;
        ShortCutManager.getInstance().installShortCut(this, cutInfo);
    }

    public static String appendHttpHead(String inUrl) {
        if (TextUtils.isEmpty(inUrl)) {
            return inUrl;
        }
        String urlLowerCase = inUrl.trim().toLowerCase();
        if (!urlLowerCase.startsWith("http://")
                && !urlLowerCase.startsWith("https://")
                && !urlLowerCase.startsWith("file://")
                && !urlLowerCase.startsWith("rtsp://")) {
            inUrl = "http://" + inUrl;
        }
        return inUrl;
    }

}
