package dev.tomco.a24b_11345a_l10.Models;

public class Composer extends Artist {
    private String musicType;
    public Composer(String name, String musicType) {
        super(name,"Composer");
        this.musicType = musicType;
    }

    public String getMusicType() {
        return musicType;
    }
}
