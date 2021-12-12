package utils;

import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ReadFileUtil {
    private static ReadFileUtil instance;

    private ReadFileUtil() {
    }

    public static ReadFileUtil getInstance(){
        if (instance == null){
            instance = new ReadFileUtil();
        }
        return instance;
    }

    /**
     * Reads movie.csv data from file and returns a TreeMap
     */
    public TreeMap<Long, MovieEntity> readMoviesFromFile() {
        TreeMap<Long, MovieEntity> moviesList = new TreeMap<Long, MovieEntity>();
        File file = new File(PropertiesUtil.getInstance().getFilesPath() + "movies.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lineReaded = "";
            reader.readLine();
            while ((lineReaded = reader.readLine()) != null) { // read movies.csv line by line
                MovieEntity tempMovieEntity = parseMovieLine(lineReaded);
                moviesList.put(tempMovieEntity.getMovieId(), tempMovieEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moviesList;
    }

    private MovieEntity parseMovieLine(String lineReaded) {
        MovieEntity tempMovieEntity = new MovieEntity();
        StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','
        String title = "";
        String genres = "";

        if (lineTokenizer.hasMoreTokens())
            tempMovieEntity.setMovieId(Long.valueOf(lineTokenizer.nextToken()));

        while (lineTokenizer.hasMoreTokens()) {
            String tempString = lineTokenizer.nextToken();

            if (lineTokenizer.hasMoreTokens()) {
                title = title + tempString;
            } else {
                genres = tempString;
            }
        }

        tempMovieEntity.setTitle(title);

        parseGenres(tempMovieEntity, genres);

        return tempMovieEntity;
    }

    private void parseGenres(MovieEntity tempMovieEntity, String genres) {
        StringTokenizer genresTokenizer = new StringTokenizer(genres, "|");
        while (genresTokenizer.hasMoreTokens()) {
            tempMovieEntity.addGenre(new GenreEntity(GenreType.byName(genresTokenizer.nextToken())));
        }
    }

    /**
     * Reads links.csv data from file
     */
    public void readLinksFromFile(TreeMap<Long, MovieEntity> moviesList) {
        String path = PropertiesUtil.getInstance().getFilesPath() + "links.csv";
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lineReaded = "";
            reader.readLine();
            while ((lineReaded = reader.readLine()) != null) { // read links.csv line by line
                parseLinkLine(moviesList, lineReaded);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseLinkLine(TreeMap<Long, MovieEntity> moviesList, String lineReaded) {
        MovieEntity tempMovieEntity = null;
        StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','
        if (lineTokenizer.hasMoreTokens()) {
            Long movieId = Long.valueOf(lineTokenizer.nextToken());
            tempMovieEntity = moviesList.get(movieId);
        }

        if (lineTokenizer.hasMoreTokens())
            tempMovieEntity.getLink().setImdbId(lineTokenizer.nextToken());

        if (lineTokenizer.hasMoreTokens())
            tempMovieEntity.getLink().setTmdbId(lineTokenizer.nextToken());
    }


    /**
     * Reads ratings.csv data from file
     */
    public void readRatingsFromFile(TreeMap<Long, MovieEntity> moviesList) {
        String path = PropertiesUtil.getInstance().getFilesPath() + "ratings.csv";
        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lineReaded = "";
            reader.readLine();
            while ((lineReaded = reader.readLine()) != null) { // read links.csv line by line
                parseRatingLine(moviesList, lineReaded);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseRatingLine(TreeMap<Long, MovieEntity> moviesList, String lineReaded) {
        RatingEntity tempRatingEntity = new RatingEntity();
        MovieEntity tempMovieEntity = null;
        StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','

        if (lineTokenizer.hasMoreTokens())
            tempRatingEntity.setUser(new UserEntity(Long.valueOf(lineTokenizer.nextToken())));

        if (lineTokenizer.hasMoreTokens())
            tempMovieEntity = moviesList.get(Long.valueOf(lineTokenizer.nextToken()));

        if (lineTokenizer.hasMoreTokens())
            tempRatingEntity.setRating(Double.valueOf(lineTokenizer.nextToken()));

        if (lineTokenizer.hasMoreTokens())
            tempRatingEntity.setRatedAtAsTimestamp(Long.valueOf(lineTokenizer.nextToken()));

        tempMovieEntity.addRating(tempRatingEntity);
        tempRatingEntity.setMovie(tempMovieEntity);
    }


    public void readTagsFromFile(TreeMap<Long, MovieEntity> moviesList) {
        String path = PropertiesUtil.getInstance().getFilesPath() + "tags.csv";
        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lineReaded = "";
            reader.readLine();
            while ((lineReaded = reader.readLine()) != null) { // read movies.csv line by line
                parseTagLine(moviesList, lineReaded);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseTagLine(TreeMap<Long, MovieEntity> moviesList, String lineReaded) {
        TagEntity tempTagEntity = new TagEntity();
        MovieEntity tempMovieEntity = null;

        StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','

        if (lineTokenizer.hasMoreTokens())
            tempTagEntity.setUser(new UserEntity(Long.valueOf(lineTokenizer.nextToken())));

        if (lineTokenizer.hasMoreTokens())
            tempMovieEntity = moviesList.get(Long.valueOf(lineTokenizer.nextToken()));

        if (lineTokenizer.hasMoreTokens())
            tempTagEntity.setTag(lineTokenizer.nextToken());

        if (lineTokenizer.hasMoreTokens())
            tempTagEntity.setTaggedAtAsTimestamp(Long.valueOf(lineTokenizer.nextToken()));

        tempMovieEntity.addTag(tempTagEntity);
        tempTagEntity.setMovie(tempMovieEntity);
    }

    public TreeMap<Long, MovieEntity> constructAllMovies() {
        TreeMap<Long, MovieEntity> moviesList = this.readMoviesFromFile();
        LogUtil.getInstance().logInfo(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Movies okundu");
        readLinksFromFile(moviesList);
        LogUtil.getInstance().logInfo(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Links okundu");
        readRatingsFromFile(moviesList);
        LogUtil.getInstance().logInfo(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Ratings okundu");
        readTagsFromFile(moviesList);
        LogUtil.getInstance().logInfo(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Tags okundu");

        return moviesList;
    }
}
