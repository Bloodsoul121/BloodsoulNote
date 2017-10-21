package com.gionee.bloodsoulnote.designmode.observer;

/**
 * Created by cgz on 17-10-21.
 */

public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notify(String message);

}
