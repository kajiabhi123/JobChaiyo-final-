package com.example.designmodal.jobchaiyo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designmodal.jobchaiyo.Activities.DetailsOfJobPostedByEmployers;
import com.example.designmodal.jobchaiyo.Model.ListofJobsPostedbyEmployer;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

/**
 * Created by compware on 5/27/2018.
 */

public class ListofJobsPostedbyEmployerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    private List<ListofJobsPostedbyEmployer> list;


    public ListofJobsPostedbyEmployerAdapter(Context context, List<ListofJobsPostedbyEmployer> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.jobs_feed, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(layoutView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        final String job_title =list.get(position).getJob_title();
        final String experience =list.get(position).getExperience();
        final String vacancy_no =list.get(position).getVacancy_no();
        final String deadline =list.get(position).getDeadline();
        final String job_description =list.get(position).getJob_description();
        final String job_specification =list.get(position).getJob_specification();
        final String education_name =list.get(position).getEducation_name();
        final String city_name =list.get(position).getCity_name();
        final String job_type =list.get(position).getJob_type();



        holder.company_name.setText(job_type);
        holder.job_title.setText(job_title);
        holder.deadline.setText(deadline);
        //setting click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(context, DetailsOfJobPostedByEmployers.class);
                intent.putExtra("job_title", job_title);
                intent.putExtra("vacancy_no",vacancy_no);
                intent.putExtra("experience", experience);
                intent.putExtra("education", education_name);
                intent.putExtra("location", city_name);
                intent.putExtra("job_type", job_type);
                intent.putExtra("deadline", deadline);
                intent.putExtra("job_Description", job_description);
                intent.putExtra("job_specification", job_specification);
                //intent.putExtra("company_name", company_name);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
