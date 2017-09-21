package con.gionee.bloodsoulnote.mvprxpicture.other;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by cgz on 17-9-21.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> sparseArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.sparseArray = new SparseArray<>(8); //一般一个Item 不会超过8种控件
    }

    public <T extends View> T getView(int viewId) {
        View view = sparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            sparseArray.put(viewId, view);
        }
        return (T) view;
    }

}
