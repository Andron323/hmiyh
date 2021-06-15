package com.freedev.hmiyh.datas;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@IgnoreExtraProperties
public class HistoryTimer implements Serializable {
    public String id;
    public String name_time;
    public String cost;
    public String data;

    public HistoryTimer() {
    }

    public HistoryTimer(String id, String name_time, String cost, String data) {
        this.name_time = name_time;
        this.id = id;
        this.cost = cost;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_time() {
        return name_time;
    }

    public void setName_time(String name_time) {
        this.name_time = name_time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
