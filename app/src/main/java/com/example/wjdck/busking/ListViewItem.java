package com.example.wjdck.busking;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private Drawable iconDrawable;
    private String nameStr;
    private String addrStr;
    private String onairStr;

    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }

    public void setTitle(String name){
        nameStr = name;
    }

    public void setAddr(String addr){
        addrStr = addr;
    }

    public void setOnair(String onair){
        onairStr = onair;
    }

    public Drawable getIcon(){
        return this.iconDrawable;
    }

    public String getTitle(){
        return this.nameStr;
    }

    public String getAddr(){
        return this.addrStr;
    }

    public String getOnair(){
        return this.onairStr;
    }

}
