package com.example.dmudg16.hostelbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by dmudg16 on 2/19/2016.
 */
public class showcomplaints extends Activity {
   public static  TextView et;
static String json="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcomplaints);
        et = (TextView) findViewById(R.id.textView);


    }
public static void settext(String j){
    json=j;
    et.setText(json);
}
    }
