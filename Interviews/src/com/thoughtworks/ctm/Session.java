package com.thoughtworks.ctm;

//Session is a list of task, and contains a duration
// Should we use has-a reln instead?
public class Session extends Tasks{
    private final int duration;
    private static final Session lunchSession = new Session (Task.getLunchTask());
    private static final Session networkingSession = new Session (Task.getNetworkingTask());
    
    public Session(int duration) {
        this.duration = duration;
        
    }
    //Create a session with a single task
    public Session(Task t) {
        this.duration = t.getDuration();
        taskList.add(t);        
    }
    
    public static Session getLunchSession(){
        return lunchSession;
    }
    
    public static Session getNetworkingSession(){
        return networkingSession;
    }
    //Keeping an integer provides more flexibility and reuse in place of enum 
    /*static enum SessionType {
        MORNING {
            @Override
            int getDuration(){
                return 3;
            }
        },EVENING {
            @Override
            int getDuration(){
                return 4;
            }
        };
        abstract int getDuration();
    }*/
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        for (Task i:taskList){
            sb.append(i.toString());
        }
        return sb.toString();
    }
}
