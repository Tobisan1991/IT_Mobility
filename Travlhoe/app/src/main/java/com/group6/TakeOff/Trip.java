package com.group6.TakeOff;

/**
 * Created by STzavelas on 08.08.17.
 */

public class Trip {

    public String Project;
    public String Name;
    public String DATE_FROM;
    public String DATE_TO;


    public Trip(String mProject, String mName, String mDATE_FROM, String mDATE_TO){
        this.Project = mProject;
        this.Name = mName;
        this.DATE_FROM = mDATE_FROM;
        this.DATE_TO = mDATE_TO;

    }

    public String getProject(){return Project;}
    public String getName(){return Name;}
    public String getDateFrom(){return DATE_FROM;}
    public String getDateTo(){return DATE_TO;}

}
