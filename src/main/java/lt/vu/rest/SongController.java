package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Song;
import lt.vu.persistence.AlbumDAO;
import lt.vu.persistence.SongDAO;
import lt.vu.rest.contracts.AlbumDto;
import lt.vu.rest.contracts.SongDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/songs")
public class SongController {

    @Inject
    @Setter
    @Getter
    private SongDAO songDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Song song = songDAO.findOne(id);
        if (song == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        SongDto songDto = new SongDto();
        songDto.setName(song.getName());
        songDto.setLink(song.getLink());
        songDto.setStyles(song.getStyles());
        songDto.setArtistName(song.getArtist().getName());
        songDto.setAlbumName(song.getAlbum().getName());

        return Response.ok(songDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer songId,
            SongDto songData) {
        try {
            Song existingSong = songDAO.findOne(songId);
            if (existingSong == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingSong.setName(songData.getName());
            songDAO.update(existingSong);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
