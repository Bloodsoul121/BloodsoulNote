package con.gionee.bloodsoulnote.mvprxpicture.contract;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import con.gionee.bloodsoulnote.mvprxpicture.presenter.MainPresenter;

/**
 * Created by cgz on 17-9-21.
 */

public interface MainContract {

    public interface Presenter {

        void getRecyclerView(RecyclerView recyclerView);

        void load(Activity activity);

        void recycle();
    }

    public interface View extends BaseView<MainPresenter> {
        void showProgress();
        void closeProgress();
        void showInfo(String info);
    }

    public interface Model {

        void loadPic(Activity activity, onLoadPicListener onLoadPicListener);

        interface onLoadPicListener{
            void onLoadBefore();
            void onLoadAfter();
            void onResult(List<String> picPathList);
            void onFailed(String info);
        }
    }

}
