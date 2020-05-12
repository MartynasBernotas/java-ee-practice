package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Artist;
import lt.vu.entities.Song;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AlbumDAO;
import lt.vu.persistence.ArtistDAO;
import lt.vu.persistence.SongDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class SongsForAlbum implements Serializable {

    @Inject
    private ArtistDAO artistDAO;

    @Inject
    private AlbumDAO albumDAO;

    @Inject
    private SongDAO songDAO;

    @Getter
    @Setter
    private Artist artist;

    @Getter
    @Setter
    private Album album;

    @Getter @Setter
    private Song songToCreate = new Song();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("albumId"));
        this.album = albumDAO.findOne(artistId);
        this.artist = artistDAO.findOne(this.album.getArtist().getId());
    }

    @Transactional
    @LoggedInvocation
    public String createSong() {
        songToCreate.setArtist(this.artist);
        songToCreate.setAlbum(this.album);
        songDAO.persist(songToCreate);
        return "songs?faces-redirect=true&albumId=" + this.album.getId();
    }
}
