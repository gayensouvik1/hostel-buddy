package com.example.dmudg16.hostelbuddy;

import android.os.Bundle;

/**
 * Created by dmudg16 on 2/28/2016.
 */
public class sportscomplaints extends HomePage{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public void callnow() {
        populatelist(null, "sports");
    }

    @Override
    public void dosearch(String query){
        populatelist(query, "sports");
    }
}
