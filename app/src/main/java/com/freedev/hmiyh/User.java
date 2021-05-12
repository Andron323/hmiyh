package com.freedev.hmiyh;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class User implements Serializable {
    public String personId;
    public String personName;
    public String personEmail;
    public String personPhoto;
    public String personWork;
    public String personInfo;
    public String personHourCost;

    public User() {
    }

    public User(String personId, String personName, String personEmail, String personPhoto, String personWork, String personInfo, String personHourCost) {
        this.personId = personId;
        this.personName = personName;
        this.personEmail = personEmail;
        this.personPhoto = personPhoto;
        this.personWork = personWork;
        this.personInfo = personInfo;
        this.personHourCost = personHourCost;
    }

    public String getPersonWork() {
        return personWork;
    }

    public void setPersonWork(String personWork) {
        this.personWork = personWork;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public String getPersonHourCost() {
        return personHourCost;
    }

    public void setPersonHourCost(String personHourCost) {
        this.personHourCost = personHourCost;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }
}
