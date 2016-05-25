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

    public UserSubmissionModelBean() {
    }
}
