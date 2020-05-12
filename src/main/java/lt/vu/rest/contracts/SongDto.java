package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Style;

import java.util.List;

@Getter
@Setter
public class SongDto {
    private String Name;

    private String ArtistName;

    private String AlbumName;

    private List<Style> Styles;

    private String Link;
}
