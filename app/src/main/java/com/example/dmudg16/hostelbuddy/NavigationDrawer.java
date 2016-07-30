package com.example.dmudg16.hostelbuddy;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;


/**
 * Created by dmudg16 on 2/26/2016.
 */
public  class NavigationDrawer extends ActionBarActivity {
    //GestureDetector gd;
    ListView drawerList = null;
    String [] state;
    private ActionBarDrawerToggle mDrawerToggle;
     DrawerLayout mDrawerLayout;
    SearchView searchView;
    private String mActivityTitle;
    protected LinearLayout fullLayout;
    protected FrameLayout actContent;
    @Override
    public void setContentView(final int layoutResID) {
        // Your base layout here
        fullLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.drawer, null);
        actContent= (FrameLayout) fullLayout.findViewById(R.id.frame_container);

        // Setting the content of layout your provided to the act_content frame
        getLayoutInflater().inflate(layoutResID, actContent, true);
        super.setContentView(fullLayout);
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // here you can get your drawer buttons and define how they
        // should behave and what must they do, so you won't be
        // needing to repeat it in every activity class
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    public void addDrawerItems() {
        drawerList=(ListView)findViewById(R.id.drawer_list);
        String[] lvalues={"Home","Sports","Washroom","Electrician","Unsolved","Solved","LogOut","Exit"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lvalues);
        drawerList.setAdapter(adapter1);


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawable_layout);
        mActivityTitle = getTitle().toString();
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawers();
                openActivity(position);

            }
        });
    }
    public void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
      SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
         searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

      /*  searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
      */
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.search) {
            //onSearchRequested();
            return true;
        }
        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You sure You Want To LOGOUT? ")
                .setTitle("");


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences sharedPref = getApplication().getSharedPreferences(getString(R.string.loginstatus),Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.loginstatus), "false");
                editor.commit();

                NavigationDrawer.this.finish();
                Intent i=new Intent("com.example.dmudg16.hostelbuddy.loginpage");
                startActivity(i);
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
protected void openActivity(int position){
    if(position==6){
     logout();
    }
    if(position==0){
        Intent i=new Intent("com.example.dmudg16.hostelbuddy.homepage");
        startActivity(i);
    }
    if(position==1){

        Intent i=new Intent("com.example.dmudg16.hostelbuddy.sportscomplaints");
        startActivity(i);
    }
    if(position==2){
        Intent i=new Intent("com.example.dmudg16.hostelbuddy.washroom");
        startActivity(i);
    }
    if(position==3){
        Intent i=new Intent("com.example.dmudg16.hostelbuddy.electrician");
        startActivity(i);
    }
    if(position==4){
        Intent i=new Intent("com.example.dmudg16.hostelbuddy.unsolved");
        startActivity(i);
    }
    if(position==5){

        Intent i=new Intent("com.example.dmudg16.hostelbuddy.solved");
        startActivity(i);
    }
    if(position==7){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You Want To Exit? ")
                .setTitle("");


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                NavigationDrawer.this.finish();
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

}

