package com.example.designmodal.jobchaiyo.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.DataManager.Service;
import com.example.designmodal.jobchaiyo.Model.ListofJobCategory;
import com.example.designmodal.jobchaiyo.Model.ListofJobEducation;
import com.example.designmodal.jobchaiyo.Model.ListofJobLocation;
import com.example.designmodal.jobchaiyo.Model.ListofJobOwnership;
import com.example.designmodal.jobchaiyo.Model.ListofJobTypes;
import com.example.designmodal.jobchaiyo.Model.ListofPayment;
import com.example.designmodal.jobchaiyo.Model.reqJob;
import com.example.designmodal.jobchaiyo.R;
import com.example.designmodal.jobchaiyo.SharedPreferences.PrefConfig;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Manjil on 5/17/2018.
 */

public class tab_post_job_frag extends Fragment implements Service.GetJobCallback {

    EditText jobtitle, vacancy, areaName, experience, jobdes, jobSpec;
    MaterialSpinner city, education, jobType, payment;
    Button postbtn;
    Integer educationid, cityid, jobTypeid, paymentid;
    private String jtitle,jvacancy,jareaName,jexperience;
    //  Service service;
    //Context context;
    private ApiInterface apiInterface;
    PrefConfig prefConfig;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_post_jobs, container, false);
        jobtitle = view.findViewById(R.id.jobtitle);
        vacancy = view.findViewById(R.id.vacancy);
        areaName = view.findViewById(R.id.areaName);
        experience = view.findViewById(R.id.experience);
        jobdes = view.findViewById(R.id.jobDescription);
        jobSpec = view.findViewById(R.id.jobSpec);
        postbtn = view.findViewById(R.id.postbtn);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        prefConfig = new PrefConfig(getContext());

        education =(MaterialSpinner) view.findViewById(R.id.education);
        city = (MaterialSpinner) view.findViewById(R.id.city);
        jobType = (MaterialSpinner) view.findViewById(R.id.jobType);
        payment = (MaterialSpinner) view.findViewById(R.id.payment);
        String id =prefConfig.readid();
        Service service = new Service();
        educationid = 0;
        cityid = 0;
        jobTypeid = 0;
        paymentid = 0;
        Context context = getContext();
        service.getlisteducation(this);
        service.getJobTypeList(this);
        service.getJobLocationList(this);
        service.getPaymentList(this);

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();
            }
        });


        return view;
    }

    public void register() {
        intialize();// intializing the input to string variables
        if (!validate()) {
            Toast.makeText(getContext(), "Job Posted Failed .Please fill form with valid data", Toast.LENGTH_SHORT).show();
        } else {
            onPostJobSuccess();
        }


    }

    public void onPostJobSuccess() {
        postReq();
    }

    public boolean validate() {
        boolean valid = true;
        if (jtitle.isEmpty() || jtitle.length() > 32) {
            jobtitle.setError("Please Enter Valid Job Title");
            valid = false;
        }
        if (jvacancy.isEmpty() || jvacancy.length() > 1000) {
            vacancy.setError("Number of Vacancy should not be Empty");
            valid = false;
        }

        if (jareaName.isEmpty() || jareaName.length() > 43) {
            areaName.setError("Please Enter Valid Area Name");
            valid = false;
        }
        if (jexperience.isEmpty()) {
            experience.setError("Please Enter Required Experience or enter 0 for Fresher");
            valid = false;
        }

        if (educationid == 0) {
            education.setError("Please Select Required Education.....");
            valid = false;
        }
        if (cityid == 0) {
            city.setError("Please Select City...");
            valid = false;
        }
        if (jobTypeid == 0) {
            jobType.setError("Please Select Required Job Type...");
            valid = false;
        }
        if (paymentid == 0) {
            payment.setError("Please Select Payment Method...");
            valid = false;
        }
        return valid;
    }

    public void intialize() {
        jtitle = jobtitle.getText().toString().trim();
        jvacancy = vacancy.getText().toString().trim();
        jareaName = areaName.getText().toString().trim();
        jexperience = experience.getText().toString().trim();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                educationid = education.getSelectedItemPosition() ;
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityid = city.getSelectedItemPosition() ;
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jobTypeid = jobType.getSelectedItemPosition() ;
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                paymentid = jobType.getSelectedItemPosition()
                ;
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void postReq() {
        String jobtit = jobtitle.getText().toString();
        String vacanc = vacancy.getText().toString();
        String area = areaName.getText().toString();
        String exp = experience.getText().toString();
        String jobdesc = jobdes.getText().toString();
        String jobspec = jobSpec.getText().toString();
        String id = prefConfig.readid();

        Call<reqJob> call = apiInterface.performReqJob(id, jobtit, vacanc, area, exp, jobdesc, jobspec, educationid, cityid,
                jobTypeid,paymentid);

        call.enqueue(new Callback<reqJob>() {
            @Override
            public void onResponse(Call<reqJob> call, Response<reqJob> response) {
                if (response.body().getResponse().equals("ok")) {
                    prefConfig.displayToast("Job Request Success.....Your request to post job is pending. " +
                            "Job Chaheyo will be contacting you through phone or email shortly (within 12hrs).");
                } else {
                    prefConfig.displayToast("Failed... Try again");
                }
            }

            @Override
            public void onFailure(Call<reqJob> call, Throwable t) {
                prefConfig.displayToast("On failure...");
            }
        });
        jobtitle.setText("");
        vacancy.setText("");
        areaName.setText("");
        experience.setText("");
        jobdes.setText("");
        jobSpec.setText("");

    }


    @Override
    public void onSuccessJobOwnership(List<ListofJobOwnership> listofJobOwnerhips) {

    }

    @Override
    public void onSuccessJobCategory(List<ListofJobCategory> listofJobCategory) {

    }

    @Override
    public void onSuccesslistEducation(List<ListofJobEducation> listofEducations) {

        education.setAdapter(new ArrayAdapter<>(getActivity(), R.
                layout.spinner_layout,R.id.textView, listofEducations));

    }

    @Override
    public void onSuccesslistJobtypes(List<ListofJobTypes> listofJobTypes) {
        jobType.setAdapter(new ArrayAdapter<>(getActivity(), R.
                layout.spinner_layout,R.id.textView, listofJobTypes));
    }

    @Override
    public void onSuccesslistJobLocation(List<ListofJobLocation> listofJobLocation) {
        city.setAdapter(new ArrayAdapter<>(getActivity(), R.
                layout.spinner_layout,R.id.textView, listofJobLocation));
    }

    @Override
    public void onSuccesslistPayment(List<ListofPayment> listofPayment) {
        payment.setAdapter(new ArrayAdapter<>(getActivity(),R.
                layout.spinner_layout,R.id.textView, listofPayment));
    }


    @Override
    public void onError(String e) {

    }
}
