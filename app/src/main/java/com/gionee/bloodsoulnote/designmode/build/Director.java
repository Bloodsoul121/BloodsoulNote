package com.gionee.bloodsoulnote.designmode.build;

/**
 * Created by cgz on 17-10-21.
 */

public class Director {

    private final Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Computer createComputer(String cpu, String mainboard, String ram) {
        this.builder.buildCpu(cpu);
        this.builder.buildMainboard(mainboard);
        this.builder.buildRam(ram);
        return builder.create();
    }

}
