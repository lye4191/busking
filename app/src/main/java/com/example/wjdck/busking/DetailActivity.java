package com.example.wjdck.busking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void donationbutton(View v){
        Intent intent = new Intent(DetailActivity.this, Duration.class);
        startActivity(intent);

    }
}
