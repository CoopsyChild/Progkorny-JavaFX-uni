package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class Query {
    private Query () { // private constructor
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
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.getConnection();
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
}
