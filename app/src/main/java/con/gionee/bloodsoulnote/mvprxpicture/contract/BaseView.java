package con.gionee.bloodsoulnote.mvprxpicture.contract;

/**
 * Created by cgz on 17-9-21.
 */

public interface BaseView<T> {

    void bindPresenter(T presenter);

    void initView();
}
