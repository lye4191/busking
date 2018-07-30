package com.example.wjdck.busking;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.busker_listview);
        ListVIewAdapter adapter;
        final EditText keyword = (EditText) findViewById(R.id.keyword);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        adapter = new ListVIewAdapter();

        listview.setAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.busker), "MAP", "광안리 해수욕장" ,"공연 예정");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.busker1), "WHO", "부산항 컨벤션센터","공연 중");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.busker1), "ICT", "부산대학교 제도관","공연 종료");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String nameStr = item.getTitle();
                String addrStr = item.getAddr();
                Drawable iconDrawable = item.getIcon();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if (i == 1) keyword.setHint("버스커 이름으로 검색");
                else if (i == 0) keyword.setHint("지번, 도로명, 건물명으로 검색");
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });



    }
    public void search(View v){

    }

    public void fromMyLocation(View v){

    }

    public void addClick(View v){

        Intent intent = new Intent(MainActivity.this, AddbuskActivity.class);
        startActivity(intent);


    }



}
