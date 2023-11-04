package app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class QueryHelper {
    private QueryHelper() { // private constructor
    }
    public static Connection dBconnection(){
        DatabaseConnection dbConnection = new DatabaseConnection();
        return dbConnection.getConnection();
    }
    public static Integer selectUserIdByUsername(String username){
        try {
            Connection connection = dBconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM user WHERE username=?");
            preparedStatement.setString(1,username);
            ResultSet queryResult = preparedStatement.executeQuery();
            queryResult.next();
            return queryResult.getInt(1);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }
    public static User selectUserData(Integer id){
        try {
            Connection connection = dBconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, username, password, registration_date, last_name FROM user WHERE id=?");
            preparedStatement.setString(1,id.toString());
            ResultSet queryResult = preparedStatement.executeQuery();
            queryResult.next();
            return new User(queryResult.getString(2),queryResult.getString(5),queryResult.getString(4));
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }

    public static Integer updateUserPassword(Integer id, String newPassword){
        try {
            Connection connection = dBconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password=? WHERE id=?");
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,id.toString());
            return preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return 0;
    }

    public static String getCategoryName(Integer id){
        try {
            Connection connection = dBconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM drink_category WHERE id=?");
            preparedStatement.setString(1,id.toString());
            ResultSet queryResult = preparedStatement.executeQuery();
            queryResult.next();
            return queryResult.getString(1);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }

    public static List<Drink> selectUserDrinkStock(Integer id){
        try {
            Connection connection = dBconnection();
            List<Drink> drinkList=new ArrayList<Drink>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, item_number, name, size, price, category_id FROM drink_stock WHERE owner_id=?");
            preparedStatement.setString(1,id.toString());
            ResultSet queryResult = preparedStatement.executeQuery();

            while (queryResult.next()){
                Drink drink= new Drink(queryResult.getInt(1), queryResult.getString(2), queryResult.getString(3), queryResult.getFloat(4), queryResult.getInt(5),getCategoryName(queryResult.getInt(6)) );
                drinkList.add(drink);
            }
            return drinkList;
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }

    public static String updateUserLastName(String currentUsername, Integer id, String newUsername) {
        try {
            Connection connection = dBconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET last_name=? WHERE id=?");
            preparedStatement.setString(1,newUsername);
            preparedStatement.setString(2,id.toString());
            if(preparedStatement.executeUpdate()>0){
                return newUsername;
            }
            else {
                return currentUsername;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return currentUsername;
    }
}
