package view.login;

import view.mainViews.MoviesOverViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.ViewUtil;

public class LoginController {

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldUserName;

    @FXML
    void actionLogin(ActionEvent event) {
        System.out.println("Login clicked..");
        showMoviesOverView();

    }

    private void showMoviesOverView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../mainViews/MoviesOverView.fxml"));
        try {
            AnchorPane moviesOverView = (AnchorPane) loader.load();
            ViewUtil.getInstance().getRootPane().setCenter(moviesOverView);
            MoviesOverViewController controller = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
