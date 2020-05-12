package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Song;
import lt.vu.mybatis.model.Style;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.SongMapper;
import lt.vu.mybatis.dao.SongsStylesMapper;
import lt.vu.mybatis.dao.StyleMapper;
import lt.vu.mybatis.model.SongsStyles;
import lt.vu.persistence.SongDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class SongUpdate implements Serializable {

    @Inject
    private SongDAO songsDao;

    @Getter
    @Setter
    private Song song = new Song();
    @Inject
    private SongMapper songsMapper;

    @Inject
    private SongsStylesMapper songsStylesMapper;

    @Inject
    private StyleMapper stylesMapper;

    @Getter
    @Setter
    private SongsStyles songsStyles = new SongsStyles();

    @Getter
    @Setter
    private Style style = new Style();

    @Getter
    private List<lt.vu.mybatis.model.Style> stylesList;

    @Getter
    private List<lt.vu.mybatis.model.Style> styleListFree;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer songId = Integer.parseInt(requestParameters.get("songId"));
        this.song = songsDao.findOne(songId);
        loadSongStyles(songId);
        loadSongFreeStyles(songId);
    }

    private void loadSongStyles(Integer songId){
        this.stylesList = stylesMapper.selectStyleBySong(songId);
    }

    private void loadSongFreeStyles(Integer songId){
        this.styleListFree = stylesMapper.selectStyleBySongFree(songId);
    }

    @Transactional
    @LoggedInvocation
    public String updateSongStyle() {
        songsStyles.setSongsId(this.song.getId());
        songsStyles.setStylesId(this.style.getId());
        songsStylesMapper.insert(songsStyles);
        return "song.xhtml?songId=" + this.song.getId() +  "&faces-redirect=true";
    }

    @Transactional
    @LoggedInvocation
    public String updateSongLink() {
        songsDao.update(this.song);
        return "song.xhtml?songId=" + this.song.getId() + "&faces-redirect=true";
    }
}
