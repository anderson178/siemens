package ru.siemens;

public class Metering {
    private String w;
    private String l;
    private String t;
    private String dt;

    public Metering(String w, String l, String t, String dt) {
        this.w = w;
        this.l = l;
        this.t = t;
        this.dt = dt;
    }

    public String getW() {
        return w;
    }

    public String getL() {
        return l;
    }

    public String getT() {
        return t;
    }

    public String getDt() {
        return dt;
    }
}
