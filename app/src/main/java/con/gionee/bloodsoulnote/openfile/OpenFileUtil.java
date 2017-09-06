package con.gionee.bloodsoulnote.openfile;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Locale;

import con.gionee.bloodsoulnote.NoteApplication;

public class OpenFileUtil {
    private static final String GIONEE_FILEMANAGER_PACKAGE_NAME = "com.gionee.filemanager";
    public static final String NOT_SUPPORTED_ACTION = "android.content.Intent.notsupported";
    static {
        setup();
    }
    private static void setup(){
        disableStrictModeFeatures();
    }

    private static void disableStrictModeFeatures() {
        if(AndroidUtils.isAboveSpecifiedVersion(AndroidUtils.N)){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @SuppressLint("DefaultLocale")
    public static Intent openFile(String filePath) {

        File file = new File(filePath);
        if (!file.exists())
            return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf")
                || end.equals("ogg") || end.equals("wav") || end.equals("wma") || end.equals("aiff")) {
            return getAudioFileIntent(filePath);
        } else if (isVideo(end)) {
            return getVideoFileIntent(filePath);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(filePath);
        } else if (end.equals("apk")) {
            return getApkFileIntent(filePath);
        } else if (end.equals("ppt")) {
            return getPptFileIntent(filePath);
        } else if (end.equals("xls") || end.equals("xlsx")) {
            return getExcelFileIntent(filePath);
        } else if (end.equals("doc") || end.equals("docx")) {
            return getWordFileIntent(filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(filePath);
        } else if (end.equals("chm")) {
            return getChmFileIntent(filePath);
        } else if (end.equals("txt")) {
            return getTextFileIntent(filePath, false);
        } else if (end.equals("crt") || end.equals("der")) {
            return getCAcertIntent(filePath);
        } else {
            return getOtherIntent(filePath);
        }
    }

    private static Intent getOtherIntent(String filePath) {
        return getAllIntent(filePath);
    }

    // 提示不支持打开文件
    private static Intent getNotSupportIntent() {
        Intent intent = new Intent();
        intent.setAction(NOT_SUPPORTED_ACTION);
        return intent;
    }

    private static boolean isVideo(String suffix) {
        return suffix.equals("3gp") || suffix.equals("3gpp") || suffix.equals("3g2") || suffix.equals("vob")
                || suffix.equals("wmv") || suffix.equals("qt") || suffix.equals("asx") || suffix.equals("wm")
                || suffix.equals("wvx") || suffix.equals("wmx") || suffix.equals("movie")
                || suffix.equals("mov") || suffix.equals("mng") || suffix.equals("mxu")
                || suffix.equals("fli") || suffix.equals("mp4") || suffix.equals("lsx")
                || suffix.equals("lsf") || suffix.equals("dl") || suffix.equals("rm") || suffix.equals("dv")
                || suffix.equals("mkv") || suffix.equals("avi") || suffix.equals("rmvb")
                || suffix.equals("flv") || suffix.equals("mpe") || suffix.equals("mpeg")
                || suffix.equals("mpg") || suffix.equals("acc") || suffix.equals("flac");
    }

    public static boolean isImageSuffix(String fileName) {
        String end = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg")
                || end.equals("bmp")) {
            return true;
        }
        return false;
    }

    public static void fileToGalleryScan(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            Uri data = Uri.fromFile(file);
            NoteApplication.getInstance().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, data));
        }
    }

    public static Intent getAllIntent(String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    // Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(String param) {
        Intent openInstallAppIntent = getOpenInstallAppIntent(param);
        if (openInstallAppIntent != null) {
            return openInstallAppIntent;
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    public static Intent getCAcertIntent(String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = getUri(filePath, intent);
        intent.setDataAndType(uri, "application/x-x509-user-cert");
        return intent;
    }

    private static Intent getOpenInstallAppIntent(String param) {
        Context context = NoteApplication.getInstance().getApplicationContext();
        FileInfoUtils.FileAPKInfo apkInfo = FileInfoUtils.getAPKInfo(context, param);
        boolean appInstalled = FileInfoUtils.isAppInstalled(context, param);
        if (!appInstalled) {
            return null;
        }
        return context.getPackageManager().getLaunchIntentForPackage(apkInfo.packageName);
    }

    // Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(String param) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    // Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(String param) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    // Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(String param) {
        Uri uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param).build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    // Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(String param) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    public static boolean intentGioneeImageFileOpen(String param) {
        String[] packages = {"com.gionee.gallery"};
        //, "com.gionee.gallery"
        String[] activitys = {"everphoto.activity.GuestPreviewActivity"};
        //, "com.gionee.gallery.app.google.GoogleGalleryActivity"
        for (int i = 0; i < packages.length; i++) {
            try {
                ComponentName componentName = new ComponentName(packages[i], activitys[i]);
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(componentName);
                Uri uri = getUri(param, intent);
                intent.setData(uri);
                intent.setAction(Intent.ACTION_VIEW);
                NoteApplication.getInstance().getApplicationContext().startActivity(intent);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(String param) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    // Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String param) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    // Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(String param) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(param, intent);
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    // Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(String param) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri =  getUri(param, intent);
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    // Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(String param, boolean paramBoolean) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(param);
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = getUri(param, intent);
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    // Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(String param) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(param,intent);
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    public static Intent getGoToFileManagerIntent(String path) {
        Intent intent = new Intent();
        if (isGionee()) {
            intent.setPackage(GIONEE_FILEMANAGER_PACKAGE_NAME);
        }

        Uri uri = Uri.fromFile(new File(path));
        intent.setData(uri);
        intent.putExtra("pkg", getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    public static String getPackageName() {
        return NoteApplication.getInstance().getPackageName();
    }

    public static Uri getUri(String filePath, Intent intent) {
        /*Uri uri;
        if (AndroidUtils.isAboveSpecifiedVersion(AndroidUtils.N)) {
            addPrivacyReadUriPermission(intent);
            uri = FileProvider.getUriForFile(Controller.getInstance().getContext(), "com.android.browser.fileprovider", new File(filePath));
        } else {
            uri = Uri.fromFile(new File(filePath));
        }
        return uri;*/
        return  Uri.fromFile(new File(filePath));
    }

    private static void addPrivacyReadUriPermission(Intent intent) {
        if (AndroidUtils.isAboveSpecifiedVersion(AndroidUtils.N)) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }

    private static final String ANDROID_OS_SYSTEM_PROPERTIES = "android.os.SystemProperties";
    private static final String GIONEE = "GIONEE";
    private static final String GET = "get";
    private static final String PREFIX_ROMVERSION = "gionee rom";
    private static final String KEY_MANUFACTURER = "ro.product.manufacturer";

    /**
     * 判断是不是金立手机
     */
    public static boolean isGionee() {
        try {
            String className = ANDROID_OS_SYSTEM_PROPERTIES;
            String methodName = GET;
            Class<?>[] parameterTypes = new Class<?>[] {String.class, String.class};
            Object[] args = new Object[] {KEY_MANUFACTURER, ""};
            Object roProductManufacturer = getMethod(className, methodName, parameterTypes, args);
            if (GIONEE.toLowerCase(Locale.getDefault()).equals(roProductManufacturer.toString().toLowerCase(Locale.getDefault()))) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private static Object getMethod(String className, String methodName, Class<?>[] parameterTypes, Object[] args) throws Exception {
        Class<?> classes = Class.forName(className);
        Object instance = classes.newInstance();
        Method method = classes.getMethod(methodName, parameterTypes);
        return method.invoke(instance, args);
    }
}
