package dev.tomco.a24b_11345a_l10.Models;

public class Creation {
    private Artist artist;
    private String name;
    protected String type;
    public Creation(){}
    public Creation(Artist artist, String name,String type) {
        this.artist = artist;
        this.name = name;
        this.type=type;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
}
