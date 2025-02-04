package dev.tomco.a24b_11345a_l10.Models;

public class Poet extends Artist {
    private int numPoems;

    public Poet(String name, int numPoems) {
        super(name,"Poet");
        this.numPoems=numPoems;
    }
    public void setNumPoems(int numPoems){
        this.numPoems=numPoems;
    }
    public int getNumPoems() {
        return numPoems;
    }
}