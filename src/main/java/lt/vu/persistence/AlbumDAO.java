package lt.vu.persistence;

import lt.vu.entities.Album;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class AlbumDAO {

    @Inject
    private EntityManager em;

    public void persist(Album album){
        this.em.persist(album);
    }

    public Album findOne(Integer id){
        return em.find(Album.class, id);
    }

    public Album update(Album album){
        return em.merge(album);
    }
}
