package com.gionee.bloodsoulnote.designmode.simplefactory;

/**
 * Created by cgz on 17-10-21.
 */

public class ComputerFactory {

    public static IComputer createComputer(String type) {
        IComputer computer = null;
        switch (type) {
            case "lenovo":
                computer = new LenovoComputer();
                break;
            case "hp":
                computer = new HpComputer();
                break;
            case "asus":
                computer = new AsusComputer();
                break;
        }
        return computer;
    }

}
