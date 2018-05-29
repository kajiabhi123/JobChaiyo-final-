package com.example.designmodal.jobchaiyo.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manjil on 5/15/2018.
 */

public class ListofJobOwnership {
    @SerializedName("ownership_name")
    private String job_ownership;

    public ListofJobOwnership(String job_ownership) {
        this.job_ownership = job_ownership;
    }

    public String getJob_ownership() {
        return job_ownership;
    }

    public void setJob_ownership(String job_ownership) {
        this.job_ownership = job_ownership;
    }

    @Override
    public String toString() {
        return job_ownership;
    }

}
