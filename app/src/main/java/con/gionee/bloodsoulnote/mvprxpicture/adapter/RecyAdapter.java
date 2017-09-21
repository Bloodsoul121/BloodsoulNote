package con.gionee.bloodsoulnote.mvprxpicture.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import con.gionee.bloodsoulnote.R;
import con.gionee.bloodsoulnote.mvprxpicture.other.BaseViewHolder;

/**
 * Created by cgz on 17-9-21.
 */

public class RecyAdapter extends CommonBaseAdapter<String> {

    private Activity activity;

    public RecyAdapter(RecyclerView rv, int itemLayoutId) {
        super(rv, itemLayoutId);
        this.activity = (Activity) rv.getContext();
    }

    @Override
    public void bindViewData(BaseViewHolder holder, final String item, int position) {
        final ImageView img = holder.getView(R.id.img);
        img.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(activity).load(item).override(img.getWidth(),img.getHeight()).into(img);
            }
        });
    }

}
