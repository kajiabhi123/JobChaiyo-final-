package com.example.designmodal.jobchaiyo.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.Fragments.Fragment_SearchJob;
import com.example.designmodal.jobchaiyo.Fragments.LoginPostJob;
import com.example.designmodal.jobchaiyo.Fragments.RegisterPostJob;
import com.example.designmodal.jobchaiyo.Fragments.ServiceFragment;
import com.example.designmodal.jobchaiyo.R;
import com.example.designmodal.jobchaiyo.SharedPreferences.PrefConfig;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,LoginPostJob.OnLoginFormActivityListener {

    private long backPressedTime ;
    private Toast backToast;
    PrefConfig prefConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefConfig = new PrefConfig(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new RecycleView_job()).commit();
        if (findViewById(R.id.container)!=null)
        {
            if (savedInstanceState!=null)
            {
                return;
            }

            if (prefConfig.readLoginStatus())
            {
                Intent intent=new Intent(NavigationDrawerActivity.this,Company_tab.class);
                startActivity(intent);
                finish();
            }
            else
            {
                getFragmentManager().beginTransaction().add(R.id.container,new LoginPostJob()).commit();

                //display login page
            }
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        openFragment(new RecycleView_job(), "Home");
        PrefConfig prefConfig=new PrefConfig(this);    }

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
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openFragment(Fragment f, String s){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
        setTitle(s);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            openFragment(new RecycleView_job(),"Jobs");
            // Handle the camera action
        } else if (id == R.id.nav_jobs) {

        } else if (id == R.id.nav_post_jobs)
        {
            getFragmentManager().beginTransaction().replace(R.id.container,new LoginPostJob()).commit();

        }
        else if (id == R.id.nav_services)
        {
           // getSupportFragmentManager().beginTransaction().replace(R.id.container,new ServiceFragment()).commit();
        openFragment(new ServiceFragment(), "Service");


        }
        else if (id == R.id.nav_search)
        {
            openFragment(new Fragment_SearchJob(),"search_job");
        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_contact)
        {
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
        getFragmentManager().beginTransaction().replace(R.id.container,new RegisterPostJob())
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
}
//cmt given
//jbjhb
