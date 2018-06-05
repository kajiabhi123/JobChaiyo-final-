package com.example.designmodal.jobchaiyo.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Apply_Now extends CommonMenuActivity {
    EditText email, name, phone;
    Button uploadCv, submit;
    String file_path;
    String content_type;
    File f;
    String fileName;
    ProgressDialog progress;

    String job_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply__now);
        email = (EditText) findViewById(R.id.email_apply);
        name = (EditText) findViewById(R.id.full_name);
        phone = (EditText) findViewById(R.id.contact_no);
        uploadCv = (Button) findViewById(R.id.btn_cv);
        submit = (Button) findViewById(R.id.btn_submit);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Apply Now");


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            job_id = (String) bd.get("job_id");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                return;
            }
        }
        enable_button();
        enable_button1();
    }

    private void enable_button1() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    validateSubmit();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void validateSubmit() throws IOException {
        if (!validate()) {
            Toast.makeText(this, "Failed .Please fill form with valid data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            onPostJobSuccess();
        }

    }

    public void onPostJobSuccess() throws IOException
    {
        submitcv();
    }

    public boolean validate()
    {
        String CName = name.getText().toString();
        String Email = email.getText().toString();
        String Contact = phone.getText().toString();
        boolean valid = true;

        if (CName.isEmpty())
    {
        name.setError("please Enter Full Name");
        valid = false;
    }
        if (Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            email.setError("please Enter Email Address");
            valid= false;
        }
        if (Contact.isEmpty()||Contact.length()> 10)
        {
            phone.setError("please Enter Phone Number");
            valid= false;
        }
        return valid;
    }
    private void enable_button()
    {

        uploadCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(Apply_Now.this)
                        .withFilter(Pattern.compile("^.*.(pdf|doc|docx)$"))
                        .withRequestCode(10)
                        .start();

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            enable_button();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {
            f = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
            content_type = URLConnection.guessContentTypeFromName(f.getName());
            file_path = f.getAbsolutePath();
            uploadCv.setText(file_path.substring(file_path.lastIndexOf("/") + 1));
        }
    }
    private void submitcv() throws IOException {
        if (file_path==null){
            Toast.makeText(this, "Upload the cv first", Toast.LENGTH_SHORT).show();
        }else {
            String AppliedName = name.getText().toString();
           String AppliedEmail = email.getText().toString();
           String AppliedPhone = phone.getText().toString();
            String AppliedDateTime =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault()).format(new Date());

            OkHttpClient client = new OkHttpClient();
            RequestBody file_body = RequestBody.create(MediaType.parse(content_type), f);
            RequestBody request_body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("type", content_type)
                    .addFormDataPart("uploaded_file", file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                    .addFormDataPart("job_id",job_id)
                    .addFormDataPart("full_name",AppliedName)
                    .addFormDataPart("email",AppliedEmail)
                    .addFormDataPart("contact",AppliedPhone)
                    .addFormDataPart("AppliedDateTime",AppliedDateTime)
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.1.85/jobChaiyo/ApplyNow.php")
                    .post(request_body)
                    .build();
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Application Submitted!! Will email you soon..", Toast.LENGTH_SHORT).show();
                file_path = null;
                name.setText(null);
                email.setText(null);
                phone.setText(null);

                Intent intent = new Intent(Apply_Now.this,NavigationDrawerActivity.class);
                startActivity(intent);
            }

        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

    if(id==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}