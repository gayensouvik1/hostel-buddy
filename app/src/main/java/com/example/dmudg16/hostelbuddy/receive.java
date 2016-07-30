package com.example.dmudg16.hostelbuddy;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmudg16 on 2/25/2016.
 */
public class receive extends AsyncTask  {
    InputStream is;
    ProgressDialog dialog;
    String json = "";
    String category="";
HomePage  hp;
public receive(HomePage hp){
this.hp=hp;
}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
         dialog = new ProgressDialog(hp);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();

    }

    protected Object doInBackground(Object[] params) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        String paramString = URLEncodedUtils.format(param, "utf-8");
        String url = "http://dmudg16.netau.net/allproducts.php";
       // url += "?" + paramString;
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString()+"done";

            //Intent i=new Intent("com.example.dmudg16.hostelbuddy.complaints");
            //i.putExtra(json,true);

        } catch (IOException e) {

        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        dialog.dismiss();
        hp.settext(json);
    }
}