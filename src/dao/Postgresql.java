package dao;

import bean.UserBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Postgresql {
    public static void saveUser(UserBean user) throws SQLException, ClassNotFoundException {
        // Chargement du Driver, puis établissement de la connexion
        Class.forName("org.postgresql.Driver");
        java.sql.Connection cnx = java.sql.DriverManager.getConnection( "jdbc:postgresql://192.168.56.101:5432/jee", "jee", "azerty");

        // Création de la requête compilée
        PreparedStatement querySt = cnx.prepareStatement("INSERT INTO \"user_jee\" (name, surname, age, preference) VALUES (?,?,?,?)");
        // Définition de la valeur du premier paramètre
        querySt.setString((1), user.getName());
        querySt.setString((2), user.getSurname());
        querySt.setInt((3), user.getAge());
        querySt.setString((4), user.getPreference());
        // Exécution
        querySt.executeUpdate();

        querySt.close();
        cnx.close();
    }
}
