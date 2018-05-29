package com.example.designmodal.jobchaiyo.DataManager;

import android.util.Log;

import com.example.designmodal.jobchaiyo.Model.ListofJobEducation;
import com.example.designmodal.jobchaiyo.Model.ListofJobCategory;
import com.example.designmodal.jobchaiyo.Model.ListofJobLocation;
import com.example.designmodal.jobchaiyo.Model.ListofJobOwnership;
import com.example.designmodal.jobchaiyo.Model.ListofJobTypes;
import com.example.designmodal.jobchaiyo.Model.ListofPayment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Manjil on 5/15/2018.
 */




public class Service {

    ApiInterface apiService;
    public Service()
    {
        apiService = ApiClient.getRetrofit().create(ApiInterface.class);
    }


    public interface GetJobCallback{
        void onSuccessJobOwnership(List<ListofJobOwnership> listofJobOwnerhips);
        void onSuccessJobCategory(List<ListofJobCategory> listofJobCategory);
        void onSuccesslistEducation(List<ListofJobEducation> listofEducations);
        void onSuccesslistJobtypes(List<ListofJobTypes> listofJobTypes);
        void onSuccesslistJobLocation(List<ListofJobLocation> listofJobLocation);
        void onSuccesslistPayment(List<ListofPayment> listofPayment);
        void onError(String e);
    }


    public void getJobOwnerShipList(final GetJobCallback callback){
        Call<List<ListofJobOwnership>> l = apiService.getJobOwnershipList();
        l.enqueue(new Callback<List<ListofJobOwnership>>(){
            @Override
            public void onResponse(Call<List<ListofJobOwnership>> call, Response<List<ListofJobOwnership>> response) {
                List<ListofJobOwnership> s = response.body();
                if (s!=null && s.size() > 0){
                    callback.onSuccessJobOwnership(s);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofJobOwnership>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }

    public void getJobCategoryList(final GetJobCallback callback){
        Call<List<ListofJobCategory>> c = apiService.getJobCategoryList();
        c.enqueue(new Callback<List<ListofJobCategory>>(){
            @Override
            public void onResponse(Call<List<ListofJobCategory>> call, Response<List<ListofJobCategory>> response) {
                List<ListofJobCategory> s = response.body();
                if (s!=null && s.size() > 0){
                    callback.onSuccessJobCategory(s);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofJobCategory>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }

    public void getlisteducation(final GetJobCallback callback){
        Call<List<ListofJobEducation>> c = apiService.getListofEducation();
        c.enqueue(new Callback<List<ListofJobEducation>>(){
            @Override
            public void onResponse(Call<List<ListofJobEducation>> call, Response<List<ListofJobEducation>> response) {
                List<ListofJobEducation> s = response.body();
                if (s!=null && s.size() > 0){
                    callback.onSuccesslistEducation(s);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofJobEducation>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }

    public void getJobTypeList(final GetJobCallback callback){
        Call<List<ListofJobTypes>> c = apiService.getListofJobType();
        c.enqueue(new Callback<List<ListofJobTypes>>(){
            @Override
            public void onResponse(Call<List<ListofJobTypes>> call, Response<List<ListofJobTypes>> response) {
                List<ListofJobTypes> a = response.body();
                if (a!=null && a.size() > 0){
                    callback.onSuccesslistJobtypes(a);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofJobTypes>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }

    public void getJobLocationList(final GetJobCallback callback){

        Call<List<ListofJobLocation>> l = apiService.getListofJobLocation(); //??
        l.enqueue(new Callback<List<ListofJobLocation>>(){
            @Override
            public void onResponse(Call<List<ListofJobLocation>> call, Response<List<ListofJobLocation>> response) {
                List<ListofJobLocation> s = response.body();
                if (s!=null && s.size() > 0){
                    callback.onSuccesslistJobLocation(s);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofJobLocation>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }


    public void getPaymentList(final GetJobCallback callback){

        Call<List<ListofPayment>> l = apiService.getListofPayment(); //??
        l.enqueue(new Callback<List<ListofPayment>>(){
            @Override
            public void onResponse(Call<List<ListofPayment>> call, Response<List<ListofPayment>> response) {
                List<ListofPayment> s = response.body();
                if (s!=null && s.size() > 0){
                    callback.onSuccesslistPayment(s);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofPayment>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }





}
