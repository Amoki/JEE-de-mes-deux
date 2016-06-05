package beans.controller;

import dao.fabric.DaoFabric;
import dao.instance.UserDao;
import beans.LoginBean;
import beans.UserModelBean;
import beans.UserSubmissionModelBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean
@ApplicationScoped
// Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients
public class UserControllerBean {
    private UserDao userDao;

    public UserControllerBean() {
        this.userDao= DaoFabric.getInstance().createUserDao();
    }

    public String checkUser(LoginBean loginBean){
        UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
        if(user!=null){
            //récupère l'espace de mémoire de JSF
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            //place la liste de recette dans l'espace de mémoire de JSF
            sessionMap.put("user", user);

            //place de l'application dans l'espace de mémoire de JSF
            Map<String, Object> applicationMap = externalContext.getApplicationMap();
            Object rawCounter = applicationMap.get("userCount");
            if(rawCounter == null) {
                applicationMap.put("userCount", 1);
            }
            else {
                int counter = (Integer)rawCounter;
                counter++;
                applicationMap.put("userCount", counter);
            }

            //redirect the current page
            return "home.xhtml";
        } else{
            //redirect the current page
            return "home.xhtml";
        }
    }

    public String checkAndAddUser(UserSubmissionModelBean userSubmitted){
        this.userDao.addUser(userSubmitted);

        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        //place la liste de recette dans l'espace de mémoire de JSF

        sessionMap.put("user", userSubmitted);

        //place de l'application dans l'espace de mémoire de JSF
        Map<String, Object> applicationMap = externalContext.getApplicationMap();
        Object rawCounter = applicationMap.get("userCount");
        if(rawCounter == null) {
            applicationMap.put("userCount", 1);
        }
        else {
            int counter = (Integer)rawCounter;
            counter++;
            applicationMap.put("userCount", counter);
        }

        return "home.xhtml";
    }
}
