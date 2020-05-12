package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Song.findAll", query = "select s from Song as s")
})
@Table(name = "SONG")
@Getter
@Setter
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "NAME")
    private String name;

    @Pattern(message="Please enter valid link" , regexp="(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})")
    @Column(name = "LINK")
    private String link;

    @ManyToOne
    @JoinColumn(name="ARTIST_ID")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="ALBUM_ID")
    private Album album;

    @ManyToMany(mappedBy = "songs")
    private List<Style> styles = new ArrayList<>();

    public Song() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(name, song.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}

