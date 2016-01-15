package com.thoughtworks.ctm;

public abstract class Schedulers {
    private Schedulers() {
        super();
    }
    public static Scheduler newFullBinPackingScheduler(Tasks taskList){
        return new FullBinPackingScheduler(taskList);
    }
}
