package com.example.designmodal.jobchaiyo.Fragments;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.designmodal.jobchaiyo.Model.User;
import com.example.designmodal.jobchaiyo.R;
import com.example.designmodal.jobchaiyo.SharedPreferences.PrefConfig;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterPostJob extends Fragment implements Service.GetJobCallback {
    private EditText et_CName, et_CAddress, et_Website, et_ContactPerson, et_OfficeContact, et_Mobile, et_Email, et_ReEmail, et_OptEmail,
            et_UserName, et_Password, et_RePassword, title;
    private ImageView img;
    private static final int IMG_REQUEST = 111;
    private Bitmap bitmap;
    MaterialSpinner jobCategorySpinner, jobOwnershipSpinner;
    private String CName, CAddress, Website, ContactPerson, OfficeContact, Mobile, Email, ReEmail, OptEmail, tPassword, tRepassword,
            UserName, Password, tEmail, tReEmail, RePassword;
    Integer jobOwnership;
    Integer jobCategory;
    Button signin, cancel, choosebtn;
    Service service;
    PrefConfig prefConfig;

    Context context;
    private ApiInterface apiInterface;

    public RegisterPostJob() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_post_job, container, false);
        et_CName = view.findViewById(R.id.CName);
        et_CAddress = view.findViewById(R.id.CAddress);
        et_Website = view.findViewById(R.id.Website);
        et_ContactPerson = view.findViewById(R.id.ContactPerson);
        et_OfficeContact = view.findViewById(R.id.OfficeContact);
        et_Mobile = view.findViewById(R.id.Mobile);
        et_Email = view.findViewById(R.id.Email);
        et_ReEmail = view.findViewById(R.id.ReEmail);
        et_OptEmail = view.findViewById(R.id.OptEmail);
        et_UserName = view.findViewById(R.id.UserName);
        et_Password = view.findViewById(R.id.Password);
        et_RePassword = view.findViewById(R.id.RePassword);
        signin = view.findViewById(R.id.signin);
        cancel = view.findViewById(R.id.cancel);
        title = view.findViewById(R.id.title);

        prefConfig = new PrefConfig(container.getContext());
        jobCategorySpinner = (MaterialSpinner) view.findViewById(R.id.job_category);
        jobOwnershipSpinner = (MaterialSpinner) view.findViewById(R.id.job_ownership);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        service = new Service();
        service.getJobCategoryList(this);
        service.getJobOwnerShipList(this);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container, new LoginPostJob()).commit();
            }
        });

        jobCategory = 0;
        jobOwnership = 0;

        context = getContext();

        signin.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(context, "Signup has Failed", Toast.LENGTH_SHORT).show();
        } else {
            onSignupSuccess();
        }
    }

    public void onSignupSuccess() {
        performRegisteration();
    }

    public boolean validate() {
        boolean valid = true;
        if (CName.isEmpty() || CName.length() > 32) {
            et_CName.setError("Please Enter Valid Company Name");
            valid = false;
        }
        if (CAddress.isEmpty() || CAddress.length() > 32) {
            et_CAddress.setError("Please Enter Valid Company Address");
            valid = false;
        }
        if (Mobile.isEmpty() || Mobile.length() > 10) {
            et_Mobile.setError("Please Enter Valid Mobile Number");
            valid = false;
        }
        if (Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            et_Email.setError("Please Enter Valid Email Address");
            valid = false;
        }
        if (!ReEmail.equals(Email)) {
            et_ReEmail.setError("Email not match. Please Reenter");
            valid = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(OptEmail).matches()) {
            et_OptEmail.setError("Please Enter Valid Email Address");
            valid = false;
        }
        if (UserName.isEmpty() || UserName.length() > 32) {
            et_UserName.setError("Please Enter Valid Username");
            valid = false;
        }
        if (Password.isEmpty() || Password.length() > 32) {
            et_Password.setError("Please Enter Valid Password");
            valid = false;
        }
        if (!RePassword.equals(Password)) {
            et_RePassword.setError("Password not match. Please Reenter your Password");
            valid = false;
        }
        if (jobCategory == 0) {
            jobCategorySpinner.setError("Please Select your Ownership.....");
            valid = false;
        }
        if (jobOwnership == 0) {
            jobOwnershipSpinner.setError("Please Select your Job Category");
            valid = false;
        }
        return valid;
    }

    public void intialize() {
        CName = et_CName.getText().toString().trim();
        CAddress = et_CAddress.getText().toString().trim();
        Website = et_Website.getText().toString().trim();
        ContactPerson = et_ContactPerson.getText().toString().trim();
        OfficeContact = et_OfficeContact.getText().toString().trim();
        Mobile = et_Mobile.getText().toString().trim();
        Email = et_Email.getText().toString().trim();
        ReEmail = et_ReEmail.getText().toString().trim();
        OptEmail = et_OptEmail.getText().toString().trim();
        UserName = et_UserName.getText().toString().trim();
        Password = et_Password.getText().toString().trim();
        RePassword = et_RePassword.getText().toString().trim();

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jobCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jobCategory = -1;
                //name jobcategory kaam job ownership ko ho
                jobCategory = jobCategorySpinner.getSelectedItemPosition();
                if (jobCategory == 0) {
                    jobCategorySpinner.setError("Please Select your Ownership.....");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        jobOwnershipSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jobOwnership = jobOwnershipSpinner.getSelectedItemPosition();
                //name ownership kaam job category ko ho
                if (jobOwnership == 0) {
                    jobOwnershipSpinner.setError("Please Select your Job Category");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void performRegisteration() {
//        uploadImage();
        String name = et_CName.getText().toString().trim();
        String address = et_CAddress.getText().toString().trim();
        String website = et_Website.getText().toString().trim();
        String person = et_ContactPerson.getText().toString().trim();
        String office = et_OfficeContact.getText().toString().trim();
        String mobile = et_Mobile.getText().toString().trim();
        String email = et_Email.getText().toString().trim();
        // String reemail = et_ReEmail.getText().toString().trim();
        String optemail = et_OptEmail.getText().toString().trim();
        String username = et_UserName.getText().toString().trim();
        String password = et_Password.getText().toString().trim();
        String repassword = et_RePassword.getText().toString().trim();
//        String tit=title.getText().toString().trim();
//        String Image=imageToString();


        Call<User> call = apiInterface.performRegistration
                (name, address, website, person, office, mobile, email, optemail, username, password, jobCategory, jobOwnership);
//        Toast.makeText(getContext(), "registration function", Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                if (response.body().getResponse().equals("ok")) {
                    prefConfig.displayToast("Registration Success.....Use Email and Password for Login");
                    getFragmentManager().beginTransaction().replace(R.id.container, new LoginPostJob()).commit();


//                    Toast.makeText(getContext(), "If condition ", Toast.LENGTH_SHORT).show();
                } else if (response.body().getResponse().equals("exist")) {

                    prefConfig.displayToast("User already exist...Use your Email and Password for Login");
                    getFragmentManager().beginTransaction().replace(R.id.container, new LoginPostJob()).commit();
//                    Toast.makeText(getContext(), "Else If exist  condition ", Toast.LENGTH_SHORT).show();
//

                } else if (response.body().getResponse().equals("error")) {

                    prefConfig.displayToast("Something went wrong...Please Try Again");
//                    Toast.makeText(getContext(), "else If error condition ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "on failure condition ...Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }

        });


        et_CName.setText("");
        et_CAddress.setText("");
        et_ContactPerson.setText("");
        et_Email.setText("");
        et_Mobile.setText("");
        et_OfficeContact.setText("");
        et_OptEmail.setText("");
        et_Password.setText("");
        et_ReEmail.setText("");
        et_RePassword.setText("");
        et_UserName.setText("");
        et_Website.setText("");
    }

    @Override
    public void onSuccessJobOwnership(List<ListofJobOwnership> listofJobOwnerhips) {
        jobCategorySpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.
                layout.spinner_layout, R.id.textView, listofJobOwnerhips));


    }

    @Override
    public void onSuccessJobCategory(List<ListofJobCategory> listofJobCategory) {
        jobOwnershipSpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.
                layout.spinner_layout, R.id.textView, listofJobCategory));

    }

    @Override
    public void onSuccesslistEducation(List<ListofJobEducation> listofEducations) {

    }

    @Override
    public void onSuccesslistJobtypes(List<ListofJobTypes> listofJobTypes) {

    }

    @Override
    public void onSuccesslistJobLocation(List<ListofJobLocation> listofJobLocation) {

    }

    @Override
    public void onSuccesslistPayment(List<ListofPayment> listofPayment) {

    }


    @Override
    public void onError(String e) {
        Toast.makeText(getActivity(), e, Toast.LENGTH_LONG).show();
    }

}


