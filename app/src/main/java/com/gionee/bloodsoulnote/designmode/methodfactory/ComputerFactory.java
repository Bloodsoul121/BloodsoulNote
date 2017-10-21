package com.gionee.bloodsoulnote.designmode.methodfactory;

import com.gionee.bloodsoulnote.designmode.simplefactory.IComputer;

/**
 * Created by cgz on 17-10-21.
 */

public abstract class ComputerFactory {

    public abstract <T extends IComputer>IComputer createComputer(Class<T> tClass);

}
