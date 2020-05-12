package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Artist;
import lt.vu.persistence.ArtistDAO;
import lt.vu.rest.contracts.ArtistDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/artists")
public class ArtistController {
    @Inject
    @Setter
    @Getter
    private ArtistDAO artistDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Artist artist = artistDAO.findOne(id);
        if (artist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ArtistDto artistDto = new ArtistDto();
        artistDto.setName(artist.getName());

        return Response.ok(artistDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer artistId,
            ArtistDto artistData) {
        try {
            Artist existingArtist = artistDAO.findOne(artistId);
            if (existingArtist == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingArtist.setName(artistData.getName());
            artistDAO.update(existingArtist);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
