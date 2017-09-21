package con.gionee.bloodsoulnote.mvprxpicture.other;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cgz on 17-9-21.
 */

public class RVItemDecoration extends RecyclerView.ItemDecoration {

    private int decoration;

    public RVItemDecoration(int decoration) {
        super();
        this.decoration = decoration;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = decoration;
        outRect.right = decoration;
        outRect.top = decoration;
        outRect.bottom = decoration;
    }
}
