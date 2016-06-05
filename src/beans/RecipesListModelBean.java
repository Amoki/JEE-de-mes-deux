package beans;

import model.RecipesModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class RecipesListModelBean implements Serializable {
    private List<RecipesModel> recipeList;
    public RecipesListModelBean()
    {
        recipeList=new ArrayList<RecipesModel>();
    }

    public void addRecipeList(RecipesModel recipe) {
        this.recipeList.add(recipe);
    }

    public List<RecipesModel> getRecipeList()
    {
        return recipeList;
    }
}
