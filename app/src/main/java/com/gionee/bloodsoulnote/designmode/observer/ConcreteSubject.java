package com.gionee.bloodsoulnote.designmode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgz on 17-10-21.
 */

public class ConcreteSubject implements Subject {

    private List<Observer> mObservers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        mObservers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        mObservers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : mObservers) {
            observer.update(message);
        }
    }
}
