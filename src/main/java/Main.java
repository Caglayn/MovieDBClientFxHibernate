import controller.GenreEntityController;
import controller.MovieEntityController;
import controller.UserEntityController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
    public static void main(String[] args) {
        MovieEntityController movieController = new MovieEntityController();
        GenreEntityController genreController = new GenreEntityController();
        UserEntityController userController = new UserEntityController();

        ArrayList<MovieEntity> movies = movieController.list();

        for (int i = 0; i<10;i++){
            System.out.println(movies.get(i));
            System.out.println(movies.get(i).getLink());
            movies.get(i).getGenres().forEach(System.out::println);
            movies.get(i).getRatings().forEach(System.out::println);
            movies.get(i).getTags().forEach(System.out::println);
            System.out.println("//////////////////////////////////////////////////////////////////////////////////");
        }

        /*genreController.insertAllGenresToDB();
        userController.insertUsersToDB(5000);*/

/*        MovieEntity entity = new MovieEntity("Matrix 3");
        entity.setMovieId(5L);
        TagEntity tag1 = new TagEntity().builder().tag("Harika").build();
        TagEntity tag2 = new TagEntity().builder().tag("Muthis").build();
        LinkEntity link = new LinkEntity().builder().imdbId("12345").tmdbId("54321").build();
        GenreEntity genre1 = new GenreEntity(GenreType.Animation);
        GenreEntity genre2 = new GenreEntity(GenreType.Crime);
        RatingEntity rating1 = new RatingEntity().builder().rating(3.5).ratedAt(new Date(new Timestamp(System.currentTimeMillis()).getTime())).build();
        RatingEntity rating2 = new RatingEntity().builder().rating(0.5).ratedAt(new Date(new Timestamp(System.currentTimeMillis()).getTime())).build();
        UserEntity user1 = new UserEntity(1);
        UserEntity user2 = new UserEntity(2);
        entity.setLink(link);
        entity.addTag(tag1);
        entity.addTag(tag2);
        tag1.setMovie(entity);
        tag2.setMovie(entity);
        entity.addGenre(genre1);
        entity.addGenre(genre2);
        genre1.addMovie(entity);
        genre2.addMovie(entity);
        entity.addRating(rating1);
        entity.addRating(rating2);
        rating1.setMovie(entity);
        rating2.setMovie(entity);
        rating1.setUser(user1);
        rating2.setUser(user2);
        user1.addRating(rating1);
        user2.addRating(rating2);
        tag1.setUser(user1);
        tag2.setUser(user1);
        user1.addTag(tag1);
        user1.addTag(tag2);

        movieController.create(entity);
        System.out.println("Ekleme tamam");*/


        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("view/mainViews/MainView.fxml"));
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("view/mainViews/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
