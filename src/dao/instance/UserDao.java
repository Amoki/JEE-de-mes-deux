package dao.instance;

import model.UserModelBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    public UserDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }

    public void addUser(UserModelBean user) {
        // Création de la requête
        java.sql.Statement query;   try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

            PreparedStatement querySt = connection.prepareStatement("INSERT INTO \"users\" (lastname, firstname, age, login, pwd, email) VALUES (?,?,?,?,?,?)");
            querySt.setString(1, user.getLastname());
            querySt.setString(2, user.getFirstname());
            querySt.setInt(3, user.getAge());
            querySt.setString(4, user.getLogin());
            querySt.setString(5, user.getPwd());
            querySt.setString(6, user.getEmail());

            // Exécution
            querySt.executeUpdate();
            querySt.close();
            // l’utilisateur à la base de données, ATTENTION, utiliser cette fois–ci les PrepareStatement
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserModelBean> getAllUser() {
        //return value
        ArrayList<UserModelBean> userList = new ArrayList<UserModelBean>();
        try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            java.sql.Statement query = connection.createStatement();
            // Executer puis parcourir les résultats
            java.sql.ResultSet rs = query.executeQuery("SELECT * FROM \"users\"");
            while( rs.next() ) {
                UserModelBean u = new UserModelBean(
                        rs.getInt("id"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getInt("age"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                userList.add(u);
            }
            rs.close();
            query.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public UserModelBean checkUser(String login, String pwd) {
        UserModelBean user = null;
        try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            PreparedStatement query = connection.prepareStatement("SELECT * FROM \"users\" WHERE login = ? AND pwd = ?");
            // Executer puis parcourir les résultats
            query.setString((1), login);
            query.setString((2), pwd);

            ResultSet rs = query.executeQuery();
            if( rs.next()) {
                user = new UserModelBean(
                    rs.getInt("id"),
                    rs.getString("lastname"),
                    rs.getString("firstname"),
                    rs.getInt("age"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email")
                );
            }
            rs.close();
            query.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
