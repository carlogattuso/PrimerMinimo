package edu.upc.dsa.services;

import edu.upc.dsa.MyMusicManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/music", description = "Endpoint to Music Service")
@Path("/music")
public class MyMusicService {
    private MyMusicManagerImpl mm;

    public MyMusicService() throws Exception{
        this.mm = MyMusicManagerImpl.getInstance();

        if (mm.numUsers()==0) {
            this.mm.addUser("user1", "Carlo", "Gattuso");

            this.mm.addArtista("artista1","Metallica");
            this.mm.addArtista("artista2","ACDC");
            this.mm.addArtista("artista3","Bob Marley");
            this.mm.addArtista("artista4","Estopa");

            this.mm.addPlayList("user1", "playlist1", "Reggae");
            this.mm.addPlayList("user1", "playlist2", "Rock");
            this.mm.addPlayList("user1", "playlist3", "Pop");

            this.mm.addTitol("user1", "playlist2", "titol01", "Enter Sandman", "Metallica", "Master of Puppets",3.5);
            this.mm.addTitol("user1", "playlist2", "titol02", "Highway To Hell", "ACDC", "Back in Black",4);
            this.mm.addTitol("user1", "playlist1", "titol03", "Could you be loved", "Bob Marley", "Could you be loved",3.1);
            this.mm.addTitol("user1", "playlist3", "titol04", "Vino Tinto", "Estopa", "Destrangis",4.5);

        }
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= UserTO.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(UserTO user) {
        this.mm.addUser(user.getIdUser(),user.getName(),user.getSurname());
        return Response.status(201).entity(user).build();
    }

    @POST
    @ApiOperation(value = "create a new Artista", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Artista.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/artista")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Artista artista) {
        this.mm.addArtista(artista.getIdArtista(),artista.getName());
        return Response.status(201).entity(artista).build();
    }

    @POST
    @ApiOperation(value = "add a new PlayList", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= PlayListTO.class),
            @ApiResponse(code = 500, message = "Playlist full"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/playlist/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlayList(PlayListTO playList, @PathParam("idUser") String idUser) {
        try{
            this.mm.addPlayList(idUser,playList.getId(),playList.getName());
            return Response.status(201).entity(playList).build();
        } catch(UserNotFoundException e1){
            return Response.status(404).build();
        } catch(FullPlayListException e2){
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "add a new Titol to a PlayList", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Titol.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Playlist not found"),
            @ApiResponse(code = 406, message = "Artista not found")
    })
    @Path("/titol/{idUser}/{idPlayList}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTitol(Titol titol,@PathParam("idUser") String idUser,@PathParam("idPlayList") String idPlayList) {
        try{
            this.mm.addTitol(idUser,idPlayList,titol.getId(),titol.getTitol(),titol.getArtista(),titol.getAlbum(),titol.getDuracio());
            return Response.status(201).entity(titol).build();
        } catch(UserNotFoundException e1){
            return Response.status(404).build();
        } catch(PlayListNotFoundException e2){
            return Response.status(405).build();
        } catch(ArtistaNotFoundException e3){
            return Response.status(406).build();
        }
    }

    @GET
    @ApiOperation(value = "get Artistas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Artista.class, responseContainer="List"),
    })
    @Path("/artistas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistas() {

        List<Artista> artistas = this.mm.getArtistas();

        GenericEntity<List<Artista>> entity = new GenericEntity<List<Artista>>(artistas) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get titols by PlayList", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Titol.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Playlist not found")
    })
    @Path("/titols/{idUser}/{idPlayList}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByAlbum(@PathParam("idUser") String idUser,@PathParam("idPlayList") String idPlayList){
        List<Titol> titols;
        try {
            titols = this.mm.titolsOfAPlaylist(idUser,idPlayList);
        }
        catch(UserNotFoundException e1) {
            return Response.status(404).build();
        }
        catch (PlayListNotFoundException e2) {
            return Response.status(405).build();
        }
        GenericEntity<List<Titol>> entity = new GenericEntity<List<Titol>>(titols) {
        };
        return Response.status(201).entity(entity).build();
    }
}
