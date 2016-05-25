package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Hugo on 23/05/2016.
 */
@ManagedBean
@SessionScoped
public class UserModelBean implements Serializable {
    //contrainte BEAN
    private int id;
    private String lastname;
    private String firstname;
    private int age;
    private String login;
    private String pwd;
    private String email;
    //Contrainte BEAN constructeur sans paramètre

    public UserModelBean() { }

    public UserModelBean(int id, String lastname, String firstname, int age , String login, String pwd, String email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.login = login;
        this.pwd = pwd;
        this.email = email;
    }

    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}
    public String getFirstname() { return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public int getAge() { return age;}
    public void setAge(int age) {this.age = age;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getPwd() {return pwd;}
    public void setPwd(String pwd) {this.pwd = pwd;}
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return  "[ID]:"+this.getId()+
                "[FIRSTNAME]:"+this.getFirstname()+
                ",[LASTNAME]:"+this.getLastname()+
                ",[AGE]:"+this.getAge()+
                ",[LOGIN]:"+this.getLogin()+
                ",[PWD]:"+this.getPwd()+
                ",[EMAIL]:" + this.getEmail();
    }

}

