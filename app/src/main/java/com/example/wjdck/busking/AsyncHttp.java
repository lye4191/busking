package com.example.wjdck.busking;


import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;


public class AsyncHttp extends AsyncTask<Void, Void, String> {

    private String url = "http://dapi.kakao.com/v2/local/search/keyword.json?query=";
    private Context context;

    public AsyncHttp() {
        super();
    }

    public AsyncHttp(Context context, String url) {
        this.context = context;
        this.url += url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result;
        WebConnection webConnection = new WebConnection();
        result = webConnection.request(context, url);

        return result;
    }
}
