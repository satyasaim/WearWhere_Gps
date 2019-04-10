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

/**
 * Created by User on 5/10/2017.
 */

public class LeftMenuListViewAdapter extends BaseAdapter {

    Context context;
    private ArrayList<LeftMenuListConst> leftMenuListConst;



    public LeftMenuListViewAdapter(Context context, ArrayList<LeftMenuListConst> leftMenuListConst) {
        //super(context,leftMenuListConst);
        this.context=context;
        this.leftMenuListConst=leftMenuListConst;
    }

    /*private view holder class*/
    class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        // TextView txtlocation;
    }

    @Override
    public int getCount() {
        return leftMenuListConst.size();
    }

    @Override
    public Object getItem(int position) {
        return leftMenuListConst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LeftMenuListViewAdapter.ViewHolder holder = null;
        LeftMenuListConst leftMenuListConst = (LeftMenuListConst) getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.leftmenu, null);
            holder = new LeftMenuListViewAdapter.ViewHolder();
            //  holder.txtlocation = (TextView) convertView.findViewById(R.id.tv_shoping);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.tv_menu_item);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_menuicon);
            convertView.setTag(holder);
        } else
            holder = (LeftMenuListViewAdapter.ViewHolder) convertView.getTag();

        // holder.txtlocation.setText(shopListConsts.getlocation());
        holder.txtTitle.setText(leftMenuListConst.getTitle());
       holder.imageView.setImageResource( leftMenuListConst.getImageId());

//    if (position==0){
//        convertView.setBackgroundResource( R.color.list_row_6 );
//    }else if (position==1){
//        convertView.setBackgroundResource( R.color.list_row_10 );
//    }else if (position==2){
//        convertView.setBackgroundResource( R.color.list_row_5 );
//    }else if (position==3){
//        convertView.setBackgroundResource( R.color.list_row_2 );
//    }else if (position==4){
//        convertView.setBackgroundResource( R.color.list_row_1 );
//    }

        return convertView;
    }
}