package con.gionee.bloodsoulnote.mvprxpicture.model;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import con.gionee.bloodsoulnote.mvprxpicture.contract.MainContract;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cgz on 17-9-21.
 */

public class MainModel implements MainContract.Model {

    @Override
    public void loadPic(final Activity activity, final onLoadPicListener onLoadPicListener) {
        Observable.just("image/jpeg,image/png")
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            return true;
                        } else {
                            onLoadPicListener.onFailed("存储卡不可以用");
                            return false;
                        }
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        onLoadPicListener.onLoadBefore();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, List<String>>() {
                    @Override
                    public List<String> call(String type) {
                        return getPngAndJpgList(activity, type);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        onLoadPicListener.onLoadAfter();
                    }
                })
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> picPathList) {
                        onLoadPicListener.onResult(picPathList);
                    }
                });
    }

    private List<String> getPngAndJpgList(Activity activity, String type) {
        List<String> list = new ArrayList<>();
        Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = activity.getContentResolver();

        //查找类型
        String[] types = type.split(",");
        String selection = MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=?";
        //得到Cursor
        Cursor cursor = contentResolver.query(imgUri, null, selection, types, MediaStore.Images.Media.DATE_MODIFIED);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                // 拿到每个图片的路径
                String picPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                list.add(picPath);
            }
            cursor.close();
        }
        //将集合倒序
        Collections.reverse(list);
        return list;
    }
}
