package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.persistence.AlbumDAO;
import lt.vu.rest.contracts.AlbumDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/albums")
public class AlbumController {

    @Inject
    @Setter
    @Getter
    private AlbumDAO albumDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Album album = albumDAO.findOne(id);
        if (album == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        AlbumDto albumDto = new AlbumDto();
        albumDto.setName(album.getName());
        albumDto.setArtistName(album.getArtist().getName());

        return Response.ok(albumDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer albumId,
            AlbumDto albumData) {
        try {
            Album existingAlbum = albumDAO.findOne(albumId);
            if (existingAlbum == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingAlbum.setName(albumData.getName());
            albumDAO.update(existingAlbum);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
