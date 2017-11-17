package com.gionee.bloodsoulnote.webviewdetail.base;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder
        extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View              mConvertView;

    public ViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static ViewHolder create(Context context, int layoutId, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(itemView);
    }

    public static ViewHolder create(View itemView) {
        return new ViewHolder(itemView);
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        view.setOnClickListener(onClickListener);
    }

    public void setBgRes(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }

    public void setBgColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
    }

    public void setTextLine(int viewId) {
        TextView view = (TextView) getView(viewId);
        view.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public void setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
    }

    public void setMaxLine(int viewId, int maxLine) {
        TextView view = (TextView) getView(viewId);
        view.setMaxLines(maxLine);
    }

}
