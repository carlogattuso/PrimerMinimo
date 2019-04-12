package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyMusicManagerTest {
    private MyMusicManagerImpl mm;

    @Before
    public void setUp() throws Exception {
        this.mm = MyMusicManagerImpl.getInstance();
        this.mm.addUser("user1", "Carlo", "Gattuso");

        Assert.assertEquals(1, this.mm.numUsers());

        this.mm.addArtista("artista1","Metallica");
        this.mm.addArtista("artista2","ACDC");
        this.mm.addArtista("artista3","Bob Marley");
        this.mm.addArtista("artista4","Estopa");

        Assert.assertEquals(4, this.mm.numArtistas());
        Assert.assertEquals(0, this.mm.numPlaylists("user1"));

        this.mm.addPlayList("user1", "playlist1", "Reggae");
        this.mm.addPlayList("user1", "playlist2", "Rock");
        this.mm.addPlayList("user1", "playlist3", "Pop");

        Assert.assertEquals(3, this.mm.numPlaylists("user1"));

        this.mm.addTitol("user1", "playlist2", "titol01", "Enter Sandman", "Metallica", "Master of Puppets",3.5);
        this.mm.addTitol("user1", "playlist2", "titol02", "Highway To Hell", "ACDC", "Back in Black",4);
        this.mm.addTitol("user1", "playlist1", "titol03", "Could you be loved", "Bob Marley", "Could you be loved",3.1);
        this.mm.addTitol("user1", "playlist3", "titol04", "Vino Tinto", "Estopa", "Destrangis",4.5);

        Assert.assertEquals(2, this.mm.findPlayList("user1","playlist2").getTitols().size());
        Assert.assertEquals(1, this.mm.findPlayList("user1","playlist1").getTitols().size());
        Assert.assertEquals(1, this.mm.findPlayList("user1","playlist3").getTitols().size());
    }

    @After
    public void tearDown(){
        this.mm.clear();
    }

    @Test
    public void testAddUser() {
        this.mm.addUser("user2", "Juan", "Lopez");
        Assert.assertEquals(2, this.mm.numUsers());
    }

    @Test
    public void testAddArtista() {
        this.mm.addArtista("artista5","The Beatles");
        Assert.assertEquals(5, this.mm.numArtistas());
    }

    @Test
    public void testAddPlayList() throws UserNotFoundException, FullPlayListException {
        this.mm.addPlayList("user1","playlist4","Jazz");
        Assert.assertEquals(4, this.mm.numPlaylists("user1"));
    }

    @Test
    public void testAddTitol() throws UserNotFoundException, PlayListNotFoundException {

        this.mm.addTitol("user1", "playlist3", "titol05", "Buleria","David Bisbal","Buleria",4.7);
        this.mm.addTitol("user1", "playlist3", "titol06", "Caminando por la vida","Melendi","Caminando por la vida",3);

        Assert.assertEquals(3, this.mm.findPlayList("user1", "playlist3").getTitols().size());
    }

    @Test(expected = FullPlayListException.class)
    public void testAddPlayListsAndSizeFull() throws UserNotFoundException, FullPlayListException {
        this.mm.addPlayList("user1","playlist4","Blues");
        this.mm.addPlayList("user1","playlist5","Blues");
        this.mm.addPlayList("user1","playlist6","Blues");
        this.mm.addPlayList("user1","playlist7","Blues");
        this.mm.addPlayList("user1","playlist8","Blues");
        this.mm.addPlayList("user1","playlist9","Blues");
        this.mm.addPlayList("user1","playlist10","Blues");
        this.mm.addPlayList("user1","playlist11","Blues");
    }

    @Test(expected = UserNotFoundException.class)
    public void testAddPlayListAndUserNotFound() throws UserNotFoundException, FullPlayListException{
        this.mm.addPlayList("XXXX", "playlist4", "R&B");
    }

    @Test
    public void testTitolsByPlayList() throws PlayListNotFoundException, UserNotFoundException {

        List<Titol> titols = this.mm.titolsOfAPlaylist("user1","playlist2");

        Assert.assertEquals("titol01", titols.get(0).getId());
        Assert.assertEquals("Enter Sandman", titols.get(0).getTitol());

        Assert.assertEquals("titol02", titols.get(1).getId());
        Assert.assertEquals("Highway To Hell", titols.get(1).getTitol());
    }

    @Test
    public void testGetAtistas() throws Exception {
        List<Artista> artistas = this.mm.getArtistas();

        Assert.assertEquals(4, artistas.size());
    }
}
