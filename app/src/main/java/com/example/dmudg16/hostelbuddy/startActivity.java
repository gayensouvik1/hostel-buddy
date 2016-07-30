package com.example.dmudg16.hostelbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by dmudg16 on 2/26/2016.
 */
public class startActivity extends Activity{
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.home);
        tv = (TextView) findViewById(R.id.textView2);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.loginstatus), Context.MODE_PRIVATE);
        String loginneeder = sharedPref.getString(getString(R.string.loginstatus), "false");

        if (loginneeder.equals("false")) {

            Intent i=new Intent("com.example.dmudg16.hostelbuddy.loginpage");
            startActivity(i);
            this.finish();
        }
        else{
            Intent i=new Intent("com.example.dmudg16.hostelbuddy.homepage");
            startActivity(i);

            this.finish();
        }
    }
}

