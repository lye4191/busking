package com.example.wjdck.busking;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

class Busk{
    public String name;
    public String locate;
    public String time;
    public String description;
    public String genre;
    public Image image;

    public Busk(String name, String locate, String time, String description, String genre){
        this.name = name;
        this.locate = locate;
        this.time = time;
        this.description = description;
        this.genre = genre;
    }

    public Busk(String name, String locate, String time, String description, String genre, Image image){
        this.name = name;
        this.locate = locate;
        this.time = time;
        this.description = description;
        this.genre = genre;
        this.image = image;
    }

}

public class AddbuskActivity extends AppCompatActivity {
    //데이터 베이스
    private FirebaseDatabase database;
    private DatabaseReference ref;

    //정보 담을 객체
    List<Busk> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbusk);

        Button btn_cancle = (Button)this.findViewById(R.id.cancle_btn);
        Button btn_regist = (Button)this.findViewById(R.id.regist_btn);
        final EditText edit_name = (EditText) this.findViewById(R.id.name_edit);
        final EditText edit_locate = (EditText) this.findViewById(R.id.locate_edit);
        final EditText edit_time = (EditText) this.findViewById(R.id.time_edit);
        final EditText edit_description = (EditText) this.findViewById(R.id.description_edit);
        final EditText edit_genre = (EditText) this.findViewById(R.id.genre_edit);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("busks");

        btn_cancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString();
                String locate = edit_locate.getText().toString();
                String time = edit_time.getText().toString();
                String description = edit_description.getText().toString();
                String genre = edit_genre.getText().toString();

                Busk busk = new Busk(name, locate, time, description, genre);

                ref.push().setValue(busk);
            }
        });

    }

}
