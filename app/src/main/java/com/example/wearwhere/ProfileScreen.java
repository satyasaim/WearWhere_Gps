package com.example.wearwhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ProfileScreen extends AppCompatActivity {

    SlidingMenu slidingMenu;
    ImageView menu,iv_add_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        menu = (ImageView)findViewById(R.id.menu);

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setBehindOffsetRes(R.dimen.slide_menu_width);
        slidingMenu.setFadeDegree(0.20f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.leftmenufull);

        // Common.SlideMenuDesign(slidingMenu, TabEvents.this, getResources().getString(R.string.jobcom_jobcreate));
        Common.SlideMenuDesign(slidingMenu,ProfileScreen.this,"home");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingMenu.toggle();


            }
        });

        iv_add_profile = (ImageView)findViewById(R.id.iv_add_profile);


        iv_add_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotopd=new Intent(ProfileScreen.this,Profile_detailes.class);
                startActivity(gotopd);

            }
        });
    }
}
