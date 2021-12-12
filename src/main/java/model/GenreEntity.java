package model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "genres")
public class GenreEntity {

    @Id
    @Column(name = "id")
    private long genreId;

    @Column(name = "genre")
    private String genre;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private Set<MovieEntity> movies = new HashSet<>();

    public GenreEntity(GenreType eGenre) {
        this.genreId = eGenre.getId();
        this.genre = eGenre.getName();
    }

    public void addMovie(MovieEntity movie){
        this.movies.add(movie);
    }
}
