package com.gionee.bloodsoulnote.designmode.proxy;

/**
 * Created by cgz on 17-10-21.
 */

public class ShopProxy implements IShop{

    private IShop shop;

    public ShopProxy(IShop shop) {
        this.shop = shop;
    }


    @Override
    public void buy() {
        shop.buy();
    }
}
