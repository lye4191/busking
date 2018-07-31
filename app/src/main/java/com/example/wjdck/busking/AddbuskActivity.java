package com.example.wjdck.busking;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
        Button btn_attach = (Button)this.findViewById(R.id.attach_btn);
        final EditText edit_name = (EditText) this.findViewById(R.id.name_edit);
        final EditText edit_locate = (EditText) this.findViewById(R.id.locate_edit);
        final EditText edit_time = (EditText) this.findViewById(R.id.time_edit);
        final EditText edit_description = (EditText) this.findViewById(R.id.description_edit);
        final EditText edit_genre = (EditText) this.findViewById(R.id.genre_edit);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("busks1");// 여기 busks를 방 이름을 ㅗ치면

        DatabaseReference myRef = ref.getRoot();

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
                //onBackPressed();
            }
        });

        btn_attach.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                
            }
        });



    }

}
