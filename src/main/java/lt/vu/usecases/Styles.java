package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Style;
import lt.vu.persistence.StyleDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Styles {

    @Inject
    private StyleDAO styleDAO;

    @Getter
    @Setter
    private Style styleToCreate = new Style();

    @Getter
    private List<Style> allStyles;

    @PostConstruct
    public void init(){
        loadAllStyles();
    }

    @Transactional
    public String createStyle(){
        this.styleDAO.persist(styleToCreate);
        return "styles?faces-redirect=true";
    }

    private void loadAllStyles(){
        this.allStyles = styleDAO.loadAll();
    }
}
