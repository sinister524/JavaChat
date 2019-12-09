package Server;

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

    public static String authorization (String login, String password){
        String query = String.format("SELECT nick_name FROM authUsers WHERE login = '%s' & password = %s", login , password.hashCode());
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

    public static boolean registration (String login, int password, String nick){
        String query = String.format("INSERT INTO authUsers(login, password, nick_name) VALUES('%s', %d, '%s')", login, password, nick);
        try {
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
