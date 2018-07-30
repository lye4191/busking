package com.example.wjdck.busking;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private Drawable iconDrawable;
    private String nameStr;
    private String addrStr;

    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }

    public void setTitle(String name){
        nameStr = name;
    }

    public void setAddr(String addr){
        addrStr = addr;
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


}
