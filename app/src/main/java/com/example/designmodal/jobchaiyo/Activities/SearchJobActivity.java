package com.example.designmodal.jobchaiyo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.Adapter.SearchedJobsAdapter;
import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.Model.SearchedJobs;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchJobActivity extends CommonMenuActivity {
    RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeLayout;
    String jobType, jobLocation, jobEducation, jobOwnership,jobCategory, jobSearch;

    private List<SearchedJobs> searchedList;
    private LinearLayoutManager linearLayoutManager;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_job);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Searched Jobs");
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                calltry();
            }
        });

        calltry();
    }

    public void calltry() {
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            jobSearch = (String) bd.get("jobSearch");
            jobType = (String) bd.get("jobType");
            jobLocation = (String) bd.get("jobLocation");
            jobEducation = (String) bd.get("jobEducation");
            jobOwnership = (String) bd.get("jobOwnership");
            jobCategory = (String) bd.get("jobCategory");

        }
        Call<List<SearchedJobs>> call = apiInterface.sendSelectedJob(jobSearch, jobCategory, jobEducation, jobType, jobLocation, jobOwnership);
        call.enqueue(new Callback<List<SearchedJobs>>() {
            @Override
            public void onResponse(Call<List<SearchedJobs>> call, Response<List<SearchedJobs>> response) {
                List<SearchedJobs> searchedList = response.body();
                SearchedJobsAdapter searchedJobsAdapter = new SearchedJobsAdapter(SearchJobActivity.this, searchedList);
                recyclerView.setAdapter(searchedJobsAdapter);
                mSwipeLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<SearchedJobs>> call, Throwable t) {
                Toast.makeText(SearchJobActivity.this, "No Jobs Found", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

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

