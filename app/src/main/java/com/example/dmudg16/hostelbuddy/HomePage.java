package com.example.dmudg16.hostelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by dmudg16 on 2/26/2016.
 */
public  class HomePage extends NavigationDrawer implements GestureDetector.OnGestureListener{
    GestureDetector gd;
    TextView tv;
    receive r;
    JSONArray complaints = null;
    protected List<Complain> complain = new ArrayList<Complain>();
    ArrayList<HashMap<String, String>> complaintsList;
    public static  TextView et;
    static String json="";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_COMPLAINTS = "complaints";
    private static final String TAG_SOLVED="solved";
    private static final String TAG_category = "category";
    private static final String TAG_NAME = "name";
    private static final String TAG_TIME = "time";
    private static final String TAG_COMPLAINT="complaint";
    private float lastX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        gd=new GestureDetector(this,this);
       // et = (TextView) findViewById(R.id.textviewcom);
        complaintsList = new ArrayList<HashMap<String, String>>();

       receivedata();

    }
    public void receivedata(){
        r=new receive(this);
        r.execute();
    }
    public int selectimage(String category){
        if(category.toLowerCase().equals("sports")){
            return R.drawable.football;
        } else if(category.toLowerCase().equals("washroom")){
            return R.drawable.men_washroom;
        } else if(category.toLowerCase().equals("electrician")){
            return R.drawable.bulb;
        } else if(category.toLowerCase().equals("other")){
            return R.drawable.misc;
        } else
            return R.drawable.misc;


    }
    public  void   populatelist(String query,String reqcategory){
        complain.clear();
        for(int i=0;i<complaintsList.size();i++){
            HashMap<String, String> map=complaintsList.get(i);
            String complaint=map.get("complaint").toLowerCase();
            String name=map.get("name").toLowerCase();
            String category=map.get("category").toLowerCase();
            String solved=map.get("solved");
            String time=map.get("time");
            if(query==null&&reqcategory==null){
                complain.add(new Complain(complaint,name,selectimage(category),solved,time,category));
            }
            else if(query==null&&category.equals(reqcategory)){
                complain.add(new Complain(complaint,name,selectimage(category),solved,time,category));
            }
            else if(query!=null&&complaint.contains(query)&&category.equals(reqcategory)){
                complain.add(new Complain(complaint,name,selectimage(category),solved,time,category));
            }
            else if(query!=null&&reqcategory==null&&complaint.contains(query)){
                complain.add(new Complain(complaint,name,selectimage(category),solved,time,category));
            }

        }
        populateListView();
    }
    public void settext(String j){
        json=j;
        try {
            JSONObject jobj = new JSONObject(json);
            int success = jobj.getInt(TAG_SUCCESS);
            complaints = jobj.getJSONArray(TAG_COMPLAINTS);
            for (int i = 0; i < complaints.length(); i++) {
                JSONObject c = complaints.getJSONObject(i);

                // Storing each json item in variable
                String category = c.getString(TAG_category);
                String name = c.getString(TAG_NAME);
                String complaint=c.getString(TAG_COMPLAINT);
                String solved=c.getString(TAG_SOLVED);
                String time=c.getString(TAG_TIME);
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                // adding each child node to HashMap key => value
                map.put(TAG_category, category);
                map.put(TAG_NAME, name);
                map.put(TAG_COMPLAINT,complaint);
                map.put(TAG_SOLVED,solved);
                map.put(TAG_TIME,time);
                // adding HashList to ArrayList
                complaintsList.add(map);
            }

        }catch (Exception e){}
        callnow();

    }
public void callnow(){
    populatelist(null,null);
}

    protected void populateListView(){
        ArrayAdapter<Complain> adapter = new MyListAdapter();
        ListView listview = (ListView)findViewById(R.id.listView);
        listview.setAdapter(adapter);


    }

    private class MyListAdapter extends ArrayAdapter<Complain>{
        public MyListAdapter(){
            super(HomePage.this, R.layout.item_view, complain);

        }

        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if(itemView==null){
                itemView = getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }

            final Complain current = complain.get(position);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView);
            imageView.setImageResource(current.getIconID());

            TextView show_complain = (TextView)itemView.findViewById(R.id.textView);
            show_complain.setText(current.getComplain());

            TextView show_complainant = (TextView)itemView.findViewById(R.id.textView2);
            show_complainant.setText(current.getComplainant());
            ImageView solvedView = (ImageView)itemView.findViewById(R.id.done);
            solvedView.setImageResource(current.getDone());
            TextView show_category = (TextView)itemView.findViewById(R.id.category);
            show_category.setText(current.getCategory().toUpperCase());
            TextView show_date = (TextView)itemView.findViewById(R.id.date);
            show_date.setText(current.getTime());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final SlidingDrawer sd=(SlidingDrawer)findViewById(R.id.sliding1);
                    sd.open();

                    TextView show_complain = (TextView)findViewById(R.id.slidingtv1);
                    show_complain.setText("Complain :\n" + current.getComplain());

                    TextView show_complainant = (TextView)findViewById(R.id.slidingtv2);
                    show_complainant.setText("Complainant : " + current.getComplainant());

                    TextView show_category = (TextView)findViewById(R.id.slidingtv3);
                    show_category.setText("Category : "+current.getCategory().toUpperCase());
                    TextView show_date = (TextView)findViewById(R.id.slidingtv4);
                    show_date.setText("Date : "+current.getTime());
                    TextView show_status = (TextView)findViewById(R.id.slidingtv5);
                    show_status.setText("Status : "+current.status());
                    RelativeLayout ll=(RelativeLayout)sd.getContent();

                    ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sd.close();

                        }
                    });

                }
            });
            return itemView;
        }
    }

   @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
       this.gd.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX()-e2.getX()>100&&mDrawerLayout.isDrawerOpen(findViewById(R.id.drawer_list))==false){
        Intent i=new Intent("com.example.dmudg16.hostelbuddy.writecomplaint");
        startActivity(i);
        overridePendingTransition(R.anim.slideleftin, R.anim.slideleftout);
        }
        else if(e1.getY()<e2.getY()&&mDrawerLayout.isDrawerOpen(findViewById(R.id.drawer_list))==false){
           // recreate();
        }
        return true;
    }
public void dosearch(String query){
    populatelist(query, null);
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HomePage.this.dosearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onOptionsItemSelected(item);
    }
}

