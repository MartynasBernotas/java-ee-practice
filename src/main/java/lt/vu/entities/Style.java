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
        @NamedQuery(name = "Style.findAll", query = "select a from Style as a")
})
@Table(name = "STYLE")
@Getter
@Setter
public class Style implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "SONGS_STYLES")
    private List<Song> songs = new ArrayList<>();

    public Style() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Style style = (Style) o;
        return Objects.equals(id, style.id) &&
                Objects.equals(name, style.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
