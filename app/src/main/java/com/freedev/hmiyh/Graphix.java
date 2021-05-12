package com.freedev.hmiyh;

public class Graphix {
    public String name_progress;
    public String onProgSpeed;
    public int progress;
    public String name_graphic;
    public float[] graphic1;
    public float[] graphic2;
    public String[] name_x_graphic;

    public Graphix(String name_progress,String onProgSpeed, int progress, String name_graphic, float[] graphic1, float[] graphic2, String[] name_x_graphic) {
        this.name_progress = name_progress;
        this.onProgSpeed = onProgSpeed;
        this.progress = progress;
        this.name_graphic = name_graphic;
        this.graphic1 = graphic1;
        this.graphic2 = graphic2;
        this.name_x_graphic = name_x_graphic;
    }
}
