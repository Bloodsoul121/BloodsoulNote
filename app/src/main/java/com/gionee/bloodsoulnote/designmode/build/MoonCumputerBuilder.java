package com.gionee.bloodsoulnote.designmode.build;

/**
 * Created by cgz on 17-10-21.
 */

public class MoonCumputerBuilder extends Builder {

    private Computer mComputer = new Computer();

    @Override
    public void buildCpu(String cpu) {
        mComputer.setCpu(cpu);
    }

    @Override
    public void buildMainboard(String mainboard) {
        mComputer.setMainboard(mainboard);
    }

    @Override
    public void buildRam(String ram) {
        mComputer.setRam(ram);
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
