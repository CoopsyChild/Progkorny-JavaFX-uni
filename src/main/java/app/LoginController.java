package app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class LoginController {
    @FXML
    private Label loginErrorMessageLabel;
    @FXML
    private Button cancelButton;

    @FXML
    protected void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}