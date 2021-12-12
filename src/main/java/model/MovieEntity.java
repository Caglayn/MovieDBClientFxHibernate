package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "movies")
public class MovieEntity {


    @Id
    @Column(name = "id")
    private long movieId;

    @Column(name = "name")
    private String title;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TagEntity> tags = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    private LinkEntity link;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(targetEntity = GenreEntity.class, fetch = FetchType.EAGER)
    private Set<GenreEntity> genres = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RatingEntity> ratings = new HashSet<>();

    public MovieEntity(String title) {
        this.title = title;
    }

    public void addTag(TagEntity tag){
        this.tags.add(tag);
    }

    public void addGenre(GenreEntity genre){
        this.genres.add(genre);
    }

    public  void addRating(RatingEntity rating){
        this.ratings.add(rating);
    }

    public LinkEntity getLink(){
        if (this.link == null){
            this.link = new LinkEntity();
        }
        return this.link;
    }
}
