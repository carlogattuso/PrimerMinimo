package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyMusicManagerImpl implements MyMusicManager{

    private static MyMusicManagerImpl instance = new MyMusicManagerImpl();

    final static Logger logger = Logger.getLogger(MyMusicManagerImpl.class);

    public static MyMusicManagerImpl getInstance() {
        return instance;
    }

    HashMap<String,User> userHashMap = new HashMap<>();
    LinkedList<Artista> artistas = new LinkedList<>();

    @Override
    public void addUser(String idUser, String name, String surname) {
        User u = new User(idUser,name,surname,P);
        this.userHashMap.put(idUser,u);
        logger.info("New User: "+u.toString());
        logger.info("User Count: "+this.numUsers());
    }

    @Override
    public void addArtista(String idArtista, String name) {
        Artista a = new Artista(idArtista,name);
        this.artistas.add(a);
        logger.info("New Artista: "+a.toString());
        logger.info("Artista Count: "+this.numArtistas());
    }

    @Override
    public void addPlayList(String idUser, String idPlayList, String name) throws UserNotFoundException, FullPlayListException {
        User u = findUser(idUser);
        PlayList p = new PlayList(idPlayList,name);
        List<PlayList> playLists = u.getPlayLists();
        if(playLists.size()==P){
            logger.warn("Full playlists");
            throw new FullPlayListException();
        }
        u.getPlayLists().add(p);
        logger.info("New PlayList: "+p.toString());
        logger.info("Adding User: "+u.toString());
        logger.info("User Playlists: "+playLists.size());
    }

    @Override
    public void addTitol(String idUser, String idPlayList, String idTitol, String titol, String artista, String album, double duracio) throws UserNotFoundException, PlayListNotFoundException, ArtistaNotFoundException {
        User u = findUser(idUser);
        PlayList p = findPlayList(idUser,idPlayList);
        Artista a = findArtista(artista);
        Titol t = new Titol(idTitol,titol,artista,album,duracio);
        List<Titol> titols = p.getTitols();
        titols.add(t);
        logger.info("New Titol: "+t.toString());
        logger.info("Adding User: "+u.toString());
        logger.info("Adding Playlist: "+p.toString());
        logger.info("Artista Found: "+a.toString());
        logger.info("Titols in playlist: "+titols.size());
    }

    @Override
    public List<Titol> titolsOfAPlaylist(String idUser, String idPlayList) throws UserNotFoundException, PlayListNotFoundException {
        User u = findUser(idUser);
        PlayList p = findPlayList(idUser,idPlayList);
        List<Titol> playListTitols = p.getTitols();
        logger.info("Get titols: "+playListTitols.size());
        logger.info("User: "+u.toString());
        logger.info("Playlist: "+p.toString());
        return playListTitols;
    }

    @Override
    public List<Artista> getArtistas() {
        logger.info("Get artistas: "+this.numArtistas());
        return this.artistas;
    }

    @Override
    public int numUsers() {
        return this.userHashMap.size();
    }

    @Override
    public int numArtistas() {
        return this.artistas.size();
    }

    @Override
    public int numPlaylists(String idUser) throws UserNotFoundException{
        User u = this.findUser(idUser);
        return u.getPlayLists().size();
    }

    @Override
    public void clear() {
        this.userHashMap.clear();
        this.artistas.clear();
    }

    public User findUser(String idUser) throws UserNotFoundException {
        User u = this.userHashMap.get(idUser);
        if(u!=null){
            return u;
        }
        logger.warn("User not found");
        throw new UserNotFoundException();
    }

    public Artista findArtista(String artista) throws ArtistaNotFoundException {
        for(Artista a : this.artistas){
            if(a.getName().equals(artista)){
                return a;
            }
        }
        logger.warn("Artista not found");
        throw new ArtistaNotFoundException();
    }

    public PlayList findPlayList(String idUser, String idPlayList) throws UserNotFoundException, PlayListNotFoundException{
        User u = findUser(idUser);
        for(PlayList p : u.getPlayLists()){
            if(p.getId().equals(idPlayList)){
                return p;
            }
        }
        logger.warn("PlayList not found");
        throw new PlayListNotFoundException();
    }
}
