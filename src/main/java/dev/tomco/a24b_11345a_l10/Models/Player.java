package dev.tomco.a24b_11345a_l10.Models;

public class Player extends Artist {
    private String instrument;

    public Player(String name, String instrument) {
        super(name,"Player");
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }
    public void setInstrument(String instrument){
        this.instrument=instrument;
    }
}
