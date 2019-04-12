package edu.upc.dsa.models;

public class Artista {
    private String idArtista;
    private String name;
    private String surname;

    public Artista() {
    }

    public Artista(String idArtista, String name, String surname) {
        this.idArtista = idArtista;
        this.name = name;
        this.surname = surname;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
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

    @Override
    public String toString() {
        return "Artista [idArtista="+idArtista+", name=" + name + ", surname=" + surname + "]";
    }
}
