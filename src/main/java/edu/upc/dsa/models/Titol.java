package edu.upc.dsa.models;

public class Titol {
    private String id;
    private String titol;
    private String artista;
    private String album;
    private double duracio;

    public Titol() {
    }

    public Titol(String id, String titol, String artista, String album, double duracio) {
        this.id = id;
        this.titol = titol;
        this.artista = artista;
        this.album = album;
        this.duracio = duracio;
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

    @Override
    public String toString() {
        return "Titol [id="+id+", titol=" + titol + ", artista=" + artista + ", album=" + album + ", duracio="+ duracio +"]";
    }

}
