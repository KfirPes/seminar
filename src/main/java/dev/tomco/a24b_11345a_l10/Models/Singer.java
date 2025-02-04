package dev.tomco.a24b_11345a_l10.Models;

public class Singer extends Artist {
    private int numAlbums;


    public Singer(String name,int numAlbums) {
        super(name,"Singer");
        this.numAlbums = numAlbums;
    }

    public int getNumAlbums() {
        return numAlbums;
    }
    public void setNumAlbums(int numAlbums){
        this.numAlbums=numAlbums;
    }

}
