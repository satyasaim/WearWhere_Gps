package com.example.wearwhere;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.io.IOException;
import java.util.List;

public class Home extends AppCompatActivity implements OnMapReadyCallback {


    SlidingMenu slidingMenu;
    ImageView menu;
    private GoogleMap mMap;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    Marker marker;
    LocationListener locationListener;

    GeofencingFragment geofencingFragment;

    FragmentTransaction mFragmentTransaction;

    FragmentManager fragmentManager;

    static Home home;

    public static Home activity() {
        return home;
    }
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");


        menu = (ImageView)findViewById(R.id.menu);

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setBehindOffsetRes(R.dimen.slide_menu_width);
        slidingMenu.setFadeDegree(0.20f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.leftmenufull);

        // Common.SlideMenuDesign(slidingMenu, TabEvents.this, getResources().getString(R.string.jobcom_jobcreate));
        Common.SlideMenuDesign(slidingMenu,Home.this,"home");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingMenu.toggle();

            }
        });



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }


        //Location//
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //get the location name from latitude and longitude
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getLocality() + ":";
                    result += addresses.get(0).getCountryName();
                    LatLng latLng = new LatLng(latitude, longitude);
                    if (marker != null) {
                        marker.remove();
                        marker = mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                        mMap.setMaxZoomPreference(20);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));
                    } else {
                        marker = mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                        mMap.setMaxZoomPreference(20);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 21.0f));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
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
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);




    }

    private void accessGPS() {

        fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();

        geofencingFragment = new GeofencingFragment();

        mFragmentTransaction.add(R.id.relative, geofencingFragment);
        mFragmentTransaction.commitAllowingStateLoss();
    }

//

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }



    final int GPS_PERMISSION_REQUEST_CODE = 99;

    public void askGPSPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(Home.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                        Manifest.permission.READ_CONTACTS)) {
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Home.this);
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("Please confirm Contacts access");
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION}
                                    , GPS_PERMISSION_REQUEST_CODE);
                        }
                    });
                    builder.show();


                } else {


                    ActivityCompat.requestPermissions(Home.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            GPS_PERMISSION_REQUEST_CODE);


                }
            } else {
                accessGPS();
            }
        } else {
            accessGPS();
        }
    }


    //    @Override
    public void onRequestPermissionsResult12(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case GPS_PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    accessGPS();

                } else {
                    SetDiolog(Home.this, "App does not have permission to read Contacts");
                }
                return;
            }

        }
    }


    public static AlertDialog mAlertDialog;

    public static void SetDiolog(final Activity activity, String message) {
//        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
        mAlertDialog = new AlertDialog.Builder(activity).create();
        mAlertDialog.setMessage(message);

        mAlertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialog.dismiss();
                activity.finish();
            }
        });
        mAlertDialog.show();
    }

    static final Integer LOCATION = 0x1;
    static final Integer SMS = 0x2;

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(Home.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(Home.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(Home.this, new String[]{permission}, requestCode);
            }
        } else {
//            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            if (requestCode == LOCATION) {
                askForPermission(Manifest.permission.SEND_SMS, SMS);
            } else if (requestCode == SMS) {
                accessGPS();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                //Location
                case 1:
//                    accessGPS();
                    askForPermission(Manifest.permission.SEND_SMS, SMS);
                    break;

                case 2:
                    accessGPS();
                    break;
                //Call
//                case 2:
//                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.parse("tel:" + "{This is a telephone number}"));
//                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        startActivity(callIntent);
//                    }
//                    break;
                //Write external Storage
//                case 3:
//                    break;
//                //Read External Storage
//                case 4:
//                    Intent imageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(imageIntent, 11);
//                    break;
//                //Camera
//                case 5:
//                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                        startActivityForResult(takePictureIntent, 12);
//                    }
//                    break;
//                //Accounts
//                case 6:
//                    AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
//                    Account[] list = manager.getAccounts();
//                    Toast.makeText(this,""+list[0].name,Toast.LENGTH_SHORT).show();
//                    for(int i=0; i<list.length;i++){
//                        Log.e("Account "+i,""+list[i].name);
//                    }
            }

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

            String per = null;
            for (int i = 0; i < permissions.length; i++) {
                if (permissions.length == 1)
                    per += permissions[i];
                else if ((permissions.length - 1) == i)
                    per += permissions[i];
                else
                    per += permissions[i] + ", ";
            }

            SetDiolog(Home.this, "App does not have permission to " + per);
        }
    }


}
