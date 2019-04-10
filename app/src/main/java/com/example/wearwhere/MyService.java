package com.example.wearwhere;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    Timer timer = new Timer();
    private Context ctx;

    public int i = 0;
    float lat, lon, radius;
    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs";
    GPSTracker mGpsTracker;
    String str;

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    BroadcastReceiver mReceiver = null;
    //private static final String TAG = "SMSBroadcastReceiver";

    String messages = "", newnumber = "", oldnumber = "";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        System.out.println("Service is started");

        ctx = this;
        mGpsTracker = new GPSTracker(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        IntentFilter filter = new IntentFilter(SMS_RECEIVED);
        filter.addAction(SMS_RECEIVED);
//        mReceiver = new TextMessageBroadcastReceiver();
        try {
            registerReceiver(mReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        timer = new Timer();
        if (sharedpreferences.contains("timer")) {
            i = sharedpreferences.getInt("timer", 0);
            timer.scheduleAtFixedRate(new mainTask(), i, i);
        }
        if (sharedpreferences.contains("latitude")) {
            lat = sharedpreferences.getFloat("latitude", 0);
            System.out.println("latitude in service" + lat);
        }
        if (sharedpreferences.contains("longitude")) {
            lon = sharedpreferences.getFloat("longitude", 0);
            System.out.println("longitude in service" + lon);
        }
        if (sharedpreferences.contains("radius")) {
            radius = sharedpreferences.getFloat("radius", 0);
            System.out.println("radius in service" + radius);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private class mainTask extends TimerTask {

        public mainTask() {

            // TODO Auto-generated constructor stub
        }

        public void run() {
            toastHandler.sendEmptyMessage(0);
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        System.out.println("service stopped");
        timer.cancel();
    }

    private final Handler toastHandler = new Handler() {
        @SuppressWarnings("deprecation")
        @Override
        public void handleMessage(Message msg) {


            System.out.println("service called");
            float[] results = new float[2];

            if (sharedpreferences.contains("latitude")) {
                lat = sharedpreferences.getFloat("latitude", 0);
                System.out.println("latitude in service" + lat);
            }
            if (sharedpreferences.contains("longitude")) {
                lon = sharedpreferences.getFloat("longitude", 0);
                System.out.println("longitude in service" + lon);
            }
            if (sharedpreferences.contains("radius")) {
                radius = sharedpreferences.getFloat("radius", 0);
                System.out.println("radius in service" + radius);
            }
            if (sharedpreferences.contains("number")) {
                str = sharedpreferences.getString("number", str);
                System.out.println("mobile number  " + str);
            }


            Location.distanceBetween(mGpsTracker.latitude, mGpsTracker.longitude,
                    lat, lon, results);

            float end = results[0];

            if (!str.equals("")) {
                if (end > radius) {
                    try {

                        String uri = "http://maps.google.com/maps?q=" + mGpsTracker.latitude + "," + mGpsTracker.longitude;
                        StringBuffer smsBody = new StringBuffer();

                        smsBody.append(Uri.parse(uri) + "   " + "Maggy tracker is outside of the defined geofence");
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(str, null, smsBody.toString(), null, null);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "SMS faild!",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                } else
                    Toast.makeText(getApplicationContext(), "Maggy tracker is inside of the defined geofence", Toast.LENGTH_SHORT).show();
            }
        }

    };

}
