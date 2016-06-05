package dao.instance;

import beans.RecipesListModelBean;
import model.RecipesModel;
import beans.SearchRecipeBean;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class RecipesDao {
    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    public RecipesDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }
    public void addRecipes(RecipesModel recipes) {
        // Création de la requête
        try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

            PreparedStatement querySt = connection.prepareStatement("INSERT INTO \"recipes\" (title, description, expertise, nbpeople, duration, type) VALUES (?,?,?,?,?,?)");
            querySt.setString((1), recipes.getTitle());
            querySt.setString((2), recipes.getDescription());
            querySt.setInt((3), recipes.getExpertise());
            querySt.setInt((4), recipes.getNbpeople());
            querySt.setInt((5), recipes.getDuration());
            querySt.setString((6), recipes.getType());
            // Exécution
            querySt.executeUpdate();
            querySt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RecipesModel> getAllRecipes() {
        //return value
        ArrayList<RecipesModel> recipesList = new ArrayList<RecipesModel>();
        try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            java.sql.Statement query = connection.createStatement();
            // Executer puis parcourir les résultats
            java.sql.ResultSet rs = query.executeQuery("SELECT * FROM \"recipes\"");
            while( rs.next() ) {
                RecipesModel r = new RecipesModel();
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setExpertise(rs.getInt("expertise"));
                r.setNbpeople(rs.getInt("nbpeople"));
                r.setDuration(rs.getInt("duration"));
                r.setType(rs.getString("type"));
                recipesList.add(r);
            }
            rs.close();
            query.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipesList;
    }

    public List<RecipesModel> searchRecipes(int duration,int expertise,int nbPeople,String type){
        List<RecipesModel> recipeModels = new ArrayList<>();

        String sql = 	"select id, title, description, duration, expertise, nbPeople, type " +
                "from recipe " +
                "where duration <= ? and expertise <= ? and nbpeople >= ?";
        if(type.compareTo("[ALL]") != 0) sql += " and type = ? ";

        try {
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,duration);
            preparedStatement.setInt(2,expertise);
            preparedStatement.setInt(3,nbPeople);
            if(type.compareTo("[ALL]") != 0)
                preparedStatement.setString(4,type);

            ResultSet resultSet  = preparedStatement.executeQuery();

            while (resultSet.next()){
                recipeModels.add(new RecipesModel(resultSet.getInt("id"), resultSet.getString("title"),resultSet.getString("description"),resultSet.getInt("expertise"),resultSet.getInt("nbpeople"),resultSet.getInt("duration"),resultSet.getString("type")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeModels;
    }

    public RecipesListModelBean searchRecipe(RecipesModel recipe) {
        RecipesListModelBean list = new RecipesListModelBean();
        try {
            // create connection
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            String sql = "SELECT * FROM \"recipes\" WHERE 1=1";

            List<String> stringParams = new ArrayList<>();
            List<Integer> intParams = new ArrayList<>();

            if(!recipe.getDescription().equals(SearchRecipeBean.ALL_VALUES_STRING)) {
                sql += " AND description = ?";
                stringParams.add(recipe.getDescription());
            }
            if(!recipe.getTitle().equals(SearchRecipeBean.ALL_VALUES_STRING)) {
                sql += " AND title = ?";
                stringParams.add(recipe.getTitle());
            }
            if(!recipe.getType().equals(SearchRecipeBean.ALL_VALUES_STRING)) {
                sql += " AND type = ?";
                stringParams.add(recipe.getType());
            }
            if(recipe.getExpertise() != (SearchRecipeBean.ALL_VALUES_INT)) {
                sql += " AND expertise = ?";
                intParams.add(recipe.getExpertise());
            }
            if(recipe.getNbpeople() != (SearchRecipeBean.ALL_VALUES_INT)) {
                sql += " AND nbpeople = ?";
                intParams.add(recipe.getNbpeople());
            }
            if(recipe.getDuration() != (SearchRecipeBean.ALL_VALUES_INT)) {
                sql += " AND duration = ?";
                intParams.add(recipe.getDuration());
            }

            PreparedStatement query = connection.prepareStatement(sql);

            int index = 1;
            for(String param : stringParams) {
                query.setString(index++, param);
            }
            for(int param : intParams) {
                query.setInt(index++, param);
            }


            // Executer puis parcourir les résultats
            java.sql.ResultSet rs = query.executeQuery();
            while( rs.next() ) {
                list.addRecipeList(new RecipesModel(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("expertise"),
                        rs.getInt("duration"),
                        rs.getInt("nbpeople"),
                        rs.getString("type")
                ));
            }
            rs.close();
            query.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
