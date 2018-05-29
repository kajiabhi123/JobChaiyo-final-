package com.example.designmodal.jobchaiyo.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manjil on 5/15/2018.
 */

public class ListofJobCategory {
    @SerializedName("company_nature")
    private String job_category;

    public ListofJobCategory(String job_category) {
        this.job_category = job_category;

    }

    public String getJob_category() {
        return job_category;
    }

    public void setJob_category(String job_category) {
        this.job_category = job_category;
    }

    @Override
    public String toString() {
        return job_category;
    }
}
