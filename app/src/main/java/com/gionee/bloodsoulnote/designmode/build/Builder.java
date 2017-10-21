package com.gionee.bloodsoulnote.designmode.build;

/**
 * Created by cgz on 17-10-21.
 */

public abstract class Builder {

    public abstract void buildCpu(String cpu);

    public abstract void buildMainboard(String mainboard);

    public abstract void buildRam(String ram);

    public abstract Computer create();
}
