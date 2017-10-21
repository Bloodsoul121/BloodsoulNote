package com.gionee.bloodsoulnote.designmode.proxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.designmode.proxy.dynamicproxy.DynamicProxy;

import java.lang.reflect.Proxy;

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
    }

    /**
     * 静态代理
     */
    private void shop() {
        Cgz cgz = new Cgz();
        ShopProxy proxy = new ShopProxy(cgz);
        proxy.buy();
    }

    /**
     * 动态代理
     */
    private void dynamicShop() {
        Cgz cgz = new Cgz();
        DynamicProxy proxy = new DynamicProxy(cgz);

        ClassLoader loader = proxy.getClass().getClassLoader();

        IShop shop = (IShop) Proxy.newProxyInstance(loader, new Class[]{IShop.class}, proxy);

        shop.buy();
    }

}
