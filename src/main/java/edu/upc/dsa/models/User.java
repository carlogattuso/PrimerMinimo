package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;

public class User {
    private String idUser;
    private String name;
    private String surname;
    private ArrayList<PlayList> playLists;

    public User() {
    }

    public User(String idUser, String name, String surname, int maxPlayLists) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        playLists  = new ArrayList<>(maxPlayLists);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(ArrayList<PlayList> playLists) {
        this.playLists = playLists;
    }

    @Override
    public String toString() {
        return "User [id="+idUser+", name=" + name + ", surname=" + surname + ", playlists=" + playLists.size() +"]";
    }
}
