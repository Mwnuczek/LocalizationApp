package com.umcs.localizationapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView dostawca;
    TextView dlugosc;
    TextView szerokosc;

    LocationManager mylm;

    Criteria cr;

    Location loc;
    String mojDostawca;

    private static final int LOCATION_ACCESS_CODE= 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //makeRequest();

        dostawca=(TextView) findViewById(R.id.textView1);
        dlugosc=(TextView) findViewById(R.id.textView2);
        szerokosc=(TextView) findViewById(R.id.textView3);
        setUpText();


    }


    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET},
                LOCATION_ACCESS_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_ACCESS_CODE: {
                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED || grantResults[1] !=
                        PackageManager.PERMISSION_GRANTED) {
                    //Log.i(TAG, "Permission has been denied by user");
                } else {
                    //Log.i(TAG, "Permission has been granted by user");
                    setUpText();
                }
                return;
            }
        }
    }
        public void setUpText() {
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int permissionCheck2 = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.INTERNET);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED || permissionCheck2 !=
                    PackageManager.PERMISSION_GRANTED) {
                //Log.i(TAG, "Permission to record denied");
                makeRequest();
            } else {
                //SaveTextS();

                cr = new Criteria();

                mylm = (LocationManager) getSystemService(LOCATION_SERVICE);
                mojDostawca = mylm.getBestProvider(cr, true);
                loc = mylm.getLastKnownLocation(mojDostawca);

                dostawca.setText("dostawca: "+mojDostawca);
                dlugosc.setText("dlugosc geograficzna: "+loc.getLongitude());
                szerokosc.setText("szerokosc geograficzna: "+loc.getLatitude());


            }
            }

}









