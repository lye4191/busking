package com.example.wjdck.busking;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListVIewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListVIewAdapter(){

    }

    @Override
    public int getCount(){
        return listViewItemList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.busker_image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.busker_name);
        TextView addrTextView = (TextView) convertView.findViewById(R.id.busker_address);
        TextView onairTextview = (TextView) convertView.findViewById(R.id.busker_onair);


        ListViewItem listViewItem = listViewItemList.get(position);

        iconImageView.setImageDrawable(listViewItem.getIcon());
        nameTextView.setText(listViewItem.getTitle());
        addrTextView.setText(listViewItem.getAddr());
        onairTextview.setText(listViewItem.getOnair());

        return convertView;

    }


    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    public void addItem(Drawable icon, String name, String addr, String onair){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(name);
        item.setAddr(addr);
        item.setOnair(onair);

        listViewItemList.add(item);
    }



}
