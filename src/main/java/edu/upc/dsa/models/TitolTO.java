package edu.upc.dsa.models;

public class TitolTO {
    private String idUser;
    private String idPlayList;
    private String id;
    private String titol;
    private String artista;
    private String album;
    private double duracio;

    public TitolTO() {
    }

    public TitolTO(String idUser, String idPlayList, String id, String titol, String artista, String album, double duracio) {
        this.idUser = idUser;
        this.idPlayList = idPlayList;
        this.id = id;
        this.titol = titol;
        this.artista = artista;
        this.album = album;
        this.duracio = duracio;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public double getDuracio() {
        return duracio;
    }

    public void setDuracio(double duracio) {
        this.duracio = duracio;
    }
}
