package Server;

import org.sqlite.SQLiteException;

import java.sql.*;

public abstract class AuthService {
    private static Connection connection;
    private static Statement statement;

    protected static void connect (){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chatDB");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void disconnect () {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static String authorization (String login, String password){
        String query = String.format("SELECT nick_name FROM authUsers WHERE login = '%s' AND password = '%s'", login , password);
        try {
            ResultSet nick = statement.executeQuery(query);
            if (nick.next()){
                return nick.getString("nick_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static boolean registration (String login, String password, String nick){
        String query = String.format("INSERT INTO authUsers(login, password, nick_name) VALUES('%s', '%s', '%s')", login, password, nick);
        try {
            statement.execute(query);
            return true;
        }  catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
