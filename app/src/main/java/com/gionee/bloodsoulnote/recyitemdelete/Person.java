package com.gionee.bloodsoulnote.recyitemdelete;

/**
 * Created by cgz on 17-9-22.
 */

public class Person {

    public Person() {}

    public Person(String name, String money) {
        this.name = name;
        this.money = money;
    }

    private String name;
    private String money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
