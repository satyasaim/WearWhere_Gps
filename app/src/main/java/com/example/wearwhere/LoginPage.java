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
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginPage extends AppCompatActivity {

    TextView tv_signup_login,tv_forgot_login;

    Button but_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);



        tv_signup_login = (TextView)findViewById(R.id.tv_signup_login);
        tv_forgot_login = (TextView)findViewById(R.id.tv_forgot_login);

        tv_signup_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotologin = new Intent(LoginPage.this,SignupPage.class);
                startActivity(gotologin);
            }
        });

        tv_forgot_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoforgot = new Intent(LoginPage.this,ForgotPassword.class);
                startActivity(gotoforgot);
            }
        });

        but_login = (Button)findViewById(R.id.but_login);

        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotohome = new Intent(LoginPage.this,Home.class);
                startActivity(gotohome);
            }
        });
    }


    public void onclick(View view) {
    }
}
