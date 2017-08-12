package com.group6.TakeOff;

/**
 * Created by STzavelas on 08.08.17.
 */

public class Trip {

    public String Project;
    public String Name;
    public String DateFrom;
    public String DateTo;


    public Trip(String mProject, String mName, String mDATE_FROM, String mDATE_TO){
        this.Project = mProject;
        this.Name = mName;
        this.DateFrom= mDATE_FROM;
        this.DateTo = mDATE_TO;

    }

    public String getProject(){return Project;}
    public String getName(){return Name;}
    public String getDateFrom(){return DateFrom;}
    public String getDateTo(){return DateTo;}

}
