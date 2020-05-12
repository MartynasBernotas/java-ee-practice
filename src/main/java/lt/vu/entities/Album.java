package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Album.findAll", query = "select a from Album as a")
})
@Table(name = "ALBUM")
@Getter
@Setter
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 100)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="ARTIST_ID")
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Song> songs = new ArrayList<>();

    public Album() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
