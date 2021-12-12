package model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;

    @Column(name = "name")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<RatingEntity> ratings = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<TagEntity> tags = new HashSet<>();

    public UserEntity(long userId) {
        this.userId = userId;
        this.name = "Secret_User_Name";
    }

    public void addRating(RatingEntity rating){
        this.ratings.add(rating);
    }

    public void addTag(TagEntity tag) {
        this.tags.add(tag);
    }
}
