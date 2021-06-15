package com.freedev.hmiyh.datas;

import java.util.ArrayList;

public class Graphix {
    public String id;
    public String targetCostTotal;
    public String targetMonthCost;
    public String targetDayCost;
    public String name_progress;
    public String titelOnProgresBar;
    public String progress;

    public String name_graphic;
    public ArrayList<String> list;
    public ArrayList<String> list2;
    public ArrayList<String> list3;

    public Graphix() {
    }

    public Graphix(String id, String targetCostTotal, String targetMonthCost, String targetDayCost, String name_progress, String titelOnProgresBar, String progress, String name_graphic, ArrayList<String> list, ArrayList<String> list2, ArrayList<String> list3) {
        this.id = id;
        this.targetCostTotal = targetCostTotal;
        this.targetMonthCost = targetMonthCost;
        this.targetDayCost = targetDayCost;
        this.name_progress = name_progress;
        this.titelOnProgresBar = titelOnProgresBar;
        this.progress = progress;
        this.name_graphic = name_graphic;
        this.list = list;
        this.list2 = list2;
        this.list3 = list3;
    }
}
