package com.example.designmodal.jobchaiyo.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.designmodal.jobchaiyo.R;
import com.example.designmodal.jobchaiyo.SharedPreferences.PrefConfig;

/**
 * Created by Manjil on 5/17/2018.
 */

public class tab_profile_frag extends Fragment {

    // Button logout;
    TextView address,name,website,contactPerson,officeContact,mobile,email,optEmail,username,password;
    Button logout;
    PrefConfig prefConfig;

    OnLogoutListener logoutListener;
    public interface OnLogoutListener
    {
        public void logoutperform();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_profile, container, false);
        prefConfig = new PrefConfig(getContext());
        logout = view.findViewById(R.id.logout);


        name = view.findViewById(R.id.name);
        address = view.findViewById(R.id.address);
        website = view.findViewById(R.id.website);
        contactPerson = view.findViewById(R.id.contactPerson);
        officeContact = view.findViewById(R.id.officeContact);
        mobile = view.findViewById(R.id.mobile);
        email = view.findViewById(R.id.email);
        optEmail = view.findViewById(R.id.optEmail);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);

        name.setText(prefConfig.readCompanyName());
        address.setText(prefConfig.readAddress());
        website.setText(prefConfig.readWebsite());
        contactPerson.setText(prefConfig.readPerson());
        officeContact.setText(prefConfig.readOffice());
        mobile.setText(prefConfig.readMobile());
        email.setText( prefConfig.readEmail());
        optEmail.setText(prefConfig.readOptEmail());
        username.setText(prefConfig.readUsername());
        password.setText(prefConfig.readPassword());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you Sure,Logout?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logoutListener.logoutperform();
                    }
                }).setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();


            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        logoutListener=(OnLogoutListener) activity;
    }


}
