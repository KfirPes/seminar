package dev.tomco.a24b_11345a_l10.Models;

public class Person {
   protected String name;
    public Person(){}
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}