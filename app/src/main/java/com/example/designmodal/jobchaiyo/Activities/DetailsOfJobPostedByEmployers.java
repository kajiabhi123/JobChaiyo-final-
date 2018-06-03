package com.example.designmodal.jobchaiyo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.designmodal.jobchaiyo.R;

public class DetailsOfJobPostedByEmployers extends CommonMenuActivity
{
    TextView company_name, title, vacancy, experience_required, education_required, location, type_job, vacancy_deadline,
            specification_job, description_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_job_posted_by_employers);
        title = (TextView) findViewById(R.id.job_title);
        vacancy = (TextView) findViewById(R.id.vacancy);
        experience_required = (TextView) findViewById(R.id.experience);
        location = (TextView) findViewById(R.id.location);
        type_job = (TextView) findViewById(R.id.job_type);
        vacancy_deadline = (TextView) findViewById(R.id.deadline);
        specification_job = (TextView) findViewById(R.id.job_specification);
        description_job = (TextView) findViewById(R.id.job_description);
        education_required = (TextView) findViewById(R.id.education);
        company_name = (TextView) findViewById(R.id.company);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            String job_title = (String) bd.get("job_title");
            String vacancy_no = (String) bd.get("vacancy_no");
            String experience = (String) bd.get("experience");
            String education = (String) bd.get("education");
            String job_location = (String) bd.get("location");
            String job_type = (String) bd.get("job_type");
            String job_specification = (String) bd.get("job_specification");
            String deadline = (String) bd.get("deadline");
            String job_Description = (String) bd.get("job_Description");
            String companyName = (String) bd.get("company_name");

            //setting the job description value
            title.setText(job_title);
            vacancy.setText(vacancy_no);
            experience_required.setText(experience);
            vacancy_deadline.setText(deadline);
            location.setText(job_location);
            type_job.setText(job_type);
            education_required.setText(education);
            description_job.setText(job_Description);
            specification_job.setText(job_specification);
            company_name.setText(companyName);

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
