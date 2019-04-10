package com.example.wearwhere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;

import java.util.regex.Pattern;

//public class TextMessageBroadcastReceiver extends BroadcastReceiver{
//
//	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
//	private static final String TAG = "SMSBroadcastReceiver";
//	SharedPreferences sharedpreferences;
//	public static final String MyPREFERENCES = "MyPrefs" ;
//	Editor editor;
//	String strMessage = "";
//	String [] strFromMessageReceived;
//
//	String latFromMessage="",lonFromMessage="",contentFromMesaage="";
//
//	double lat,lon;
//
//	@Override
//	public void onReceive(Context arg0, Intent intent) {
//		// TODO Auto-generated method stub
//
//		Bundle myBundle = intent.getExtras();
//		SmsMessage [] messages = null;
//		String strMessage = "";
//		String newnumber="",oldNumber="";
//
//		System.out.println("in broadcast");
//
//		sharedpreferences = arg0.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//		editor = sharedpreferences.edit();
//
//		Log.i(TAG, "Intent recieved: " + intent.getAction());
//		if (intent.getAction().equalsIgnoreCase(SMS_RECEIVED))
//		{
//			Object[] pdus = (Object[])myBundle.get("pdus");
//			messages= new SmsMessage[pdus.length];
//
//			for (int i = 0; i < messages.length; i++)
//			{
//				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//				newnumber=messages[i].getOriginatingAddress();
//				strMessage += messages[i].getMessageBody();
//				strMessage += "\n";
//			}
//		}
//		if(strMessage.contains("maggytracker"))
//		{
//			String decimalPattern = "([0-9]*)\\.([0-9]*)";
//			//editor.putString("new number", newnumber);
//			strFromMessageReceived=strMessage.split(":");
//			if(strFromMessageReceived.length > 2)
//			{
//				contentFromMesaage=strFromMessageReceived[0];
//
//				latFromMessage=strFromMessageReceived[1].trim();
//				lonFromMessage=strFromMessageReceived[2].trim();
//
//				if(Pattern.matches(decimalPattern,latFromMessage) && Pattern.matches(decimalPattern,lonFromMessage))
//				{
//					lat=Double.valueOf(latFromMessage);
//					lon=Double.valueOf(lonFromMessage);
//
//					editor.putFloat("latitude",(float)lat);
//					editor.putFloat("longitude",(float)lon);
//					editor.commit();
//
//					if(Home.activity() != null)
//					{
//						Home.activity().geofencingFragment.setUpMap();
//					}
//
//				}
//			}
//		}
//		else
//		{
//			System.out.println("put receiver blank in sharedpreferene from braodcast "+newnumber);
//		}
//	}
//}
//
//
