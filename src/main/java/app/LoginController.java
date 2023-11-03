package app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController implements Initializable {
    @FXML
    private Label loginErrorMessageLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void onLoginButtonClick(ActionEvent event) {
        if (!passwordTextField.getText().isBlank() && !usernameTextField.getText().isBlank()){
            validateLogin(usernameTextField.getText(),passwordTextField.getText());
        } else {
            if (passwordTextField.getText().isBlank() && usernameTextField.getText().isBlank()) {
                loginErrorMessageLabel.setText("Blank Password and Password field.");
            }
            else if (usernameTextField.getText().isBlank()){
                loginErrorMessageLabel.setText("Blank Username field.");
            }
            else {
                loginErrorMessageLabel.setText("Blank Password field.");
            }
        }
    }
    public void validateLogin(String username, String password){
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        //TODO  Password hashing

         try {
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(1) FROM user WHERE username=? AND password=?");
             preparedStatement.setString(1,username);
             preparedStatement.setString(2,password);
             ResultSet queryResult = preparedStatement.executeQuery();

             while (queryResult.next()){
                 if (queryResult.getInt(1) == 1) {
                     loginErrorMessageLabel.setText("Succes!");
                 } else {
                     loginErrorMessageLabel.setText("Invalid Username or Password. Please try again!");
                 }
             }
         } catch (Exception e){
             e.printStackTrace();
             e.getCause();
         }
    }
}