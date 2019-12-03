package Server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement statement;

    public void connect (){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chatDB");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close () {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String authorization (String login, String password){
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

    public boolean registration (String login, String password, String nick){
        String query = String.format("INSERT INTO authUsers(login, password, nick_name) VALUES('%s', %d, '%s')", login, password.hashCode(), nick);
        try {
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
