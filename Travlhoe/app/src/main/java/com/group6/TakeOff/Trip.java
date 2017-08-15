package com.group6.TakeOff;

/**
 * Created by STzavelas on 08.08.17.
 */

public class Trip {

    public String Project;
    public double Price;
    public String DateFrom;
    public String DateTo;


    public Trip(String mProject, double mPrice, String mDATE_FROM, String mDATE_TO){
        this.Project = mProject;
        this.Price = mPrice;
        this.DateFrom= mDATE_FROM;
        this.DateTo = mDATE_TO;

    }

    public String getProject(){return Project;}
    public double getPrice(){return Price;}
    public String getDateFrom(){return DateFrom;}
    public String getDateTo(){return DateTo;}

}
