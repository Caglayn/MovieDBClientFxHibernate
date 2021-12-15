import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.ViewUtil;
import view.login.LoginController;

public class Main extends Application {
    public static void main(String[] args) {
        MovieEntityController movieController = new MovieEntityController();
        GenreEntityController genreController = new GenreEntityController();
        UserEntityController userController = new UserEntityController();

//        movieController.readFromFileAndWriteToDBAllMovies();

//        ArrayList<MovieEntity> movies = movieController.list();
//
//        for (int i = 7; i<10;i++){
//            System.out.println(movies.get(i));
//            System.out.println(movies.get(i).getLink());
//            movies.get(i).getGenres().forEach(System.out::println);
//            movies.get(i).getRatings().forEach(System.out::println);
//            movies.get(i).getTags().forEach(System.out::println);
//            System.out.println("//////////////////////////////////////////////////////////////////////////////////");
//        }

//        genreController.insertAllGenresToDB();
//        userController.insertUsersToDB(1000);


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initPrimaryStage(primaryStage);
        initRootLayout();
        showLoginView();
    }

    private void initRootLayout() {
        Stage primaryStage = ViewUtil.getInstance().getPrimaryStage();
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("view/mainViews/MainView.fxml"));
            ViewUtil.getInstance().setRootPane(root);
            Scene scene =new Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("view/mainViews/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            ViewUtil.getInstance().setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPrimaryStage(Stage primaryStage) {
        ViewUtil.getInstance().setPrimaryStage(primaryStage);
    }

    private void showLoginView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/login/LoginView.fxml"));
        try {
            AnchorPane loginView = (AnchorPane) loader.load();
            ViewUtil.getInstance().getRootPane().setCenter(loginView);
            LoginController controller = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
