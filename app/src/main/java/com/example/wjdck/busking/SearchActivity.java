package com.example.wjdck.busking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        ListView listview = (ListView) findViewById(R.id.addr_listview);
        List<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selected_item = (String)adapterView.getItemAtPosition(position);

                //텍스트뷰에 출력
                //selected_item_textview.setText(selected_item);
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("selected_item", selected_item);

                startActivity(intent);

                //EditText edit = (EditText) findViewById(R.id.keyword);
                //edit.setText(selected_item);
                finish();

            }
        });

        Log.d("url 상황", url);
        try {
            AsyncHttp asyncHttp = new AsyncHttp(this, url);
            String result = asyncHttp.execute().get();
            Log.d("Result", result);

            JSONObject jsonObject = new JSONObject(result);
            JSONArray documents = jsonObject.getJSONArray("documents");
            for(int i=0; i<documents.length(); i++) {
                JSONObject json = documents.getJSONObject(i);
                String road_address_name = json.getString("road_address_name");
                String place_name = json.getString("place_name");
                list.add(place_name);

                Log.d("############road", road_address_name);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void search_s(View v){

        EditText editText = (EditText)findViewById(R.id.keyword);
        String userStr = editText.getText().toString();

        //Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        //intent.putExtra("url", userStr);
        //startActivity(intent);
    }

    public void fromMyLocation_s(View v){

    }


}
