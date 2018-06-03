package com.example.designmodal.jobchaiyo.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contact_Us extends CommonMenuActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button submit;
    private EditText uname, email,contact_number, message;
    private TextView call, t_v_email;
    private String c_uname, c_email, c_contact_number, c_message ;
    public static ApiInterface apiInterface;

    public Contact_Us() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mapFragment.getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
//        apiInterface = ApiUtigetApiClient();

        //form validation below find view by id
        submit=findViewById(R.id.submit);
        uname=findViewById(R.id.uname);
        email =findViewById(R.id.email);
        contact_number =findViewById(R.id.contact_number);
        message =findViewById(R.id.message);
        call = findViewById(R.id.call);
        t_v_email = findViewById(R.id.t_v_email);

        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+977 014256677"));

//                if (ActivityCompat.checkSelfPermission(MapsActivity.this,
//                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
                startActivity(callIntent);
            }
        });

        t_v_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent emailIntent = new Intent(Intent.ACTION_SEND);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("milto:"));
                emailIntent.setType("message/rfc822");
                String[]to={"manzil.shrs6655@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                startActivity(emailIntent);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDetail();//call when button is clicked to validate the input
            }
        });

    }

    public void submitDetail(){
        intialize();
        if (!validate()){

            Toast.makeText(this,"Failded to submit data",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "your quaries are submited",Toast.LENGTH_SHORT).show();
            insertDetail();
            cleareData();
        }
    }

    private void cleareData() {
        uname.setText("");
        email.setText("");
        contact_number.setText("");
        message.setText("");
    }

    public boolean validate() {
        boolean valid = true;
        if (c_uname.isEmpty()) {
            uname.setError("Please Enter Valid Company Name");
            valid = false;
        }
        if (c_email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(c_email).matches()) {
            email.setError("Please Enter Valid Email Address");
            valid = false;
        }


        if (c_contact_number.isEmpty() || c_contact_number.length() > 13) {
            contact_number.setError("please enter a correct number");
            valid = false;
        }
        if (c_message.isEmpty()) {
            message.setError("please fill the message");
            valid = false;
        }
        return valid;
    }

    public void intialize(){
        c_uname=uname.getText().toString().trim();
        c_email=email.getText().toString().trim();
        c_contact_number=contact_number.getText().toString().trim();
        c_message=message.getText().toString().trim();

    }
    private void insertDetail(){

        try{
            sendPost(c_uname,c_email,c_contact_number,c_message);
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
    public void sendPost(String c_uname, String c_email, String c_contact_number, String c_message)
            throws IOException {
        Call<String> call=apiInterface.getStringScaler(c_uname, c_email,c_contact_number,c_message);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(contecxt: MainActivity.this, text:"data inserted successfully",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng nepal = new LatLng(27.687387, 85.327759);
        mMap.addMarker(new MarkerOptions().position(nepal).title("Batista Coffee"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nepal));

        //added
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nepal, 16));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
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
