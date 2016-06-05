package controller;

import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;
import model.RecipesListModelBean;
import model.RecipesModel;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class RecipesControllerBean {
    private RecipesDao recipeDao;

    public RecipesControllerBean() {
        this.recipeDao = DaoFabric.getInstance().createRecipesDao();
    }
    public void loadAllRecipe(){
        ArrayList<RecipesModel> list = this.recipeDao.getAllRecipes();
        RecipesListModelBean recipeList=new RecipesListModelBean();
        for(RecipesModel recipe:list){
            recipeList.addRecipeList(recipe);
        }
        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        sessionMap.put("recipeList", recipeList);
    }

    public String addRecipe(RecipesModel recipe){
        this.recipeDao.addRecipes(recipe);
        //Redirection vers une page de confirmation
        return "recipeForm.xhtml";
    }

    public String searchRecipe(RecipesModel recipe){
        RecipesListModelBean list = this.recipeDao.searchRecipe(recipe);
        //TODO effectuer une recherche des recettes répondant aux critères passés en
        //parametre, récupérer la liste des recettes correspondantes et demander à
        // recipeResultList.xhtml d’afficher les recettes trouvées
        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        sessionMap.put("recipeSearchList", list);
        return "recipeResultList.xhtml";
    }

    public String displayRecipeDetail(RecipesModel recipe){
        //TODO demander à recipeDetail.jsf d’afficher les details de la recette passée en paramètre
        return "recipeDetail.jsf";
    }
}
