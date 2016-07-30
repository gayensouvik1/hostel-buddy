package com.example.dmudg16.hostelbuddy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by dmudg16 on 2/26/2016.
 */

public class loginpage extends Activity implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        SharedPreferences sharedPref = getApplication().getSharedPreferences(getString(R.string.loginstatus),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(checkBox.isChecked()) {
            editor.putString(getString(R.string.loginstatus), "true");
        }else
            editor.putString(getString(R.string.loginstatus), "false");
        editor.commit();
        this.finish();
        Intent i=new Intent("com.example.dmudg16.hostelbuddy.homepage");
        startActivity(i);

    }

    Button b;
    AutoCompleteTextView l1;
    AutoCompleteTextView l2;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       checkBox=(CheckBox) findViewById(R.id.checkbox);
l1=(AutoCompleteTextView) findViewById(R.id.listView);

        l2=(AutoCompleteTextView) findViewById(R.id.listView2);
        String[] college = getResources().getStringArray(R.array.collegelist);
// Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, college);
        l1.setAdapter(adapter1);
        String[] hostels= getResources().getStringArray(R.array.hostellist);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hostels);
        l2.setAdapter(adapter2);
        b=(Button)findViewById(R.id.signupbutton);
        b.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You Want To Exit? ")
                .setTitle("");


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked cancel button
            }
        });
        AlertDialog alert =builder.create();
        alert.show();
    }
}
