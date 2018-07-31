package com.example.wjdck.busking;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LocationThread extends Thread{
    String urlStr;

    LocationThread thread =  null;
    Handler handler = new Handler();
    int jsonResultsLength = 0;
    public static String defaultUrl = "https://dapi.kakao.net/v2/local/search/category.json?query=광안리";

    //public static String defaultUrl = "https://dapi.kakao.net/v2/local/search/address.json?query=전북 삼성동 100";


    String lats[];
    String lngs[];
    String addrs[];


    public LocationThread(String inStr) { urlStr = inStr;}

    public void run(){
        try{
            final String output = request(urlStr);
            handler.post(new Runnable(){
                @Override
                public void run(){
                    findLatLng(output);
                }
            });
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private String request(String urlStr){
        StringBuilder output = new StringBuilder();

        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.setRequestProperty("Authorization", "KakaoAK e916e007bd9d14b1096ab8ae52dbb09e");


                int resCode = conn.getResponseCode();

                Log.d("resCode", String.valueOf(resCode));

                if(resCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }
                        output.append(line + "\n");
                    }

                    reader.close();
                    conn.disconnect();
                }
            }
        }catch(Exception ex){
            Log.e("SampleHTTP", "Exception in processiont response.", ex);
            ex.printStackTrace();
        }

        return output.toString();
    }

    private void findLatLng(String output){
        Log.d("output", output);
        try{
            JSONObject jsonObject = new JSONObject(output);
            JSONObject channel = new JSONObject(jsonObject.getString("channel"));
            JSONArray items = new JSONArray(channel.getString("item"));
            jsonResultsLength = items.length();

            if(jsonResultsLength > 0){
                addrs = new String[jsonResultsLength];
                lats = new String[jsonResultsLength];
                lngs = new String[jsonResultsLength];

                for(int i = 0; i < jsonResultsLength; i++){
                    String addr = items.getJSONObject(i).getString("title");
                    String lat = items.getJSONObject(i).getString("latitude");
                    String lng = items.getJSONObject(i).getString("longitude");
                    addrs[i] = addr;
                    lats[i] = lat;
                    lngs[i] = lng;
                }

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
