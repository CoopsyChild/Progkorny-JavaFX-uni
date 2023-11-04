package app;

import com.mysql.cj.xdevapi.Table;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockPageController {

    @FXML
    private TableView<Drink> drinkTableView;
    @FXML
    private TableColumn<Drink,String> name;
    @FXML
    private TableColumn<Drink,Integer> id;
    @FXML
    private TableColumn<Drink,String> itemNumber;
    @FXML
    private TableColumn<Drink,Integer> price;
    @FXML
    private TableColumn<Drink,String> category;
    @FXML
    private TableColumn<Drink,Float> size;
    @FXML
    private Button logoutButton;
    ObservableList<Drink> drinks;

    public void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<Drink, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Drink, Integer>("id"));
        itemNumber.setCellValueFactory(new PropertyValueFactory<Drink, String>("itemNumber"));
        price.setCellValueFactory(new PropertyValueFactory<Drink, Integer>("price"));
        category.setCellValueFactory(new PropertyValueFactory<Drink, String>("category"));
        size.setCellValueFactory(new PropertyValueFactory<Drink, Float>("size"));
        drinks=FXCollections.observableArrayList(QueryHelper.selectUserDrinkStock(UserSession.getInstance().getId()));
        drinkTableView.setItems(drinks);
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
