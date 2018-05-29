package com.example.designmodal.jobchaiyo.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.Adapter.ListofJobsPostedbyEmployerAdapter;
import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.Model.ListofJobsPostedbyEmployer;
import com.example.designmodal.jobchaiyo.R;
import com.example.designmodal.jobchaiyo.SharedPreferences.PrefConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kuldip on 5/20/2018.
 */

public class tab_jobs_posted_frag extends Fragment
{
    String id;
    PrefConfig prefConfig;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    private SwipeRefreshLayout mSwipeLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycle_view_job,container,false);
        prefConfig = new PrefConfig(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        mSwipeLayout = view.findViewById(R.id.swipeRefreshLayout);
        id = prefConfig.readid();
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                calltry();
            }
        });
        calltry();
        return view;
    }

    public void calltry()
    {
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<ListofJobsPostedbyEmployer>> call = apiInterface.listofJobsPostedbyEmployer(id);
        call.enqueue(new Callback<List<ListofJobsPostedbyEmployer>>()
        {
            @Override
            public void onResponse(Call<List<ListofJobsPostedbyEmployer>>  call, Response<List<ListofJobsPostedbyEmployer>> response) {
                List<ListofJobsPostedbyEmployer> jobsPostedbyEmployerList = response.body();

                Toast.makeText(getActivity(), "onResponse: " + jobsPostedbyEmployerList.size(), Toast.LENGTH_LONG).show();
                ListofJobsPostedbyEmployerAdapter searchedJobsAdapter = new ListofJobsPostedbyEmployerAdapter(getActivity(),jobsPostedbyEmployerList);
                recyclerView.setAdapter(searchedJobsAdapter);
                mSwipeLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<ListofJobsPostedbyEmployer>>  call, Throwable t) {
                Toast.makeText(getActivity(), "Connection Timed Out", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}