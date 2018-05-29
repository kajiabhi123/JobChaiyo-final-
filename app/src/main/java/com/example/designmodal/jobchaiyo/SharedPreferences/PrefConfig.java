package com.example.designmodal.jobchaiyo.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.R;


/**
 * Created by Manjil on 5/11/2018.
 */

public class PrefConfig
{
    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file),context.MODE_PRIVATE);

    }

    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }
    public boolean readLoginStatus()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    public void writeNmae(String name)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name),name);
        editor.commit();
    }

    public void writeCompanyName(String CompanyName)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_company_name),CompanyName);
        editor.commit();
    }

    public void writeCompanyAddress(String CompanyAddress)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_company_address),CompanyAddress);
        editor.commit();
    }
    public void writeWebsite(String Website)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_website),Website);
        editor.commit();
    }

    public void writeContactPerson(String ContactPerson)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_contact_person),ContactPerson);
        editor.commit();
    }

    public void writeOfficeNo(String OfficeNo)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_office_no),OfficeNo);
        editor.commit();
    }

    public void writeMobile(String Mobile)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_mobile_no),Mobile);
        editor.commit();
    }

    public void writeEmail(String Email)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_email),Email);
        editor.commit();
    }

    public void writeOptEmail(String OptEmail)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_opt_email),OptEmail);
        editor.commit();
    }
    public void writeUsername(String Username)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name),Username);
        editor.commit();
    }
    public void writePassword(String Password)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_password),Password);
        editor.commit();
    }
    public void writeid(String id)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_id),id);
        editor.commit();
    }
    public String readid()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_id),"Id");
    }
    public String readName()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_user_name),"User");
    }

    public String readCompanyName()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_company_name),"Company Name");
    }

    public String readAddress()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_company_address),"Company Address");
    }
    public String readWebsite()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_website),"Company Website");
    }
    public String readPerson()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_contact_person),"Person Contact");
    }
    public String readOffice()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_office_no),"Office Contact");
    }
    public String readMobile()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_mobile_no),"Mobile Number");
    }
    public String readEmail()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_email),"Company Email");
    }
    public String readOptEmail()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_opt_email),"Company Optional Email");
    }
    public String readUsername()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_user_name),"Username");
    }
    public String readPassword()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_password),"Password");
    }

    public void displayToast(String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
