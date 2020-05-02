package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passInput;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private TextField signUpCountry;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {

            signUpNewUser();
        });
    }

    private void signUpNewUser() {
        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = loginInput.getText();
        String password = passInput.getText();
        String location = signUpCountry.getText();
        String gender = "";
        if (signUpCheckBoxMale.isSelected()) {
            gender = "Мужской";
        } else {
            gender = "Жунский";
        }

        User user = new User(firstName, lastName, userName, password, location, gender);

        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.signUpUser(user);
    }
}
