package com.example.designmodal.jobchaiyo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.designmodal.jobchaiyo.Activities.SearchJobActivity;
import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.DataManager.Service;
import com.example.designmodal.jobchaiyo.Model.ListofJobCategory;
import com.example.designmodal.jobchaiyo.Model.ListofJobEducation;
import com.example.designmodal.jobchaiyo.Model.ListofJobLocation;
import com.example.designmodal.jobchaiyo.Model.ListofJobOwnership;
import com.example.designmodal.jobchaiyo.Model.ListofJobTypes;
import com.example.designmodal.jobchaiyo.Model.ListofPayment;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;


/**
 * Created by Kuldip on 5/8/2018.
 */

public class Fragment_SearchJob extends Fragment implements Service.GetJobCallback {

    Context context;
    EditText search_job;
    MaterialSpinner  jobCategorySpinner,jobLocationSpinner, jobEducationSpinner, jobOwnershipSpinner,jobTypeSpinner;

    Service service;
    Button search_btn;
    String jobType, jobLocation, jobEducation,jobOwnership, jobCategory,jobSearch;

    //    Editable jobSearch;
    private ApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_view, null);

        context = container.getContext();
        jobTypeSpinner =view.findViewById(R.id.job_type);
        jobLocationSpinner = view.findViewById(R.id.job_location);
        jobEducationSpinner = view.findViewById(R.id.job_education);
        jobOwnershipSpinner = view.findViewById(R.id.job_ownership);
        jobCategorySpinner  =view.findViewById(R.id.job_category);
        search_btn  =view.findViewById(R.id.find_now);
        search_job  =view.findViewById(R.id.search_job);

        service = new Service();
        service.getJobTypeList(this);
        service.getJobLocationList(this);
        service.getlisteducation(this);
        service.getJobOwnerShipList(this);
        service.getJobCategoryList(this);
        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);

        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSelctedJobs();
            }
        });
        return view;
    }
    public void sendSelctedJobs()
    {
        jobSearch = search_job.getText().toString();

        if(jobLocationSpinner.getSelectedItemPosition()>0)
            jobLocation = ((ListofJobLocation)jobLocationSpinner.getSelectedItem()).getJob_location();
        else
            jobLocation = "";

        if(jobCategorySpinner.getSelectedItemPosition()>0)
            jobCategory = ((ListofJobCategory)jobCategorySpinner.getSelectedItem()).getJob_category();
        else
            jobCategory="";

        if(jobTypeSpinner.getSelectedItemPosition()>0)
            jobType = ((ListofJobTypes)jobTypeSpinner.getSelectedItem()).getJob_type();
        else
            jobType="";

        if(jobEducationSpinner.getSelectedItemPosition()>0)
            jobEducation = ((ListofJobEducation)jobEducationSpinner.getSelectedItem()).getJob_education();
        else
            jobEducation ="";

        if(jobOwnershipSpinner.getSelectedItemPosition()>0)
            jobOwnership = ((ListofJobOwnership)jobOwnershipSpinner.getSelectedItem()).getJob_ownership();
        else
            jobOwnership = "";


        Intent intent = new Intent(context, SearchJobActivity.class);
        intent.putExtra("jobSearch",jobSearch);
        intent.putExtra("jobType",jobType);
        intent.putExtra("jobLocation",jobLocation);
        intent.putExtra("jobEducation",jobEducation);
        intent.putExtra("jobOwnership",jobOwnership);
        intent.putExtra("jobCategory",jobCategory);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onSuccesslistJobLocation(List<ListofJobLocation> listofJobLocations) {
        jobLocationSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.spinner_layout,R.id.textView,listofJobLocations));
    }
    @Override
    public void onSuccesslistPayment(List<ListofPayment> listofPayment)
    {
    }

    @Override
    public void onError(String e)
    {
    }

    @Override
    public void onSuccesslistJobtypes(List<ListofJobTypes> listofJobTypes) {
        jobTypeSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.spinner_layout,R.id.textView, listofJobTypes));
    }

    @Override
    public void onSuccesslistEducation(List<ListofJobEducation> listofJobEducations) {
        jobEducationSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.spinner_layout,R.id.textView, listofJobEducations));
    }

    @Override
    public void onSuccessJobOwnership(List<ListofJobOwnership> listofJobOwnerhips) {
        jobOwnershipSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.spinner_layout,R.id.textView, listofJobOwnerhips));
    }

    @Override
    public void onSuccessJobCategory(List<ListofJobCategory> listofJobCategory)
    {
        jobCategorySpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.spinner_layout,R.id.textView, listofJobCategory));
    }

}