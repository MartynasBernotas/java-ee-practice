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
        @NamedQuery(name = "Artist.findAll", query = "select a from Artist as a")
})
@Table(name = "ARTIST")
@Getter @Setter
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    private List<Song> songs = new ArrayList<>();


    public Artist() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist player = (Artist) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
