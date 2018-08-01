package com.example.wjdck.busking;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class GwangActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwang);

        ListView listview = (ListView) findViewById(R.id.busker_listview_g);
        ListVIewAdapter adapter;
        final EditText keyword = (EditText) findViewById(R.id.keyword_g);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_g);

        /*
        Intent intent = getIntent();
        String item = intent.getStringExtra("selected_item");

        Log.d("item", item);
        */
        adapter = new ListVIewAdapter();

        listview.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.busker), "MAP", "광안리 해수욕장" ,"공연 예정");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String nameStr = item.getTitle();
                String addrStr = item.getAddr();
                Drawable iconDrawable = item.getIcon();

                Intent intent = new Intent(GwangActivity.this, DetailActivity.class);
                startActivity(intent);
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

        //Intent intent = getIntent();
        //String item = intent.getExtras().getString("selected_item");
        //keyword.setText(item);



    }
    public void search(View v){

        editText = (EditText)findViewById(R.id.keyword_g);
        String userStr = editText.getText().toString();
        Intent intent = new Intent(GwangActivity.this, SearchActivity.class);
        intent.putExtra("url", userStr);
        startActivity(intent);
    }

    public void fromMyLocation(View v){

    }

    public void addClick(View v){

        Intent intent = new Intent(GwangActivity.this, AddbuskActivity.class);
        startActivity(intent);


    }



}
