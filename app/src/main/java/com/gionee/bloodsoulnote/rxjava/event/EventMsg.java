package com.gionee.bloodsoulnote.rxjava.event;

/**
 * Created by cgz on 17-11-1.
 */

public abstract class EventMsg {

    public Object data;

    public EventMsg(Object data) {
        this.data = data;
    }

}
