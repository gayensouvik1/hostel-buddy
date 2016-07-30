package com.example.dmudg16.hostelbuddy;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmudg16 on 2/18/2016.
 */
public class sender extends AsyncTask<String, Void,Long > {String a,b,c;
    ProblemPage s;
    ProgressDialog dialog;
    public sender(ProblemPage s){
        this.s=s;
    }
public void setdata(String a,String b,String c){
this.a=a;
    this.b=b;this.c=c;
}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(s);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected Long doInBackground(String... params) {
        HttpClient client=new DefaultHttpClient();
        HttpPost post=new HttpPost("http://dmudg16.netau.net/phpcode.php");
        List<NameValuePair> mylist=new ArrayList<NameValuePair>(2);

            mylist.add(new BasicNameValuePair("name", a));
            mylist.add(new BasicNameValuePair("complaint",b));
            mylist.add(new BasicNameValuePair("category",c));
           try {
               post.setEntity(new UrlEncodedFormEntity(mylist));
               HttpResponse response = client.execute(post);
               
           }
           catch(Exception e){
           System.out.print("unsuccessful");
           }
        return null;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        dialog.dismiss();
        s.written();
    }
}
