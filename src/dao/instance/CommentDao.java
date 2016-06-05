package dao.instance;

import model.CommentModel;
import model.RecipesModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Hugo on 25/05/2016.
 */
public class CommentDao {
    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;
    public CommentDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }
    public void addComment(CommentModel comment) {
        // Création de la requête
        java.sql.Statement query;   try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

            PreparedStatement querySt = connection.prepareStatement("INSERT INTO \"comments\" (recipe, author, comment) VALUES (?,?,?)");
            querySt.setInt(1, comment.getRecipe());
            querySt.setInt(2, comment.getAuthor());
            querySt.setString(3, comment.getComment());
            // Exécution
            querySt.executeUpdate();
            querySt.close();
            // l’utilisateur à la base de données, ATTENTION, utiliser cette fois–ci les PrepareStatement
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CommentModel> getRecipeComments(RecipesModel recipe) {
        //return value
        ArrayList<CommentModel> commentList = new ArrayList<>();
        try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            // Executer puis parcourir les résultats
            PreparedStatement querySt = connection.prepareStatement("SELECT * FROM \"comments\" WHERE recipe = ?");
            querySt.setInt(1, recipe.getId());
            java.sql.ResultSet rs = querySt.executeQuery();
            while( rs.next() ) {
                CommentModel u = new CommentModel(
                        rs.getInt("author"),
                        rs.getInt("recipe"),
                        rs.getString("comment")
                );
                commentList.add(u);
            }
            rs.close();
            querySt.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }

}
