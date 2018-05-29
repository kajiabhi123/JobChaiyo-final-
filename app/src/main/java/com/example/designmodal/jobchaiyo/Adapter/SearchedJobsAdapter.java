package com.example.designmodal.jobchaiyo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designmodal.jobchaiyo.Activities.Activity_Jobs_details;
import com.example.designmodal.jobchaiyo.Model.SearchedJobs;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;


/**
 * Created by Kuldip on 5/18/2018.
 */

public class SearchedJobsAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


    private Context context;
    private List<SearchedJobs> list;

    public SearchedJobsAdapter(Context context,List<SearchedJobs> list) {
        this.list=list;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.jobs_feed, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(layoutView);

        return recyclerViewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {


        final String job_title =list.get(position).getJob_title();
        final String deadline =list.get(position).getDeadline();
        final String job_description =list.get(position).getJob_description();
        final String job_specification = list.get(position).getJob_specification();
        final String experience = list.get(position).getExperience();
        final String location = list.get(position).getArea_name();
        final String job_type = list.get(position).getJob_type();
        final String education = list.get(position).getEducation_name();
        final String company_name= list.get(position).getCompany_name();
        final String  vacancy = list.get(position).getVacancy_no();
        final String job_id = list.get(position).getJob_id();
        // final String vacancy_seat = String.valueOf(vacancy);

        holder.company_name.setText(company_name);
        holder.job_title.setText(job_title);
        holder.deadline.setText(deadline);

        //setting click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity_Jobs_details.class);
                intent.putExtra("job_title", job_title);
                intent.putExtra("vacancy_no",vacancy);
                intent.putExtra("experience", experience);
                intent.putExtra("education", education);
                intent.putExtra("location", location);
                intent.putExtra("job_type", job_type);
                intent.putExtra("deadline", deadline);
                intent.putExtra("job_Description", job_description);
                intent.putExtra("job_specification", job_specification);
                intent.putExtra("company_name", company_name);
                intent.putExtra("job_id", job_id);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}