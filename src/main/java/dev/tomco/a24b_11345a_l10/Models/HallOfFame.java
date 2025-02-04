package dev.tomco.a24b_11345a_l10.Models;

import java.util.HashMap;
import java.util.Map;

public class HallOfFame {
   private Map<String, Worker>workers= new HashMap<>();
   private Map<String, Artist> artists= new HashMap<>();
  private  Map<String, Creation> creations= new HashMap<>();
    public HallOfFame(){

    }
    public void addWorker(Worker worker) {
        workers.put(worker.getName(), worker);
    }

    public void addArtist(Artist artist) {
        artists.put(artist.getName(), artist);
    }

    public void addCreation(Creation creation) {
        creations.put(creation.getName(), creation);
    }

    public Worker getWorker(String name) {
        return workers.get(name);
    }

    public Artist getArtist(String name) {
        return artists.get(name);
    }

    public Creation getCreation(String name) {
        return creations.get(name);
    }

    public Map<String, Worker> getWorkers() {
        return workers;
    }

    public Map<String, Artist> getArtists() {
        return artists;
    }

    public Map<String, Creation> getCreations() {
        return creations;
    }
}