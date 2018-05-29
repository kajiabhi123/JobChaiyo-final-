package com.example.designmodal.jobchaiyo.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.Model.ApplyNowModel;
import com.example.designmodal.jobchaiyo.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

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

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            job_id = (String) bd.get("job_id");
        }


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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerfromApply();
                // Toast.makeText(Apply_Now.this, job_id, Toast.LENGTH_SHORT).show();
            }

        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                return;
            }
        }

    }

    public void PerfromApply() {

        OkHttpClient client = new OkHttpClient();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), f);
        RequestBody request_body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("uploaded_file", file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                .build();

        fileName = file_path.substring(file_path.lastIndexOf("/") + 1);

        String AppliedName = name.getText().toString();
        String AppliedEmail = email.getText().toString();
        String AppliedPhone = phone.getText().toString();
        String AppliedDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault()).format(new Date());


        //Call<ApplyNowModel> performApply(@Field("id") String Id,@Field("title") String Title);
        //Call<ApplyNowModel> call = ApiInterface.
        ApiInterface service = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ApplyNowModel> call = service.PerformApplyNow(job_id, AppliedEmail, AppliedName, AppliedPhone,fileName,AppliedDateTime);

        call.enqueue(new Callback<ApplyNowModel>() {
            @Override
            public void onResponse(Call<ApplyNowModel> call, retrofit2.Response<ApplyNowModel> response) {
                if (response.body().getResponse().equals("Success")) {
                    Toast.makeText(Apply_Now.this, "You have Successfully Applied For this job.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Apply_Now.this, NavigationDrawerActivity.class);
                    startActivity(intent);
                    //getSupportFragmentManager().beginTransaction().replace(R.id.container,new RecycleView_job()).commit();
                } else if (response.body().getResponse().equals("Failed")) {
                    Toast.makeText(Apply_Now.this, "failed to apply for the job", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApplyNowModel> call, Throwable t) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {
            f = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
            content_type = URLConnection.guessContentTypeFromName(f.getName());
            file_path = f.getAbsolutePath();
            Toast.makeText(this, "File path" + file_path, Toast.LENGTH_SHORT).show();
        }

    }
}