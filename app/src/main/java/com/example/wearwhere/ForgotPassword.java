package com.example.wearwhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import javax.xml.validation.Validator;

public class ForgotPassword extends AppCompatActivity {

    ImageView iv_back;

    EditText et_email_forgotpass;
    Button but_send_forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        iv_back = (ImageView)findViewById(R.id.iv_back);
        et_email_forgotpass = (EditText)findViewById(R.id.et_email_forgotpass);
        but_send_forgotpass = (Button)findViewById(R.id.but_send_forgotpass);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotologin = new Intent(ForgotPassword.this,LoginPage.class);
                startActivity(gotologin);
            }
        });

        but_send_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_email_forgotpass.getText().toString().length()==0){

                    Toast.makeText(ForgotPassword.this, "Please Enter Your Email-Id", Toast.LENGTH_SHORT).show();

                }else {

                    Intent gotolg = new Intent(ForgotPassword.this,LoginPage.class);
                    startActivity(gotolg);
                }
            }
        });
    }
}
