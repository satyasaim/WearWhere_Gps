package com.example.wearwhere;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeofencingFragment extends Fragment implements GoogleMap.OnMarkerClickListener,
        GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    SupportMapFragment mSupportMapFragment;
    private GoogleMap googleMap;

    Location mcurrentLocation;

    GPSTracker mGpsTracker;

//    LocationClient mLocationClient;

    DraggableCircle mDraggableCircle;


    double DEFAULT_RADIUS = 1500;

    public static final double RADIUS_OF_EARTH_METERS = 6371009;

    private List<DraggableCircle> mCircles = new ArrayList<DraggableCircle>(1);

    private int mStrokeColor;
    private int mFillColor;

    Marker centerMarker;
    Marker radiusMarker;
    Circle circle;

    double Radius;
    double lat = 0;
    double lon = 0;

    float start;

    float end;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences.Editor editor;

    LatLng mLatLng;
    //    Timer mTimer;
//    float zoomLevel = 10.0f;
    float zoomLevel = 13.0f;


    public GeofencingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mGpsTracker = new GPSTracker(getActivity());
//        mLocationClient = new LocationClient(getActivity(), this, this);
//        mTimer = new Timer();

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();


        View rootView = inflater.inflate(R.layout.fragment_geofencing, container, false);

        mSupportMapFragment = new SupportMapFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.relative, mSupportMapFragment).commit();


//        mLocationClient.connect();
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
        }

        return rootView;
        // Inflate the layout for this fragment
    }

    public void currentLocation() {
        if (mGpsTracker.canGetLocation) {
            double lat = mGpsTracker.latitude;
            double lon = mGpsTracker.longitude;

            LatLng currentLocation = new LatLng(lat, lon);

            googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .position((currentLocation)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, zoomLevel));

        }
    }

    public void showGeofence() {
        if (sharedpreferences.contains("latitude")) {
            lat = (double) sharedpreferences.getFloat("latitude", 0);
        }
        if (sharedpreferences.contains("longitude")) {
            lon = (double) sharedpreferences.getFloat("longitude", 0);
        }
        if (sharedpreferences.contains("radius")) {
            Radius = (double) sharedpreferences.getFloat("radius", 0);
        }
        if (sharedpreferences.contains("zoom")) {
            zoomLevel = (float) sharedpreferences.getFloat("zoom", 0);
        }
        mLatLng = new LatLng(lat, lon);

        mCircles.clear();
//        googleMap.clear();
        mDraggableCircle = new DraggableCircle(mLatLng, Radius);
        mCircles.add(mDraggableCircle);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, zoomLevel));
    }

    public void SetGeofence() {
        editor.putFloat("latitude", (float) circle.getCenter().latitude);
        editor.putFloat("longitude", (float) circle.getCenter().longitude);
        editor.putFloat("radius", (float) circle.getRadius());
        editor.putFloat("zoom", (float) googleMap.getCameraPosition().zoom);
        editor.commit();
        Log.e("SetGeofence", "Latitude: " + circle.getCenter().latitude);
        Log.e("SetGeofence", "Longitude: " + circle.getCenter().longitude);
        Log.e("SetGeofence", "Radius: " + circle.getRadius());
        Log.e("SetGeofence", "Zoom,: " + googleMap.getCameraPosition().zoom);

        Toast.makeText(getActivity(), "Geofence Set", Toast.LENGTH_SHORT).show();
    }

    private boolean checkPlayServices() {

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getActivity(), "This device is not supported.", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
            return false;
        }
        return true;
    }

    /*
       New location code
    */
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }

    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {





    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        buildGoogleApiClient();

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    public void setUpMap() {

        mStrokeColor = Color.BLACK;

        final Timer mTimer = new Timer();

        System.out.println("in timer before run");
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                OnMapReadyCallback onMapReadyCallback = null;
                mSupportMapFragment.getMapAsync(onMapReadyCallback);
                if (googleMap != null) {

                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            googleMap.setOnMarkerDragListener((GoogleMap.OnMarkerDragListener) GeofencingFragment.this);

                            mStrokeColor = Color.BLACK;

                            if (mGpsTracker.canGetLocation) {
                                {
                                    lat = mGpsTracker.latitude;
                                    lon = mGpsTracker.longitude;
                                }
                                if (sharedpreferences.contains("latitude")) {
                                    lat = (double) sharedpreferences.getFloat("latitude", 0);
                                }
                                if (sharedpreferences.contains("longitude")) {
                                    lon = (double) sharedpreferences.getFloat("longitude", 0);
                                }
                                if (sharedpreferences.contains("radius")) {
                                    Radius = (double) sharedpreferences.getFloat("radius", 0);
                                }
                                if (sharedpreferences.contains("zoom")) {
                                    zoomLevel = (float) sharedpreferences.getFloat("zoom", 0);
                                }
                                System.out.println("latitude in connected " + lat);
                                Log.e("SetGeofence", "Latitude: " + lat);
                                Log.e("SetGeofence", "Longitude: " + lon);
                                Log.e("SetGeofence", "Radius: " + Radius);
                                Log.e("SetGeofence", "Zoom,: " + zoomLevel);
                                mLatLng = new LatLng(lat, lon);
                                mCircles.clear();
                                googleMap.clear();
                                if (Radius > DEFAULT_RADIUS) {
                                    mDraggableCircle = new DraggableCircle(mLatLng, Radius);
                                    mCircles.add(mDraggableCircle);
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, zoomLevel));
                                } else {
                                    mDraggableCircle = new DraggableCircle(mLatLng, DEFAULT_RADIUS);
                                    mCircles.add(mDraggableCircle);
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 12.0f));
                                }
                            }

                        }
                    });
                    mTimer.cancel();
                }

            }

        }, 2000, 2000);

    }

    private class DraggableCircle {

        public DraggableCircle(LatLng center, double radius) {

            Radius = radius;

            centerMarker = googleMap.addMarker(new MarkerOptions()
                    .position(center)
                    .draggable(true));

            radiusMarker = googleMap.addMarker(new MarkerOptions()
                    .position(toRadiusLatLng(center, radius))
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_AZURE)));

            circle = googleMap.addCircle(new CircleOptions()
                    .center(center)
                    .radius(radius)
                    .strokeColor(mStrokeColor)
                    .fillColor(mFillColor));

            float[] results = new float[2];

            Location.distanceBetween(centerMarker.getPosition().latitude, centerMarker.getPosition().longitude, radiusMarker.getPosition().latitude, radiusMarker.getPosition().longitude, results);

            start = results[0];
        }

        public boolean onMarkerMoved(Marker marker) {
            if (marker.equals(centerMarker)) {

                circle.setCenter(marker.getPosition());
                radiusMarker.setPosition(toRadiusLatLng(marker.getPosition(), Radius));
                return true;
            }
            if (marker.equals(radiusMarker)) {

                Radius = toRadiusMeters(centerMarker.getPosition(), radiusMarker.getPosition());
                circle.setRadius(Radius);
                return true;
            }
            return false;
        }

        /**
         * Generate LatLng of radius marker
         */
        private LatLng toRadiusLatLng(LatLng center, double radius) {
            double radiusAngle = Math.toDegrees(radius / RADIUS_OF_EARTH_METERS) /
                    Math.cos(Math.toRadians(center.latitude));
            return new LatLng(center.latitude, center.longitude + radiusAngle);
        }

        private double toRadiusMeters(LatLng center, LatLng radius) {
            float[] result = new float[1];
            Location.distanceBetween(center.latitude, center.longitude,
                    radius.latitude, radius.longitude, result);

            return result[0];
        }

    }



}
