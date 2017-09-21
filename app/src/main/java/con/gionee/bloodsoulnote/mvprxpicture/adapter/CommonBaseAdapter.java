package con.gionee.bloodsoulnote.mvprxpicture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import con.gionee.bloodsoulnote.mvprxpicture.other.BaseViewHolder;

/**
 * Created by cgz on 17-9-21.
 */

public abstract class CommonBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> data = new ArrayList<>();

    private final Context context;
    private final int itemLayoutId;
    private onRecyclerItemClickerListener mListener;

    public CommonBaseAdapter(RecyclerView rv, int itemLayoutId) {
        this.context = rv.getContext();
        this.itemLayoutId = itemLayoutId;
    }

    public void setData(List<T> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     *  增加点击监听
     */
    public void setItemListener(onRecyclerItemClickerListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里不能使用两个参数的方法inflate（itemLayoutId，null）会导致item布局填充不满全屏
        View view = LayoutInflater.from(context).inflate(itemLayoutId, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        holder.setIsRecyclable(true); // 是否复用
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        bindViewData(holder, data.get(position), position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null && v != null) {
                    mListener.onRecyclerItemClick(v, data.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public interface onRecyclerItemClickerListener {
        void onRecyclerItemClick(View view, Object data, int position);
    }

    public abstract void bindViewData(BaseViewHolder holder, T item, int position);
}
