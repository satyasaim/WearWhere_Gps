package com.example.wearwhere;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//public class NavDrawerListAdapter extends BaseAdapter {
//
//   private Context context;
//   private ArrayList<NavDrawerItem> navDrawerItems;
//
//   int i = 0;
//   public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
//       this.context = context;
//       this.navDrawerItems = navDrawerItems;
//   }
//
//   @Override
//   public int getCount() {
//       return navDrawerItems.size();
//   }
//
//   @Override
//   public Object getItem(int position) {
//       return navDrawerItems.get(position);
//   }
//
//   @Override
//   public long getItemId(int position) {
//       return position;
//   }
//
//   @Override
//   public View getView(final int position, View convertView, ViewGroup parent) {
//       if (convertView == null) {
//           LayoutInflater mInflater = (LayoutInflater)
//                   context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//           convertView = mInflater.inflate(R.layout.drawer_list_item, null);
//       }
//
//       ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
//       final TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
//
//       txtTitle.setText(navDrawerItems.get(position).getTitle());
//       imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
//
//
//
//       return convertView;
//   }
//
//
//
//}
