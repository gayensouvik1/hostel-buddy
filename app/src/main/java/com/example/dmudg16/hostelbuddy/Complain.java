package com.example.dmudg16.hostelbuddy;

/**
 * Created by HP PC on 29-02-2016.
 */
public class Complain {

    private String complain,complainant;
    private int iconID;
    String solved;
    String time;
    String category;
    public Complain(String complain,String complainant,int iconID,String solved,String time,String category
    ){
        super();
        this.complain = complain;
        this.complainant = complainant;
        this.iconID = iconID;
    this.solved=solved;
        this.time=time;
        this.category=category;
    }


    public String getComplain(){
        return complain;
    }
    public String getComplainant(){
        return complainant;
    }
    public int getIconID(){
        return iconID;
    }
    public String getsolved(){return solved;}
    public String getTime(){return time;}
    public int getDone(){
        if(solved.equals("1")){
            return R.drawable.tick;
        }
        else
            return R.drawable.cross;
    }
    public String status(){
        if(solved.equals("1")){
            return "SOLVED";
        }
        else
            return "UNSOLVED";
    }
    public  String getCategory(){return  category;}
}
