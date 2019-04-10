package com.example.wearwhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SettingsPage extends AppCompatActivity {
    SlidingMenu slidingMenu;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        menu = (ImageView)findViewById(R.id.menu);

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setBehindOffsetRes(R.dimen.slide_menu_width);
        slidingMenu.setFadeDegree(0.20f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.leftmenufull);

        // Common.SlideMenuDesign(slidingMenu, TabEvents.this, getResources().getString(R.string.jobcom_jobcreate));
        Common.SlideMenuDesign(slidingMenu,SettingsPage.this,"home");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingMenu.toggle();


            }
        });
    }
}
