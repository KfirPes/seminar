package dev.tomco.a24b_11345a_l10.Models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Artist extends Person {

    protected String type;
    protected Vector<String> creations = new Vector<String>();
    public Artist(){}
    public Artist(String name,String Type)  {
        super(name);
        this.type=Type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type){
        this.type=type;
    }

    public void addCreation(Creation creation) {
        creations.add(creation.getName());
    }


}