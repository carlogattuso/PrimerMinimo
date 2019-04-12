package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface MyMusicManager {

    /**
     * number of playlists of a user
     */
    public static final int P = 10;

    /**
     * Add a new User
     *
     * @param idUser identifier of the user
     * @param name name of the user
     * @param surname surname of the user
     */
    public void addUser(String idUser, String name, String surname);

    /**
     * Add a new Artista
     *
     * @param idArtista identifier of the artista
     * @param name name of the artista
     * @param surname surname of the artista
     */
    public void addArtista(String idArtista, String name, String surname);

    /**
     * Add a new PlayList
     *
     * @param idUser identifier of the user
     * @param idPlayList identifier of the playlist
     * @param name name of the playlist
     * @throws UserNotFoundException  if the user is not found
     * @throws FullPlayListException  if the user is not found
     */
    public void addPlayList(String idUser, String idPlayList, String name) throws UserNotFoundException, FullPlayListException;


    /**
     * Add a new Titol into a PlayList
     *
     * @param idUser identifier of the user
     * @param idPlayList identifier of the playlist
     * @param idTitol identifier of the titol
     * @param titol name of the titol
     * @param artista name of the artista
     * @param album name of the album
     * @param duracio duration of the song
     * @throws UserNotFoundException  if the user does not exist
     * @throws PlayListNotFoundException if the playlist doesn't exist
     */
    public void addTitol(String idUser, String idPlayList, String idTitol, String titol, String artista, String album, double duracio) throws UserNotFoundException, PlayListNotFoundException;


    /**
     * Get the titols of a playlist
     *
     * @param idUser identifier of the user
     * @param idPlaylist identifier of the playlist
     * @return list of titols
     * @throws PlayListNotFoundException if the station doesn't exist
     */
    public List<Titol> titolsOfAPlaylist(String idUser, String idPlayList) throws UserNotFoundException, PlayListNotFoundException;

    /**
     * get the List of artists
     */
    public List<Artista> getArtistas();

    /**
     * get the number of users
     *
     * @return the number of users
     */
    public int numUsers();

    /**
     * get the number of stations
     *
     * @param idUser identifier of the user
     * @return the number of playlists of a user
     */
    //public int numPlaylists(String idUser);

    /**
     * get the number of bikes in a station
     *
     * @return the number of artistas
     */
    public int numArtistas();

    /**
     * clear all the data structures
     */
    public void clear();
}


