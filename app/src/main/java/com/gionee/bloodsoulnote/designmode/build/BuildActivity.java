package com.gionee.bloodsoulnote.designmode.build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;

public class BuildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);
    }

    private void createComputer() {
        MoonCumputerBuilder builder = new MoonCumputerBuilder();
        Director director = new Director(builder);
        Computer computer = director.createComputer("cpu", "mainboard", "ram");
    }
}
