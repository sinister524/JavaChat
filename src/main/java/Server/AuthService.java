package Server;

import org.sqlite.SQLiteException;

import java.sql.*;

public abstract class AuthService {
    private static Connection connection;
    private static Statement statement;

    public static void connect (){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chatDB");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect () {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String authorization (String login, int password){
        String query = String.format("SELECT nick_name FROM authUsers WHERE login = '%s' & password = %s", login , password);
        try {
            ResultSet nick = statement.executeQuery("SELECT nick_name FROM authUsers WHERE login = '" + login + "' AND password = " + password);
            if (nick.next()){
                return nick.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registration (String login, int password, String nick){
        String query = String.format("INSERT INTO authUsers(login, password, nick_name) VALUES('%s', %d, '%s')", login, password, nick);
        try {
            statement.execute(query);
            return true;
        } catch (SQLiteException e){
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
