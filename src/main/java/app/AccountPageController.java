package app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class AccountPageController {

    @FXML
    private PasswordField newPassField;
    @FXML
    private PasswordField newPassConfirmField;
    @FXML
    private Button newPassSubmitButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label registrationDateLabel;
    @FXML
    private Label usernameLabel;

    public void initialize(){
        lastNameLabel.setText(UserSession.getInstance().getLastName());
        usernameLabel.setText(UserSession.getInstance().getUsername());
        registrationDateLabel.setText(UserSession.getInstance().getRegistrationDate());
    }

    public void onNewPassSubmitButton(){
        if(!newPassField.getText().isBlank() && !newPassConfirmField.getText().isBlank()) {
            if (newPassField.getText().equals(newPassConfirmField.getText())) {
                QueryHelper.updateUserPassword(UserSession.getInstance().getId(),newPassField.getText());
                showInfoDialog("Password successfully updated","Success!");
            } else {
                showErrorDialog("Provided passwords don't match. Please try again","Match Error");
            }
        }
        else {
            showErrorDialog("Blank Password field(s).","Blank Field Error");
        }
    }
    public void showErrorDialog(String message, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showInfoDialog(String message, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onLogoutButtonClick(){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root,520, 400));
            loginStage.show();
            UserSession.getInstance().clearUserSession();
            ((Stage) logoutButton.getScene().getWindow()).close();
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void onExitButtonClick() {
        Platform.exit();
    }
}
