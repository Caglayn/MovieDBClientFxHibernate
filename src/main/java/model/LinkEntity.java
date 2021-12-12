package model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "links")
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long link_id;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "tmdb_id")
    private String tmdbId;

/*    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false) // gerek kalmadi
    private MovieEntity movie;*/


}
