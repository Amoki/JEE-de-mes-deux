package model;

import dao.fabric.DaoFabric;
import dao.instance.UserDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean
@RequestScoped
//Durée de vue uniquement lors d'une requète
// même propriétés que UserModelBean mais portée différente
public class UserSubmissionModelBean extends UserModelBean{
    private UserDao userDao;
    public UserSubmissionModelBean() {
        this.userDao= DaoFabric.getInstance().createUserDao();
    }
    public String checkUser(SignupBean signupBean){
        UserModelBean user = null; //this.userDao.checkUser(signupBean.getLogin(), signupBean.getPwd());
        System.out.println(user);
        if( user!=null){
            //récupère l'espace de mémoire de JSF
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            //place l'utilisateur dans l'espace de mémoire de JSF
            sessionMap.put("loggedUser", user);
            //redirect the current page
            return "userDisplay.xhtml";
        } else{
            //redirect the current page
            return "signup.xhtml";
        }
    }
}
