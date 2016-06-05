import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;
import dao.instance.UserDao;
import model.RecipesModel;
import beans.UserModelBean;

import java.util.List;

/**
 * Created by Hugo on 23/05/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        UserDao userDao= DaoFabric.getInstance().createUserDao();
        RecipesDao recipesDao=DaoFabric.getInstance().createRecipesDao();
        UserModelBean user1=new UserModelBean(999, "Doe", "John",55, "jdoe", "pwd", "bla@bla.bla");
        RecipesModel recipe1=new RecipesModel(999, "Fish Salad", "bla bla bal bla", 5, 180, 10, "salad");
        RecipesModel recipe2=new RecipesModel(999, "Fresh Meat", "bla bla bal bla", 1, 20, 1, "meat");
        userDao.addUser(user1);
        recipesDao.addRecipes(recipe1);
        recipesDao.addRecipes(recipe2);
        List<UserModelBean> userList=userDao.getAllUser();
        List<RecipesModel> recipeList=recipesDao.getAllRecipes();
        for(UserModelBean userTmp:userList){
            System.out.println("User added:"+userTmp);
        }
        for(RecipesModel recipeTmp:recipeList){
            System.out.println("Recipes added:"+ recipeTmp);
        }
    }
}
