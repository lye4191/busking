package com.example.wjdck.busking;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/**
 * Created by rlarh on 2018-01-25.
 */

public class WebConnection {
    public String request(final Context context, String _url){

        // HttpURLConnection 참조 변수.
        HttpURLConnection urlConn = null;
        // URL 뒤에 붙여서 보낼 파라미터.
        StringBuffer sbParams = new StringBuffer();

        /**
         * 2. HttpURLConnection을 통해 web의 데이터를 가져온다.
         * */
        try{
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            // [2-1]. urlConn 설정.
            urlConn.setRequestMethod("GET"); // URL 요청에 대한 메소드 설정 : POST.
            urlConn.setRequestProperty("Cache-Control", "no-cache"); // Accept-Charset 설정.
            urlConn.setRequestProperty("Accept-Charset", "utf-8");
            urlConn.setRequestProperty("Authorization", "KakaoAK e916e007bd9d14b1096ab8ae52dbb09e");
            urlConn.setDoInput(true);
            urlConn.setConnectTimeout(2 * 1000);

            // [2-3]. 연결 요청 확인.
            // 실패 시 null을 리턴하고 메서드를 종료.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;

            // [2-4]. 읽어온 결과물 리턴.
            // 요청한 URL의 출력물을 BufferedReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

            // 출력물의 라인과 그 합에 대한 변수.
            String line;
            String result = "";

            // 라인을 받아와 합친다.
            while ((line = reader.readLine()) != null){
                result += line;
            }

            return result;

        } catch (SocketTimeoutException e) {
           e.printStackTrace();
        } catch (MalformedURLException e) { // for URL.
            e.printStackTrace();
        } catch (IOException e) { // for openConnection().
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }

        return null;

    }
}
