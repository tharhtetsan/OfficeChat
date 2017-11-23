package com.example.ucsm.officechat;

import java.util.List;

/**
 * Created by ucsm on 10/23/2016.
 */

public class GetAndSetUserProfileData {

    private  int userID;
    private String userName;
    private  String userGmail;
    private  String userLivein;
    private String userWorkat;
    private  String userRank;
    private  String userGender;
    private  String userSchool;

    private List<String> Person;

    public GetAndSetUserProfileData(int userID, String userName, String userGmail, String userLivein, String userWorkat, String userRank, String userGender, String userSchool)
    {
        this.userID=userID;
        this.userName=userName;
        this.userGmail=userGmail;
        this.userLivein=userLivein;
        this.userWorkat=userWorkat;
        this.userRank=userRank;
        this.userGender=userGender;
        this.userSchool=userSchool;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGmail() {
        return userGmail;
    }

    public void setUserGmail(String userGmail) {
        this.userGmail = userGmail;
    }

    public String getUserLivein() {
        return userLivein;
    }

    public void setUserLivein(String userLivein) {
        this.userLivein = userLivein;
    }

    public String getUserWorkat() {
        return userWorkat;
    }

    public void setUserWorkat(String userWorkat) {
        this.userWorkat = userWorkat;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public List<String> getPerson() {
        return Person;
    }

    public void setPerson(List<String> person) {
        Person = person;
    }
}
