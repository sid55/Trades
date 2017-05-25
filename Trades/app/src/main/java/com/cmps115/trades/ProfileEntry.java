package com.cmps115.trades;

public class ProfileEntry {
    private String fName;
    private String lName;
    private String email;
    private int phoneNo;

    //Need to make data struct for an array of skills
    //private String skills;
    //private Image profPic;

    //Constructors
    public ProfileEntry(){

    }

    public ProfileEntry(String fName, String lName, String email, int phoneNo){
        this.fName= fName;
        this.lName= lName;
        this.email= email;
        this.phoneNo= phoneNo;
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

    public void setPhone(int numb){
        this.phoneNo= numb;
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

    public int getPhone(){
        return phoneNo;
    }

    //use Databaseref.push to generate salted keys
}
