package com.thoughtworks.ctm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Track {
    private final List<Session> sessionList;
    private final int dayStartTime;
    public Track(Session morn, Session eve,int dayStartTime) {
        this.sessionList = new ArrayList<Session>(4);
        this.dayStartTime = dayStartTime;
        sessionList.add(morn);
        sessionList.add(Session.getLunchSession()); //lunch
        sessionList.add(eve);
        sessionList.add(Session.getNetworkingSession()); //networking
    }
    
    public void print(){
        for (Session session : sessionList){
            session.print();
        }
    }
    public void printWithTime(){
        int clock = dayStartTime;
        //Date date = new Date();
        for (Session session : sessionList){
            for (Task task : session.getAllTasks()){
                if (task.equals(Task.getLunchTask())){
                    clock = 1200;        
                }
                System.out.println(printClock(clock)+" "+task.toString());
                
                int temp= task.getDuration();
                while (temp>=60){
                    clock+=100;
                    temp = temp-60;
                    if (clock >=1300){
                        clock -= 1200;
                    }
                }
                clock += temp;
                clock = correct(clock);
            }
        }
    }

    private String printClock(int clock) {
        String temp = String.format("%04d", clock);
        StringBuilder sb = new StringBuilder("");
        sb.append(temp.substring(0,2));
        sb.append(":");
        sb.append(temp.substring(2,4));
        if (clock >=900 && clock<1200)
            sb.append("AM");
        else{
            sb.append("PM");
        }
        return sb.toString();
    }

    private int correct(int clock) {
        String temp = String.format("%04d", clock);
        Integer x = Integer.valueOf(temp.substring(2,4));
        if (x>=60){
            clock = clock+40;
        }
        return clock;
    }
}
