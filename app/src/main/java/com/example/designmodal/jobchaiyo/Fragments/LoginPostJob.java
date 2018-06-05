package com.example.designmodal.jobchaiyo.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.Activities.Company_tab;
import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.Model.User;
import com.example.designmodal.jobchaiyo.R;
import com.example.designmodal.jobchaiyo.SharedPreferences.PrefConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * A simple {@link Fragment} subclass.
 */
public class LoginPostJob extends Fragment {
    TextView Register;
    EditText Email, Password;
    Button Login;
    private ApiInterface apiInterface;
    OnLoginFormActivityListener loginFormActivityListener;
    PrefConfig prefConfig;
    //WelcomePostJob.OnLogoutListener logoutListener;
    public interface OnLoginFormActivityListener
    {
        public void performRegister();
        public void performLogin(String name);
    }

    public LoginPostJob() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login_post_job, container, false);
        Register= view.findViewById(R.id.Register);
        Email=view.findViewById(R.id.Email);
        Password=view.findViewById(R.id.Password);
        Login=view.findViewById(R.id.Login);
        prefConfig = new PrefConfig(container.getContext());

        apiInterface=  ApiClient.getRetrofit().create(ApiInterface.class);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();

            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFormActivityListener.performRegister();

            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        loginFormActivityListener=(OnLoginFormActivityListener) activity;

    }


    private void performLogin(){
        final String email=Email.getText().toString();
        String password=Password.getText().toString();
        Call<User> call =apiInterface.performUserLogin(email,password);

        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok"))
                {
                    prefConfig.writeLoginStatus(true);
                    // loginFormActivityListener.performLogin(response.body().getName());
                    String name=response.body().getName();
                    String address=response.body().getCompanyAddress();
                    String website=response.body().getWebsite();
                    String person=response.body().getContactPerson();
                    String office=response.body().getOfficeNo();
                    String mobile=response.body().getMobileNo();
                    String emaill=response.body().getEmail();
                    String optemail=response.body().getOptEmail();
                    String username=response.body().getUsername();
                    String passwordd=response.body().getPassword();
                    String id=response.body().getId();
                    prefConfig.writeid(id);
                    prefConfig.writeCompanyAddress(address);
                    prefConfig.writeCompanyName(name);
                    prefConfig.writeWebsite(website);
                    prefConfig.writeContactPerson(person);
                    prefConfig.writeOfficeNo(office);
                    prefConfig.writeMobile(mobile);
                    prefConfig.writeEmail(emaill);
                    prefConfig.writeOptEmail(optemail);
                    prefConfig.writeUsername(username);
                    prefConfig.writePassword(passwordd);
                    //       Toast.makeText(getContext(), "Address::" +address, Toast.LENGTH_SHORT).show();
                    //     Toast.makeText(getContext(), "id::" +id, Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getContext(), Company_tab.class);
                    startActivity(intent);
                }
                else if (response.body().getResponse().equals("failed"))
                {
                    prefConfig.displayToast("Login failed...Please try again...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                Toast.makeText(getContext(), "Sorry No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        Email.setText("");
        Password.setText("");
    }
}
