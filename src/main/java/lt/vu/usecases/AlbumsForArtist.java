package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Artist;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AlbumDAO;
import lt.vu.persistence.ArtistDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class AlbumsForArtist implements Serializable {

    @Inject
    private ArtistDAO artistDAO;

    @Inject
    private AlbumDAO albumDAO;

    @Getter
    @Setter
    private Artist artist;

    @Getter @Setter
    private Album albumToCreate = new Album();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistDAO.findOne(artistId);
    }

    @Transactional
    @LoggedInvocation
    public String createAlbum() {
        albumToCreate.setArtist(this.artist);
        albumDAO.persist(albumToCreate);
        return "albums?faces-redirect=true&artistId=" + this.artist.getId();
    }
}
