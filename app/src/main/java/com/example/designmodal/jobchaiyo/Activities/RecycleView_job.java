package com.example.designmodal.jobchaiyo.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.designmodal.jobchaiyo.Adapter.RecyclerViewAdapter;
import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.Model.JobAttributes;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecycleView_job extends Fragment {
    RecyclerView recyclerView;
    TextView loading;
    private SwipeRefreshLayout mSwipeLayout;


    public RecycleView_job() {

    }

    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_recycle_view_job, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mSwipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        loading = (TextView) view.findViewById(R.id.loading);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                calltry();
            }
        });

        calltry();
    }

    private void calltry() {
        loading.setText("Loading...");
        loading.setVisibility(View.VISIBLE);
        try {
            ApiInterface service = ApiClient.getRetrofit().create(ApiInterface.class);
            Call<List<JobAttributes>> call = service.getJobInfo();

            call.enqueue(new Callback<List<JobAttributes>>() {
                @Override
                public void onResponse(Call<List<JobAttributes>> call, Response<List<JobAttributes>> response) {
                    //Log.d("onResponse", response.message());

                    List<JobAttributes> userList = response.body();
                    //layoutManager = new LinearLayoutManager(MainActivity.this);
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),userList);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    loading.setVisibility(View.GONE);
                    mSwipeLayout.setRefreshing(false);
                }
                @Override
                public void onFailure(Call<List<JobAttributes>> call, Throwable t) {
                    loading.setText("Connection failed");
                    loading.setVisibility(View.VISIBLE);
                    t.printStackTrace();
                }
            });

        }
        catch (Exception e)
        {
        }
    }
}