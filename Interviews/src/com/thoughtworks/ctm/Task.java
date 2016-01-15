package com.thoughtworks.ctm;

//All durations in minutes
public class Task {
    private final String taskName;
    private final int duration;

    private static final Task lunchTask = new Task ("Lunch",60);
    private static final Task networkingTask = new Task ("Networking Event",60);
    private static final int LIGHTNING_TASK_DURATION = 5;
    
    
    public Task(String taskName, int duration) {
        this.taskName = taskName;
        this.duration = duration;
    }
    
    //using static factory instead of overloaded constructor, as name is more meaningful
    public static Task newLightningTask(String taskName){
        return new Task(taskName,LIGHTNING_TASK_DURATION);
    }
    
    public int getDuration() {
        return duration;
    }
    
    public static Task getLunchTask(){
        return lunchTask;
    }
    
    public static Task getNetworkingTask(){
        return networkingTask;
    }
    
    @Override
    public String toString(){
        return (taskName + " : " +duration + " minutes");
    }
    
}
