package dev.tomco.a24b_11345a_l10.Models;

public class Tune extends Creation {
    private double length; // Length in seconds

    public Tune(Artist artist, String name, double length) {
        super(artist, name,"Tune");
        this.length = length;
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length){
        this.length=length;
    }
}
