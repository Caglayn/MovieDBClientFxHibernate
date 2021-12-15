package utils;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewUtil {
    private static ViewUtil instance;

    private Stage primary;
    private BorderPane rootPane;
    private Scene scene;


    private ViewUtil() {
    }

    public static ViewUtil  getInstance(){
        if (instance == null){
            instance = new ViewUtil();
        }
        return instance;
    }

    public Stage getPrimaryStage(){
        return this.primary;
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primary = primaryStage;
    }

    public BorderPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
