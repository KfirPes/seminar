package dev.tomco.a24b_11345a_l10.Models;

public class Worker extends Person {
    private String type;

    public Worker() {
        super("");

        // בונה ריק
    }

    public Worker(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}

