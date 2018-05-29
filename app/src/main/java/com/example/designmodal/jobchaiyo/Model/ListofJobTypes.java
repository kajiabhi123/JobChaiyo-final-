package com.example.designmodal.jobchaiyo.Model;

/**
 * Created by Manjil on 5/21/2018.
 */

public class ListofJobTypes {
    private String job_type;
    //constructior
    public ListofJobTypes(String job_type)
    {
        this.job_type = job_type;
    }

    //getter and setter
    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type)
    {
        this.job_type = job_type;
    }

    @Override
    public String toString() {
        return job_type;
    }
}
