package com.example.wearwhere;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//public class Tracking extends AppCompatActivity {
//
//    private DrawerLayout mDrawerLayout;
//    private ActionBarDrawerToggle mDrawerToggle;
//    private GeofencingFragment geofencingFragment;
//    FragmentTransaction mFragmentTransaction;
//    private ListView mDrawerList;
//
//    static Tracking trackingact;
//
//    FragmentManager fragmentManager;
//    AlertDialog alertForMobileNumber;
//
//    private CharSequence mTitle;
//
//    // nav drawer title
//    private CharSequence mDrawerTitle;
//    private Menu menu;
//    private ProcessBuilder timer;
//
//    SharedPreferences sharedpreferences;
//    public static final String MyPREFERENCES = "MyPrefs";
//    SharedPreferences.Editor editor;
//    int i, j, k, l, m;
//    String lastnumber = "";
//    private String[] navMenuTitles;
//    private TypedArray navMenuIcons;
//    private ArrayList<NavDrawerItem> navDrawerItems;
//    private NavDrawerListAdapter adapter;
//
//    int itemSave = -1;
//    int itemselected = -1;
//
//
//    public static Tracking activity() {
//        return trackingact;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tracking);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        trackingact = this;
//
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
////        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
////                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
////        drawer.addDrawerListener(mDrawerToggle);
////        mDrawerToggle.syncState();
//
//
//
//
////        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
////        navigationView.setNavigationItemSelectedListener(this);
//
//
//
////        adRequest_banner = new AdRequest.Builder().build();
////        timer = new CounterClass(100000000, 40000);
//
////        timer.start();
//        mTitle = mDrawerTitle = getTitle();
//
//        // load slide menu items
//        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
//
//        // nav drawer icons from resources
//        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
//
//
//
//        navDrawerItems = new ArrayList<>();
//
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
//
//        alertForMobileNumber = new AlertDialog.Builder(this).create();
//        alertForMobileNumber.setTitle("Invalid number");
//        alertForMobileNumber.setMessage("Mobile number must be of atleast 10 characters");
//
//        // Recycle the typed array
//        navMenuIcons.recycle();
//
////        interstitial = new InterstitialAd(this);
////        interstitial.setAdUnitId(getResources().getString(R.string.adUnitId_intersitial));
////        interstitial.loadAd(adRequest_banner);
//
//
////        adView = new AdView(getApplicationContext());
////        adView.setAdSize(AdSize.BANNER);
////        adView.setAdUnitId(getResources().getString(R.string.adUnitId_banner));
////        adView.setBottom(Gravity.BOTTOM);
////        adView.loadAd(adRequest_banner);
//
//        // setting the nav drawer list adapter
//        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
//        mDrawerList.setAdapter(adapter);
//
//        // enabling action bar app icon and behaving it as toggle button
////        getActionBar().setDisplayHomeAsUpEnabled(true);
////        getActionBar().setHomeButtonEnabled(true);
////        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF0000")));
////        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF0000")));
////        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menuicon);
////        getSupportActionBar().setIcon(R.drawable.ic_drawer);
//
//        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(this, mDrawerLayout,
//                R.drawable.menuicon, //nav menu toggle icon
//                R.string.app_name, // nav drawer open - description for accessibility
//                R.string.app_name // nav drawer close - description for accessibility
//        ) {
//            public void onDrawerClosed(View view) {
//                getSupportActionBar().setTitle(mTitle);
//                // calling onPrepareOptionsMenu() to show action bar icons
//                invalidateOptionsMenu();
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                getSupportActionBar().setTitle(mDrawerTitle);
//                // calling onPrepareOptionsMenu() to hide action bar icons
//                invalidateOptionsMenu();
//            }
//        };
//        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
//
//        if (savedInstanceState == null) {
//
////            interstitial.show();
//            fragmentManager = getSupportFragmentManager();
//            mFragmentTransaction = fragmentManager.beginTransaction();
//
//            geofencingFragment = new GeofencingFragment();
//
//            mFragmentTransaction.add(R.id.map, geofencingFragment);
//            mFragmentTransaction.commit();
//            askGPSPermission();
//            askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
//        }
//        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
//
//
//
//    }
//
//    private void accessGPS() {
//
//        fragmentManager = getSupportFragmentManager();
//        mFragmentTransaction = fragmentManager.beginTransaction();
//
//        geofencingFragment = new GeofencingFragment();
//
//        mFragmentTransaction.add(R.id.relative, geofencingFragment);
//        mFragmentTransaction.commitAllowingStateLoss();
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    public void setvisiblemenu() {
////        interstitial.show();
//        menu.setGroupVisible(R.menu.tracking, true);
//    }
//
//    public void setinvisiblemenu() {
//        menu.setGroupVisible(R.menu.tracking, false);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.tracking, menu);
//        this.menu = menu;
//        return true;
//    }
//
//    @Override
//    protected void onStart() {
//        // TODO Auto-generated method stub
////		EasyTracker.getInstance(this).activityStart(this);
//        super.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        // TODO Auto-generated method stub
////        try {
////            timer.start();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        // TODO Auto-generated method stub
//        super.onDestroy();
//
//    }
//
//    @Override
//    protected void onStop() {
//        // TODO Auto-generated method stub
////        timer.cancel();
////		EasyTracker.getInstance(this).activityStop(this);
//        super.onStop();
//    }
//
//    private class SlideMenuClickListener implements ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            // display view for selected nav drawer item
//            displayView(position);
//        }
//    }
//
//    private void displayView(int position) {
//        switch (position) {
//            case 0:
//                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
//                View promptView = layoutInflater.inflate(R.layout.alert_target, null);
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//                alertDialogBuilder.setTitle("Mobile Number");
//
//                alertDialogBuilder.setView(promptView);
//
//                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                editor = sharedpreferences.edit();
//
//                final EditText input = (EditText) promptView.findViewById(R.id.alert_target_editText_phone);
//                if (sharedpreferences.contains("number")) {
//
//                    lastnumber = sharedpreferences.getString("number", lastnumber);
//                    input.setText(lastnumber);
//                } else {
//                    System.out.println("number is empty");
//                }
//
//
//                // setup a dialog window
//                alertDialogBuilder.setCancelable(false)
//
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int id) {
//                                if (input.length() < 10) {
//                                    alertForMobileNumber.setButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int which) {
//
//
//                                        }
//                                    });
//
//                                    alertForMobileNumber.show();
//                                    return;
//                                } else {
//                                    String s = input.getText().toString();
//
//                                    editor.putString("number", s);
//                                    editor.commit();
//                                }
//                            }
//                        })
//
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                alertDialogBuilder.show();
//                mDrawerLayout.closeDrawers();
//                break;
//            case 1:
//                final CharSequence[] items = {"Just once", "Twice in 10 mins", "Every 3 mins", "Every 10 mins", "Every hour"};
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Alert Geofence");
//
//                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                editor = sharedpreferences.edit();
//
//                int selected = sharedpreferences.getInt("item", -1);
//
//                itemSave = sharedpreferences.getInt("timer", -1);
//                itemselected = selected;
//
//                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        System.out.println("which " + itemselected);
//                        if (itemselected != -1) {
//                            editor.putInt("timer", itemSave);
//                            editor.putInt("item", itemselected);
//                            editor.commit();
//
//                            if (sharedpreferences.contains("number")) {
//                                lastnumber = sharedpreferences.getString("number", lastnumber);
//                                if (lastnumber.equals("")) {
//                                    System.out.println("number is but empty");
//                                    alertForMobileNumber.setTitle("Mobile Number Empty");
//                                    alertForMobileNumber.setMessage("Please first enter the Mobile Number");
//                                    alertForMobileNumber.setButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int which) {
//                                        }
//                                    });
//                                    alertForMobileNumber.show();
//                                } else {
//                                    Intent mIntent = new Intent(getApplicationContext(), MyService.class);
//                                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    mIntent.setAction("com.example.wearwhere.MyService");
//                                    if (isMyServiceRunning(MyService.class)) {
//                                        stopService(mIntent);
//                                        startService(mIntent);
//                                    } else {
//                                        startService(mIntent);
//                                    }
//                                }
//                            } else {
//                                alertForMobileNumber.setTitle("Mobile Number Empty");
//                                alertForMobileNumber.setMessage("Please first enter the Mobile Number");
//                                alertForMobileNumber.setButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(final DialogInterface dialog, final int which) {
//                                    }
//                                });
//                                alertForMobileNumber.show();
//                            }
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Select timer", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).setSingleChoiceItems(items, selected, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        if ("Just once".equals(items[which])) {
//                            itemSave = 3000000;
//                            itemselected = which;
//                        }
//                        if ("Twice in 10 mins".equals(items[which])) {
//                            itemSave = 300000;
//                            itemselected = which;
//                        } else if ("Every 3 mins".equals(items[which])) {
//                            itemSave = 180000;
//                            itemselected = which;
//                        } else if ("Every 10 mins".equals(items[which])) {
//                            itemSave = 600000;
//                            itemselected = which;
//                        } else if ("Every hour".equals(items[which])) {
//                            itemSave = 3600000;
//                            itemselected = which;
//                        }
//                    }
//                });
//
//                builder.show();
//                mDrawerLayout.closeDrawers();
//                break;
//            case 2:
//
////                interstitial.show();
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//
//                builder1.setTitle("Disable Geofence");
//                builder1.setMessage("Are you sure you want to disbale Geofence alerts?");
//
//                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                        Intent trollIntent = new Intent(getApplicationContext(), MyService.class);
//
//                        stopService(trollIntent);
//
//                    }
//                });
//
//                builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//                builder1.show();
//                mDrawerLayout.closeDrawers();
//                break;
//            case 3:
//                finish();
//                break;
//            default:
//                break;
//        }
//
//
//    }
//
//    private boolean isMyServiceRunning(Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
////            Log.e("isMyServiceRunning", "Service name: " + serviceClass.getName());
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // toggle nav drawer on selecting action bar app icon/title
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        // Handle action bar actions click
//        switch (item.getItemId()) {
//            case R.id.menu_currentLocation:
//                geofencingFragment.currentLocation();
//                return true;
//            case R.id.menu_showGeofence:
//                geofencingFragment.showGeofence();
//                return true;
//            case R.id.menu_setGeofence:
//                geofencingFragment.SetGeofence();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
////    @Override
////    public boolean onNavigationItemSelected(MenuItem item) {
////        // Handle navigation view item clicks here.
////        int id = item.getItemId();
////
////        if (id == R.id.nav_camera) {
////            // Handle the camera action
////        } else if (id == R.id.nav_gallery) {
////
////        } else if (id == R.id.nav_slideshow) {
////
////        }
////// else if (id == R.id.nav_manage) {
//////
//////        } else if (id == R.id.nav_share) {
//////
//////        } else if (id == R.id.nav_send) {
//////
//////        }
////
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
////        drawer.closeDrawer(GravityCompat.START);
////        return true;
////    }
//
//    /***
//     * Called when invalidateOptionsMenu() is triggered
//     */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // if nav drawer is opened, hide the action items
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public void setTitle(CharSequence title) {
//        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
//    }
//
//    /**
//     * When using the ActionBarDrawerToggle, you must call it during
//     * onPostCreate() and onConfigurationChanged()...
//     */
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
////        mDrawerToggle.syncState();
//    }
//
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Pass any configuration change to the drawer toggls
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//
//
//    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
//    @SuppressLint("NewApi")
//    public class CounterClass extends CountDownTimer {
//        public CounterClass(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onFinish() {
//
//        }
//
//        @SuppressLint("NewApi")
//        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
//        @Override
//        public void onTick(long millisUntilFinished) {
//
////            interstitial.loadAd(adRequest_banner);
////            interstitial.show();
//        }
//    }
//
//
//    final int GPS_PERMISSION_REQUEST_CODE = 99;
//
//    public void askGPSPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            if (ContextCompat.checkSelfPermission(Tracking.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//
//                if (ActivityCompat.shouldShowRequestPermissionRationale(Tracking.this,
//                        Manifest.permission.READ_CONTACTS)) {
//                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Tracking.this);
//                    builder.setTitle("Contacts access needed");
//                    builder.setPositiveButton(android.R.string.ok, null);
//                    builder.setMessage("Please confirm Contacts access");
//                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                        @TargetApi(Build.VERSION_CODES.M)
//                        @Override
//                        public void onDismiss(DialogInterface dialog) {
//                            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
//                                            Manifest.permission.ACCESS_COARSE_LOCATION}
//                                    , GPS_PERMISSION_REQUEST_CODE);
//                        }
//                    });
//                    builder.show();
//
//
//                } else {
//
//
//                    ActivityCompat.requestPermissions(Tracking.this,
//                            new String[]{Manifest.permission.READ_CONTACTS},
//                            GPS_PERMISSION_REQUEST_CODE);
//
//
//                }
//            } else {
//                accessGPS();
//            }
//        } else {
//            accessGPS();
//        }
//    }
//
//
//    //    @Override
//    public void onRequestPermissionsResult12(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case GPS_PERMISSION_REQUEST_CODE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    accessGPS();
//
//                } else {
//                    SetDiolog(Tracking.this, "App does not have permission to read Contacts");
//                }
//                return;
//            }
//
//        }
//    }
//
//
//    public static AlertDialog mAlertDialog;
//
//    public static void SetDiolog(final Activity activity, String message) {
////        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
//        mAlertDialog = new AlertDialog.Builder(activity).create();
//        mAlertDialog.setMessage(message);
//
//        mAlertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                mAlertDialog.dismiss();
//                activity.finish();
//            }
//        });
//        mAlertDialog.show();
//    }
//
//    static final Integer LOCATION = 0x1;
//    static final Integer SMS = 0x2;
//
//    private void askForPermission(String permission, Integer requestCode) {
//        if (ContextCompat.checkSelfPermission(Tracking.this, permission) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(Tracking.this, permission)) {
//
//                //This is called if user has denied the permission before
//                //In this case I am just asking the permission again
//                ActivityCompat.requestPermissions(Tracking.this, new String[]{permission}, requestCode);
//
//            } else {
//
//                ActivityCompat.requestPermissions(Tracking.this, new String[]{permission}, requestCode);
//            }
//        } else {
////            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
//            if (requestCode == LOCATION) {
//                askForPermission(Manifest.permission.SEND_SMS, SMS);
//            } else if (requestCode == SMS) {
//                accessGPS();
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
//            switch (requestCode) {
//                //Location
//                case 1:
////                    accessGPS();
//                    askForPermission(Manifest.permission.SEND_SMS, SMS);
//                    break;
//
//                case 2:
//                    accessGPS();
//                    break;
//                //Call
////                case 2:
////                    Intent callIntent = new Intent(Intent.ACTION_CALL);
////                    callIntent.setData(Uri.parse("tel:" + "{This is a telephone number}"));
////                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
////                        startActivity(callIntent);
////                    }
////                    break;
//                //Write external Storage
////                case 3:
////                    break;
////                //Read External Storage
////                case 4:
////                    Intent imageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                    startActivityForResult(imageIntent, 11);
////                    break;
////                //Camera
////                case 5:
////                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
////                        startActivityForResult(takePictureIntent, 12);
////                    }
////                    break;
////                //Accounts
////                case 6:
////                    AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
////                    Account[] list = manager.getAccounts();
////                    Toast.makeText(this,""+list[0].name,Toast.LENGTH_SHORT).show();
////                    for(int i=0; i<list.length;i++){
////                        Log.e("Account "+i,""+list[i].name);
////                    }
//            }
//
//            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
//        } else {
////            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//
//            String per = null;
//            for (int i = 0; i < permissions.length; i++) {
//                if (permissions.length == 1)
//                    per += permissions[i];
//                else if ((permissions.length - 1) == i)
//                    per += permissions[i];
//                else
//                    per += permissions[i] + ", ";
//            }
//
//            SetDiolog(Tracking.this, "App does not have permission to " + per);
//        }
//    }
//}
