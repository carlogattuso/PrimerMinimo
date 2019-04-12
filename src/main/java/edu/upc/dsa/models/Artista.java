package edu.upc.dsa.models;

public class Artista {
    private String idArtista;
    private String name;

    public Artista() {
    }

    public Artista(String idArtista, String name) {
        this.idArtista = idArtista;
        this.name = name;
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


    @Override
    public String toString() {
        return "Artista [idArtista="+idArtista+", name=" + name + "]";
    }
}
