package com.example.dmudg16.hostelbuddy;

import java.util.HashMap;

/**
 * Created by dmudg16 on 2/29/2016.
 */
public class Solved extends HomePage {
    @Override
    public void callnow() {
        populatelist(null, "washroom");
    }
    @Override
    public void dosearch(String query){
        populatelist(query,"");
    }
    @Override
    public void populatelist(String query,String reqcategory){
        complain.clear();
        for(int i=0;i<complaintsList.size();i++){
            HashMap<String, String> map=complaintsList.get(i);
            String complaint=map.get("complaint");
            String name=map.get("name");
            String category=map.get("category").toLowerCase();
            String solved=map.get("solved");
            String time=map.get("time");
            if(query==null&&solved.equals("1")){
                complain.add(new Complain(complaint,name,selectimage(category),solved,time,category));
            }
            if(query!=null&&solved.equals("1")&&complaint.contains(query)){
                complain.add(new Complain(complaint,name,selectimage(category),solved,time,category));
            }
        }
        populateListView();

    }
}
