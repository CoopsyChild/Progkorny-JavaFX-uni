package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
