package com.example.wjdck.busking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity {

    Button btn_donation = (Button)this.findViewById(R.id.donationbutton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btn_donation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, Duration.class);
                startActivity(intent);
            }
        });
    }
}
