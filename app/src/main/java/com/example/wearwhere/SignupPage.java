package com.example.wearwhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupPage extends AppCompatActivity {

    EditText et_name_signup,et_email_signup,et_phone_singup,et_Password_signup,et_conpassword_sign;
    Button but_signup;
    TextView tv_login_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        et_name_signup = (EditText)findViewById(R.id.et_name_signup);
        et_email_signup = (EditText)findViewById(R.id.et_email_signup);
        et_phone_singup = (EditText)findViewById(R.id.et_phone_singup);
        et_Password_signup = (EditText)findViewById(R.id.et_Password_signup);
        et_conpassword_sign = (EditText)findViewById(R.id.et_conpassword_sign);
        tv_login_signup = (TextView)findViewById(R.id.tv_login_signup);
        but_signup = (Button)findViewById(R.id.but_signup);

        but_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotolg = new Intent(SignupPage.this,LoginPage.class);
                startActivity(gotolg);
            }
        });

        tv_login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologin = new Intent(SignupPage.this,LoginPage.class);
                startActivity(gotologin);
            }
        });
    }
}
