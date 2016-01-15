package com.thoughtworks.ctm;

public class GenericTrackFactory {
    private static final Task lunchTask = new Task ("Lunch",60);
    private static final Task networkingTask = new Task ("Networking Event",60);
    private static final Session lunchSession = new Session (lunchTask);
    private static final Session networkingSession = new Session (networkingTask);
    public GenericTrackFactory() {
        super();
    }
    public Track getGenericTrack(){
        //Create a session list here 
        //180 min, lunch, 240 min , netw
        //Decouple scheduler code from other impl by using this method to get session list
        //add boolean schedulable for scheduler class so that lunch etc get ignored
        return new Track(null,null,0);
    }
}
