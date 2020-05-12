package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Artist;
import lt.vu.persistence.ArtistDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Artists {

    @Inject
    private ArtistDAO artistDAO;

    @Getter
    @Setter
    private Artist artistToCreate = new Artist();

    @Getter
    private List<Artist> allArtists;

    @PostConstruct
    public void init(){
        loadAllArtists();
    }

    @Transactional
    public String createArtist(){
        this.artistDAO.persist(artistToCreate);
        return "artist?faces-redirect=true";
    }

    private void loadAllArtists(){
        this.allArtists = artistDAO.loadAll();
    }
}
