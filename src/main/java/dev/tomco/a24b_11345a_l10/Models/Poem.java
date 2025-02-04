package dev.tomco.a24b_11345a_l10.Models;

public class Poem extends Creation {
    private int length; // Length in words
    public Poem(){
        super();
    }
    public Poem(Artist artist, String name, int length) {
        super(artist, name,"Poem");
        this.length = length;

    }

    public int getLength() {
        return length;
    }
}
