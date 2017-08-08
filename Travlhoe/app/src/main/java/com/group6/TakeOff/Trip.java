package com.group6.TakeOff;

/**
 * Created by STzavelas on 08.08.17.
 */

public class Trip {

    public String Project;
    public String Name;


    public Trip(String mProject, String mName){
        this.Project = mProject;
        this.Name = mName;

    }

    public String getProject(){return Project;}
    public String getName(){return Name;}

}
