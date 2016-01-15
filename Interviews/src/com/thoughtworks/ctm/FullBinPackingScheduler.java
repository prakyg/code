package com.thoughtworks.ctm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FullBinPackingScheduler implements Scheduler{
    private final Tasks taskList;
    private final List<Task> t; 
    private final Conference conf;
    
    public FullBinPackingScheduler(Tasks taskList) {
        this.taskList = taskList;
        t = new LinkedList<Task>(taskList.getAllTasks());  //for O(1) deletions
        conf = new Conference();
    }
    @Override
    public Conference schedule() {
        while (!t.isEmpty()) {
             conf.add(scheduleOneTrack());            
        }
        return conf;
    }

    private Track scheduleOneTrack() {
        Session s1 = scheduleOneSession(180);
        Session s2 = scheduleOneSession(240);
        Track track = new Track(s1,s2,900);
        return track;
    }

    private Session scheduleOneSession(int dur) {
        //System.out.println("Creating session with duration: "+dur +" min");
        Session currSession = new Session(dur);
        int durSum = 0;
        Iterator<Task> itr = t.iterator();
        while (itr.hasNext()){
            Task i = itr.next();
            
            if (durSum + i.getDuration()<=dur){
                //System.out.println(i.toString());
                currSession.add(i);
                durSum += i.getDuration();
                itr.remove();
                
            }
        }
        return currSession;
    }
}
