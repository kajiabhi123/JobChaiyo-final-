package com.example.designmodal.jobchaiyo.DataManager;

/**
 * Created by compware on 5/15/2018.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by compware on 5/13/2018.
 */

public class ApiClient {

    public static final String BASE_URL = "http://192.168.1.85/jobChaiyo/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit;
    }

}