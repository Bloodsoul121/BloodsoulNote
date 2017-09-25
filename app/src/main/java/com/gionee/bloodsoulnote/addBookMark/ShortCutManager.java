package com.gionee.bloodsoulnote.addBookMark;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Browser;
import android.text.TextUtils;

import com.gionee.bloodsoulnote.NoteApplication;
import com.gionee.bloodsoulnote.R;

class ShortCutManager {

    private static final String TAG = "ShortCutManager";
    public static final String SHORTCUT_INFORMATION_ACTION = "browser.intent.launcher.short.information";
    public static final String SHORTCUT_SEARCH_ACTION = "browser.intent.launcher.short.search";
    public static final int MAIN_STREAM_TITLE_FLAG = 0x1;
    public static final int MAIN_STREAM_HOT_SITE_FLAG = 0x2;

    private static ShortCutManager sInstance;

    private static final String INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private static final String DUPLICATE = "duplicate";
    private static final int SHORTCUT_TOUCH_ICON = R.mipmap.ic_launcher;

    private Context mContext;
    private boolean mFromInformationEntrance = false;
    private int mReadyFlag = 0;

    public static ShortCutManager getInstance() {
        if (sInstance == null) {
            synchronized (ShortCutManager.class) {
                if (sInstance == null) {
                    sInstance = new ShortCutManager();
                }
            }
        }
        return sInstance;
    }

    private ShortCutManager() {}

    public void init(Context context) {
        mContext = context;
    }

    public void installShortCut(Context context, ShortCutInfo shortCutInfo) {
        create(context, shortCutInfo);
    }

    public static class ShortCutInfo {
        public String mUrl;
        public String mName;
        public Bitmap mIcon;
        public Entrance mEntrance;

        public enum Entrance {
            WEB_BROWSER, WEB_SEARCH, WEB_INFORMATION
        }
    }

    private void create(Context context, ShortCutInfo shortCutInfo) {
        Intent installIntent = new Intent(INSTALL_SHORTCUT);
        Intent shortcutIntent = null;
        switch (shortCutInfo.mEntrance) {
            case WEB_BROWSER:
                shortcutIntent = createUrlShortCut(shortCutInfo.mUrl);
                break;
            case WEB_SEARCH:
                shortcutIntent = createSearchShortCut(context);
                break;
            case WEB_INFORMATION:
                shortcutIntent = createNewInformationShortCut(context);
                break;
            default:

        }

        installIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutInfo.mName);
        if (shortCutInfo.mIcon == null) {
            shortCutInfo.mIcon = BitmapFactory.decodeResource(mContext.getResources(), SHORTCUT_TOUCH_ICON);
        }
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, shortCutInfo.mIcon);
        // Do not allow duplicate items
        installIntent.putExtra(DUPLICATE, false);
        context.sendBroadcast(installIntent);
    }

    private Intent createUrlShortCut(String url) {
        Intent shortcutIntent = new Intent();
        String pkg = NoteApplication.getInstance().getPackageName();
        String className = AddBookMarkActivity.class.getName();
        if (!TextUtils.isEmpty(pkg)) {
            ComponentName componentName = new ComponentName(pkg, className);
            shortcutIntent.setComponent(componentName);
        }
        shortcutIntent.setData(Uri.parse(url));

        long urlHash = url.hashCode();
        long uniqueId = (urlHash << 32) | shortcutIntent.hashCode();
        shortcutIntent.putExtra(Browser.EXTRA_APPLICATION_ID, Long.toString(uniqueId));
        return shortcutIntent;
    }


    private Intent createSearchShortCut(Context context) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(context.getPackageName(), AddBookMarkActivity.class.getName());
        intent.setComponent(componentName);
        intent.setAction(SHORTCUT_SEARCH_ACTION);
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, 0xfffffffe);
        return intent;

    }

    private Intent createNewInformationShortCut(Context context) {

        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(
                context.getPackageName(), "com.android.browser.activity.InformationEntranceActivity");
        intent.setComponent(componentName);
        intent.setAction(SHORTCUT_INFORMATION_ACTION);
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, 0xfffffffd);
        return intent;
    }

}
