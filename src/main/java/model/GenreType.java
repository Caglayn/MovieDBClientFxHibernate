package model;

import java.util.ArrayList;
import java.util.HashMap;

public enum GenreType {
    IMAX(1, "IMAX"),
    Crime(2, "Crime"),
    Animation(3, "Animation"),
    Documentary(4, "Documentary"),
    Romance(5, "Romance"),
    Mystery(6, "Mystery"),
    Children(7, "Children"),
    Musical(8, "Musical"),
    FilmNoir(9, "Film-Noir"),
    Fantasy(10, "Fantasy"),
    Horror(11, "Horror"),
    Drama(12, "Drama"),
    Action(13, "Action"),
    Thriller(14, "Thriller"),
    Western(15, "Western"),
    SciFi(16, "Sci-Fi"),
    Comedy(17, "Comedy"),
    Adventure(18, "Adventure"),
    War(19, "War"),
    NO_GENRE(999, "(no genres listed)");

    private static final HashMap<String, GenreType> BY_NAME = new HashMap<String, GenreType>();
    private static final ArrayList<GenreEntity> ALL_GENRES = new ArrayList<>();

    static {
        for(GenreType genre : values()) {
            BY_NAME.put(genre.name, genre);
            ALL_GENRES.add(new GenreEntity(genre));
        }
    }

    private long id;
    private String name;

    private GenreType(long id, String name) {
        this.id=id;
        this.name=name;
    }

    public static GenreType byName(String name) {
        GenreType genre = BY_NAME.get(name);
        if (genre != null)
            return BY_NAME.get(name);
        else
            return NO_GENRE;
    }

    public static ArrayList<GenreEntity> getAllGenres(){
        return ALL_GENRES;
    }

    public String getName(){
        return this.name;
    }

    public long getId(){
        return this.id;
    }
}
