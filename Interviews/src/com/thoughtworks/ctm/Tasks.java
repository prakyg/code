package com.thoughtworks.ctm;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    protected final List<Task> taskList;
    public Tasks() {
        this.taskList = new ArrayList<Task>();
    }
    public void add(Task newTask){
        taskList.add(newTask);
    }
    
    public List<Task> getAllTasks(){
        return (new ArrayList<Task>(taskList));
    }
    public void print(){
        for (Task task:taskList){
            System.out.println(task.toString());
        }
    }
}
