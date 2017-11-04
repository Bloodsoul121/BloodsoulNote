package com.gionee.bloodsoulnote.listview.customlistview;

/*
 *  @项目名：  BloodsoulNote 
 *  @包名：    com.gionee.bloodsoulnote.listview.customlistview
 *  @文件名:   CustomListView
 *  @创建者:   Bloodsoul
 *  @创建时间:  2017/11/4 18:08
 *  @描述：    TODO
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class CustomListView extends ListView{
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean overScrollBy(int deltaX,
                                   int deltaY,
                                   int scrollX,
                                   int scrollY,
                                   int scrollRangeX,
                                   int scrollRangeY,
                                   int maxOverScrollX,
                                   int maxOverScrollY,
                                   boolean isTouchEvent)
    {
        return super.overScrollBy(deltaX,
                                  deltaY,
                                  scrollX,
                                  scrollY,
                                  scrollRangeX,
                                  scrollRangeY,
                                  maxOverScrollX,
                                  100,
                                  isTouchEvent);
    }
}
