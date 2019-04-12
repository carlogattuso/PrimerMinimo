package edu.upc.dsa.models;

import java.util.LinkedList;

public class PlayList {
    private String id;
    private String name;
    private LinkedList<Titol> titols = new LinkedList<>();

    public PlayList() {
    }

    public PlayList(String id, String userId, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Titol> getTitols() {
        return titols;
    }

    public void setTitols(LinkedList<Titol> titols) {
        this.titols = titols;
    }

    @Override
    public String toString() {
        return "PlayList [id="+id+", name=" + name + ", titols=" + titols.size() + "]";
    }
}
