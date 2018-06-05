package com.example.designmodal.jobchaiyo.Activities;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.Fragments.Fragment_Internship;
import com.example.designmodal.jobchaiyo.Fragments.Fragment_SearchJob;
import com.example.designmodal.jobchaiyo.Fragments.LoginPostJob;
import com.example.designmodal.jobchaiyo.Fragments.RegisterPostJob;
import com.example.designmodal.jobchaiyo.Fragments.ServiceFragment;
import com.example.designmodal.jobchaiyo.R;
import com.example.designmodal.jobchaiyo.SharedPreferences.PrefConfig;

public class NavigationDrawerActivity extends CommonMenuActivity implements NavigationView.OnNavigationItemSelectedListener,LoginPostJob.OnLoginFormActivityListener {

    private long backPressedTime ;
    private Toast backToast;
    PrefConfig prefConfig;
    TextView ErrorLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefConfig = new PrefConfig(this);
        ErrorLoading = findViewById(R.id.CheckInternetMsg);
        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            if (prefConfig.readLoginStatus()) {
                Intent intent = new Intent(NavigationDrawerActivity.this, Company_tab.class);
                startActivity(intent);
                finish();
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.main_container, new LoginPostJob()).commit();

                //display login page
            }
        }

        // Checking Internet Connection
           checkConnection();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // openFragment(new RecycleView_job(), "Home");
    }
    @Override
    public void onBackPressed() {
        if(backPressedTime+2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast = Toast.makeText(NavigationDrawerActivity.this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime  = System.currentTimeMillis();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
           // openFragment(new RecycleView_job(),"Jobs");
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new RecycleView_job()).commit();
            this.getSupportActionBar().setTitle("Home");
            // Handle the camera action
        } else if (id == R.id.nav_jobs)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new RecycleView_job()).commit();
            this.getSupportActionBar().setTitle("Jobs");

        } else if (id == R.id.nav_post_jobs)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new LoginPostJob()).commit();
            this.getSupportActionBar().setTitle("Post Job");


        }
        else if (id == R.id.nav_services)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ServiceFragment()).commit();
            this.getSupportActionBar().setTitle("Services");



        }
        else if (id == R.id.nav_internship)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Fragment_Internship()).commit();
            this.getSupportActionBar().setTitle("Internship");

        }
        else if (id == R.id.nav_search)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Fragment_SearchJob()).commit();
            this.getSupportActionBar().setTitle("Search Jobs");

        }
        else if (id == R.id.nav_share) {
            this.getSupportActionBar().setTitle("Share Us");

            Intent sendIntent = new Intent();

            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }
        else if (id == R.id.nav_contact)
        {
            this.getSupportActionBar().setTitle("Contact Us");

            Intent intent = new Intent(NavigationDrawerActivity.this,Contact_Us.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void performRegister()
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new RegisterPostJob())
                .addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name)
    {
        PrefConfig prefConfig=new PrefConfig(this);
        prefConfig.writeNmae(name);

        //getFragmentManager().beginTransaction().replace(R.id.fragment_container,new WelcomePostJob()).commit();

        Intent intent=new Intent(NavigationDrawerActivity.this,TabActivity.class);
        startActivity(intent);
        finish();
    }


    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;

        } else {

            return false;

        }

    }

    public void checkConnection(){

        if(isOnline()){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new RecycleView_job()).commit();

        }else{

            ErrorLoading.setText("Whoops!! No Internet Connection Found.");
        }

    }
}
//cmt given
//jbjhb
