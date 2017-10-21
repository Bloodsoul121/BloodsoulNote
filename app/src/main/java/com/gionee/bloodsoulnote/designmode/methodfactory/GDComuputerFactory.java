package com.gionee.bloodsoulnote.designmode.methodfactory;

import com.gionee.bloodsoulnote.designmode.simplefactory.IComputer;

/**
 * Created by cgz on 17-10-21.  开发封闭原则
 */

public class GDComuputerFactory extends ComputerFactory {

    @Override
    public  <T extends IComputer> IComputer createComputer(Class<T> tClass) {
        IComputer computer = null;
        String classname = tClass.getName();
        try {
            computer = (IComputer) Class.forName(classname).newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return (T)computer;
    }
}
