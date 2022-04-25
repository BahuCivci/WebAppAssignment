package com.webapps2022.jsf;


import com.webapps2022.ejb.UserService;
import com.webapps2022.entity.Account;
import com.webapps2022.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author parisis
 */
@Named
@RequestScoped
public class RegistrationBean {

    @EJB
    UserService usrSrv;
    String email;
    String password;
    String firstName;
    String lastName;
    String type;

    public RegistrationBean() {

    }

    //call the injected EJB
    public String register() {
        usrSrv.registerUser(email, password, firstName, lastName, type);
        return "index";
    }

    public UserService getUsrSrv() {
        return usrSrv;
    }

    public void setUsrSrv(UserService usrSrv) {
        this.usrSrv = usrSrv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    


    
}
