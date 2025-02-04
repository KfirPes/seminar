package dev.tomco.a24b_11345a_l10.Models;

public class Song extends Tune {
    public Song(Artist artist, String name, double length) {
        super(artist, name, length);
        this.type="Song";
    }
}
