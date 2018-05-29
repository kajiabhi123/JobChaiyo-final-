package com.example.designmodal.jobchaiyo.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manjil on 5/13/2018.
 */

public class User {
    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private  String Name;

    @SerializedName("address")
    private  String CompanyAddress;

    @SerializedName("website")
    private  String Website;

    @SerializedName("person")
    private  String ContactPerson;

    @SerializedName("office")
    private  String OfficeNo;

    @SerializedName("mobile")
    private  String MobileNo;

    @SerializedName("email")
    private  String Email;

    @SerializedName("optemail")
    private  String OptEmail;


    @SerializedName("username")
    private  String Username;

    @SerializedName("password")
    private  String Password;

    @SerializedName("id")
    private  String id;

    // @SerializedName("title")
    //private String Title;

    //@SerializedName("image")
//    private String Image;


    public String getId() {
        return id;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public String getWebsite() {
        return Website;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public String getOfficeNo() {
        return OfficeNo;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public String getOptEmail() {
        return OptEmail;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getResponse() {
        return Response;
    }

    public String getName()
    {
        return Name;
    }

}
