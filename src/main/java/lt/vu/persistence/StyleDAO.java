package lt.vu.persistence;

import lt.vu.entities.Style;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StyleDAO {

    @Inject
    private EntityManager em;

    public void persist(Style style){
        this.em.persist(style);
    }

    public Style findOne(Integer id){
        return em.find(Style.class, id);
    }

    public Style update(Style style){
        return em.merge(style);
    }

    public List<Style> loadAll() {
        return em.createNamedQuery("Style.findAll", Style.class).getResultList();
    }
}