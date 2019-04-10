package com.example.wearwhere;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 * Created by User on 5/10/2017.
 */
public class Common {

    private static ListView mDrawerList;
    private static ArrayList<LeftMenuListConst> leftMenuListConst;
    private static String[] navMenuList;
    private static LeftMenuListViewAdapter leftMenuListViewAdapter;
    private static TypedArray navMenuIcons;
    private static ImageView iv_ProfilePic,iv_back;
    private static TextView menu_pic_title;
    private static SharedPreferences sharedPreferences;
    private static String userName;
    @SuppressLint("ResourceType")
    public static void SlideMenuDesign(final SlidingMenu slidingMenu, final Activity activity, final String clickMenu) {



        //menu list items
        navMenuList = slidingMenu.getResources().getStringArray(R.array.menu_list);
        // nav drawer icons from resources
        navMenuIcons = activity.getResources().obtainTypedArray(R.array.menu_images);
        //  mDrawerLayout = (RelativeLayout) activity.findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) slidingMenu.findViewById(R.id.list_nav1);

        leftMenuListConst=new ArrayList<LeftMenuListConst>();
        leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(0, -1),navMenuList[0]));
        leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(1, -1),navMenuList[1]));
        leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(2, -1),navMenuList[2]));
        leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(3, -1),navMenuList[3]));
        leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(4, -1),navMenuList[4]));
//        leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(5, -1),navMenuList[5]));
   //     leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(6, -1),navMenuList[6]));
    //    leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(7, -1),navMenuList[7]));
        //leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(8, -1),navMenuList[8]));
        //leftMenuListConst.add(new LeftMenuListConst(navMenuIcons.getResourceId(9, -1),navMenuList[9]));
        leftMenuListViewAdapter=new LeftMenuListViewAdapter(activity,leftMenuListConst);
        mDrawerList.setAdapter(leftMenuListViewAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:

                        slidingMenu.toggle();
                        if(!clickMenu.equals("profile")){
                            Intent profile = new Intent(activity, Home.class);
                            activity.startActivity(profile);
                            //activity.finish();

                        }
                        break;
                    case 1:
                        slidingMenu.toggle();
                        if(!clickMenu.equals("Events")) {
                            Intent Payment = new Intent(activity, ProfileScreen.class);
                            activity.startActivity(Payment);
                            //activity.finish();
                        }

                        break;
                    case 2:

                        slidingMenu.toggle();
                        if(!clickMenu.equals("Profile")) {
                            Intent Profile = new Intent(activity, Home.class);
                            activity.startActivity(Profile);
                            //activity.finish();
                        }

                        break;


                    case 3:

                        slidingMenu.toggle();
                        if(!clickMenu.equals("Settings")) {
                            Intent Settings = new Intent(activity, SettingsPage.class);
                            activity.startActivity(Settings);
                            //activity.finish();
                        }

                        break;




                    case 4:

                        slidingMenu.toggle();
                        if(!clickMenu.equals("SignOut")) {
                            Intent reportsactivity = new Intent(activity, MainActivity.class);
                            activity.startActivity(reportsactivity);

                            //activity.finish();
                        }

                        break;
//                    case 5:
//
//                        slidingMenu.toggle();
//                        if(!clickMenu.equals("InviteaFriend")) {
//                            Intent messageactivity = new Intent(activity, TabEvents.class);
//                            activity.startActivity(messageactivity);
//                           // activity.finish();
//                        }
//                        break;
                   /* case 6:
                        slidingMenu.toggle();
                        if(!clickMenu.equals("settingsactivity")) {
                            Intent settingsactivity = new Intent(activity, Settingspage.class);
                            activity.startActivity(settingsactivity);
                          //  activity.finish();
                        }

                        break;*/
                    /*case 7:

                        slidingMenu.toggle();
                        if(!clickMenu.equals("support")) {
                            Intent support = new Intent(activity, TabEvents.class);
                            activity.startActivity(support);
                           /// activity.finish();
                        }



                     /*   sharedPreferences= activity.getSharedPreferences("LoginDetails",activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("_id","");
                        editor.putString("userName", "");
                        editor.putString("password", "");
                        editor.putString("loginType", "");
                        editor.putString("mobile","");
                        editor.putString("email","");
                        editor.putString("dob","");
                        editor.putString("gender","");
                        editor.putString("refMobile","");
                        editor.putString("userProfileImage","");
                        editor.putString("address","");
                        editor.putString("city","");
                        editor.putString("state","");
                        editor.putString("postalCode","");
                        editor.putString("country","");
                        editor.putString("userRankRewards","");
                        editor.putString("emailVerification","");
                        editor.commit();
                        Intent in=new Intent(activity,LoginScreen.class);
                        activity.startActivity(in);
                        activity.finish();
                        break;*/



                }
            }
        });

    }


}
