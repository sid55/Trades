package com.cmps115.trades;

import android.graphics.Bitmap;

public class ProfileEntry {
    private String fName;
    private String lName;
    private String email;
    //Change to int
    private String phoneNo;
    private Bitmap profPic;
    //Locational tracking
    private double longi;
    private double lat;

    String myImage;
    //Need to make data struct for an array of skills
    //private String skills;
    //private Image profPic;

    //Constructors
    public ProfileEntry(){

    }

    public ProfileEntry(String fName, String lName, String email, String phoneNo, double longi, double lat){
        this.fName= fName;
        this.lName= lName;
        this.email= email;
        this.phoneNo= phoneNo;
        this.longi= longi;
        this.lat= lat;
    }

    public ProfileEntry(String fName, String lName, String email, String phoneNo, double longi, double lat, String myImage){
        this.fName= fName;
        this.lName= lName;
        this.email= email;
        this.phoneNo= phoneNo;
        this.longi= longi;
        this.lat= lat;
        this.myImage = myImage;
    }

    //Mutators
    public void setfName(String word){
        this.fName= word;
    }

    public void setlName(String word){
        this.lName= word;
    }

    public void setEmail(String word){
        this.email= word;
    }

    public void setPhone(String numb){
        this.phoneNo= numb;
    }

    public void setLocation(double longitude, double latitude){
        this.longi= longitude;
        this.lat= latitude;
    }

    //Accessors
    public String getfName(){
        return fName;
    }

    public String getlName(){
        return lName;
    }

    public String getEmail(){
        return email;
    }

    public String getMyImage(){
        return myImage;
    }

    public String getPhone(){
        return phoneNo;
    }

    public double getLongi(){return longi; }

    public double getLat(){return lat; }

    //use Databaseref.push to generate salted keys
}
