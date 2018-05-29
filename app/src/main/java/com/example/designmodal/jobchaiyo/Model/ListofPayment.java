package com.example.designmodal.jobchaiyo.Model;

/**
 * Created by Manjil on 5/21/2018.
 */

public class ListofPayment {
    private String type_name;
    //constructior
    public ListofPayment(String type_name)
    {
        this.type_name = type_name;
    }

    //getter and setter
    public String getList_payment() {
        return type_name;
    }

    public void setList_payment(String type_name)
    {
        this.type_name = type_name;

    }

    @Override
    public String toString() {

        return type_name;
    }
}
