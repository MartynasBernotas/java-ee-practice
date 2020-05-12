package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Song;
import lt.vu.persistence.SongDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class Songs implements Serializable {

    @Inject
    private SongDAO songsDao;

    @Getter
    private List<Song> allSongs;

    @PostConstruct
    public void init(){
        loadAllASongs();
    }

    private void loadAllASongs(){
        this.allSongs = songsDao.loadAll();
    }
}
