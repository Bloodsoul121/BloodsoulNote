package com.gionee.bloodsoulnote.designmode.simplefactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.designmode.build.Computer;
import com.gionee.bloodsoulnote.designmode.methodfactory.GDComuputerFactory;

public class FactoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
    }

    private void createComputer() {
        GDComuputerFactory factory = new GDComuputerFactory();
        factory.createComputer(HpComputer.class);
    }

}
