package com.example.wearwhere;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    Button but_signup_main,but_login_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Main code starts here

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.wearwhere", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        but_signup_main = (Button)findViewById(R.id.but_signup_main);

        but_login_main = (Button)findViewById(R.id.but_login_main);

        but_login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent  gotologin= new Intent(MainActivity.this, LoginPage.class);
                startActivity(gotologin);
            }
        });

        but_signup_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosign= new Intent(MainActivity.this,SignupPage.class);
                startActivity(gotosign);
            }
        });
    }





}
