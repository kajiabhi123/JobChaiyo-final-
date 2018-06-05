package com.example.designmodal.jobchaiyo.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.designmodal.jobchaiyo.R;

public class CommonMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.common_menu_option, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share_app) {
            shareApp();
        }
        if(id==R.id.disclaimer)
            showDisclaimer();

        if(id==R.id.rate_us)
            rateMyApp();
        return super.onOptionsItemSelected(item);
    }

    public void showDisclaimer(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Disclaimer");
        View view = LayoutInflater.from(this).inflate(R.layout.menu_disclaimer,null);
        view.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
        dialog.setContentView(view);
        dialog.show();
        dialog.setCancelable(false);
    }

    public void shareApp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void rateMyApp(){
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + CommonMenuActivity.this.getPackageName()));
        startActivity(rateIntent);
    }
}