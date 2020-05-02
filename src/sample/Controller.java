package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passInput;

    @FXML
    private Button authSignButton;

    @FXML
    private Button loginSignUpButton;

    private static final Logger log = Logger.getLogger(Controller.class.getName());

    @FXML
    void initialize() {

        authSignButton.setOnAction(event -> {
            String loginText = loginInput.getText().trim();
            String loginPassword = passInput.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            } else {
                System.out.println("Login and pass empty");
                log.info("Login and pass empty");
                errorLoginAnim();
            }
        });

        loginSignUpButton.setOnAction(event -> {
            openNewScene("/sample/signUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }

        if (counter >= 1) {
            openNewScene("/sample/app.fxml");
        } else {
            errorLoginAnim();
        }
    }

    public void errorLoginAnim(){
        Shake userLoginAnim = new Shake(loginInput);
        Shake userPassAnim = new Shake(passInput);
        userLoginAnim.playAnim();
        userPassAnim.playAnim();
    }

    public void openNewScene(String window) {
        loginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}


