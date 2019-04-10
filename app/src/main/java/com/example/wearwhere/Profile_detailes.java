package com.example.wearwhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Profile_detailes extends AppCompatActivity {

    EditText et_name_device,et_imei_device,et_password_device;
    Button btu_save_device;
    ImageView iv_back_profile_detailes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detailes);

        et_name_device = (EditText)findViewById(R.id.et_name_device);
        et_imei_device = (EditText)findViewById(R.id.et_imei_device);
        et_password_device = (EditText)findViewById(R.id.et_password_device);
        btu_save_device = (Button) findViewById(R.id.btu_save_device);
        iv_back_profile_detailes = (ImageView) findViewById(R.id.iv_back_profile_detailes);

        btu_save_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoprofile= new Intent(Profile_detailes.this,ProfileScreen.class);
                startActivity(gotoprofile);
            }
        });

        iv_back_profile_detailes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback = new Intent(Profile_detailes.this,ProfileScreen.class);
                startActivity(goback);
            }
        });

    }
}
