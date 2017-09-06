package con.gionee.bloodsoulnote.openfile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.util.List;

import con.gionee.bloodsoulnote.NoteApplication;
import con.gionee.bloodsoulnote.R;

public class AndroidUtils {

    private static final String TAG = "AndroidUtils";
    public static final int ICE_CREAM_SANDWICH = 14;
    public static final int ICE_CREAM_SANDWICH_MR1 = 15;
    public static final int JELLY_BEAN = 16;
    public static final int JELLY_BEAN_MR1 = 17;
    public static final int JELLY_BEAN_MR2 = 18;
    public static final int KITKAT = 19;
    public static final int KITKAT_WATCH = 20;
    public static final int LOLLIPOP = 21;
    public static final int LOLLIPOP_MR1 = 22;
    public static final int MARSH_MALLOW = 23;
    public static final int N = 24;

    @SuppressLint("DefaultLocale")
    public static String getVersionName() {
        Context context = NoteApplication.getInstance();
        PackageManager p = context.getPackageManager();
        String clientVersion = null;
        try {
            PackageInfo pi = p.getPackageInfo(context.getPackageName(), 0);
            clientVersion = pi.versionName;
            return clientVersion.toLowerCase().replace(" ", "");
        } catch (NameNotFoundException e) {
            Log.d(TAG, "getVersionName() ");
            e.printStackTrace();
            return context.getString(R.string.version_unknown);
        }
    }

    public static int getInternalRes(String imageName, String type) {
        try {
            Field field = Class.forName("com.android.internal.R$" + type)
                    .getField(imageName);
            return field.getInt(field);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    public static String getDensity(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        return screenHeight + "*" + screenWidth;
    }

    public static int getScreenHeight() {
        Context context = NoteApplication.getInstance();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenHeight = wm.getDefaultDisplay().getHeight();
        return screenHeight;
    }


    public static int getScreenOrientation(Context contex) {
        return contex.getResources().getConfiguration().orientation;
    }

    public static boolean isAboveSpecifiedVersion(int versionCode) {
        return Build.VERSION.SDK_INT >= versionCode;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((px - 0.5) / scale);
    }

    /**
     * 暂时不用，横竖屏一个值不兼容
     */
    private static int mWindowsWidth;
    /**
     * 暂时不用，横竖屏一个值不兼容
     */
    private static int mWindowsHeight;

    public static int getWindowsWidth(Context context) {
        if (mWindowsWidth <= 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            return wm.getDefaultDisplay().getWidth();
        }
        return mWindowsWidth;
    }

    public static int getWindowsHeight(Context context) {
        if (mWindowsHeight <= 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            return wm.getDefaultDisplay().getHeight();
        }
        return mWindowsHeight;
    }

    public static void closeKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void changeKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean isInLockTaskMode(Context context) {
        try {
            ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
            if (isAboveSpecifiedVersion(LOLLIPOP)) {
                return am.isInLockTaskMode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getViewMakeMeasureWidth(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredWidth();
    }

    public static int getViewMakeMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    public static String getBootBrowserSource(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(2);
        if (tasks != null && tasks.size() > 0) {
            ActivityManager.RunningTaskInfo task = tasks.get(0);
            String basePackageName = task.baseActivity == null ? "" : task.baseActivity.getPackageName();
            String baseClassName = task.baseActivity == null ? "" : task.baseActivity.getClassName();
            String topPackageName = task.topActivity == null ? "" : task.topActivity.getPackageName();
            String topClassName = task.topActivity == null ? "" : task.topActivity.getClassName();;

            Log.i("TAG", "getBootBrowserSource base1:" + basePackageName +"/" + baseClassName);
            Log.i("TAG", "getBootBrowserSource top1:" + topPackageName +"/" + topClassName);
            if (!TextUtils.isEmpty(basePackageName)
                    && !TextUtils.isEmpty(topPackageName)
                    && !isBootFromLauncher(basePackageName, topPackageName)) {
//                return basePackageName + "/" + baseClassName;
                return basePackageName;
            }

            if(tasks.size() == 2) {
                task = tasks.get(1);
                basePackageName = task.baseActivity == null ? "" : task.baseActivity.getPackageName();
                baseClassName = task.baseActivity == null ? "" : task.baseActivity.getClassName();
                topPackageName = task.topActivity == null ? "" : task.topActivity.getPackageName();
                topClassName = task.topActivity == null ? "" : task.topActivity.getClassName();
                Log.i("TAG", "getBootBrowserSource base2:" + basePackageName +"/" + baseClassName);
                Log.i("TAG", "getBootBrowserSource top2:" + topPackageName +"/" + topClassName);
                if (isBootFromLauncher(topPackageName, "com.gionee.amisystem")) {
                    return null;
                }

                if (!TextUtils.isEmpty(topPackageName)
                        && !TextUtils.isEmpty(topClassName)) {
//                    return topPackageName + "/" + topClassName;
                    return topPackageName;
                }
            }

        }
        return null;
    }

    private static boolean isBootFromLauncher(String topPackageName, String other) {
        return topPackageName.equals(other);
    }

    public static final String MINITYPE_APK = "application/vnd.android.package-archive";

}
