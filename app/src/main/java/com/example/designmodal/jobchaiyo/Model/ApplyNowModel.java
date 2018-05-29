package com.example.designmodal.jobchaiyo.Model;

/**
 * Created by compware on 5/24/2018.
 */

public class ApplyNowModel
{
    private String FullName;
    private String Contact_Number;
    private String Email;
    private String File_Cv;
    private String response;

    public ApplyNowModel(String response,String fullName, String contact_Number, String email, String file_Cv)
    {
        FullName = fullName;
        Contact_Number = contact_Number;
        Email = email;
        File_Cv = file_Cv;
        response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getContact_Number() {
        return Contact_Number;
    }

    public void setContact_Number(String contact_Number) {
        Contact_Number = contact_Number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFile_Cv() {
        return File_Cv;
    }

    public void setFile_Cv(String file_Cv) {
        File_Cv = file_Cv;
    }
}

