package com.example.wjdck.busking;

import android.media.Image;

class Busk{
    public String roomname; //이런식으로 데이터에 구분할수있는 값 넣어줘야댐 아~~~ 그 예를들어서 roomnum이런식으로 1 2 3 카웅ㄴㅌ잉해서 그 child(roomname) 이런식으로?ㅇㅇ
    //근데 이거 이런방법도있음
    public String name;
    public String locate;
    public String time;
    public String description;
    public String genre;
    public String image;

    public Busk(String name, String locate, String time, String description, String genre){
        this.name = name;
        this.locate = locate;
        this.time = time;
        this.description = description;
        this.genre = genre;
    }

    public Busk(String name, String locate, String time, String description, String genre, String image){
        this.name = name;
        this.locate = locate;
        this.time = time;
        this.description = description;
        this.genre = genre;
        this.image = image;
    }

}
