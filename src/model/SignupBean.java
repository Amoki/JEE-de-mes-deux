package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class SignupBean implements Serializable {
    private String login;
    private String pwd;
    private String pwdCheck;
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    public SignupBean() { }

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPwd() {return pwd;}

    public void setPwd(String pwd) {this.pwd = pwd;}

    public String getPwdCheck() {
        return pwdCheck;
    }

    public void setPwdCheck(String pwdCheck) {
        this.pwdCheck = pwdCheck;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
