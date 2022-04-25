package com.webapps2022.jsf;

import com.webapps2022.ejb.TypeStorageService;
import com.webapps2022.ejb.UserStorageService;
import com.webapps2022.entity.Account;
import com.webapps2022.entity.TransactionType;
import com.webapps2022.entity.User;
import com.webapps2022.utils.FacesContextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    TypeStorageService typeRepository;

     User user = null;
    @EJB
     UserStorageService userRepository;
     List<User> userList;
     TransactionType type = new TransactionType();
    List<String> demandTransactions, supplyTransactions;

    public UserController() {
                
    }

    public User getUser() {

        if (this.user == null) {
            String email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

            try {
                this.user = userRepository.searchUserByEmail(email);
            } catch (Exception ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "cannot get user by id", ex);
            }
        }
        return this.user;
    }

    public String addType() {
        try {
            typeRepository.addTransactionType(this.type);
        } catch (Exception ex) {
            Logger.getLogger(TransactionController.class
                    .getName()).log(Level.SEVERE, "Cannot add transaction type!", ex);
        }

        return "finish.xhtml";
    }

    public TypeStorageService getTypeRepository() {
        return typeRepository;
    }

    public void setTypeRepository(TypeStorageService typeRepository) {
        this.typeRepository = typeRepository;
    }

    public UserStorageService getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserStorageService userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {
        this.userList = userRepository.getAllUsers();
        return userList;
    }

    public void setUser(User user) {

        this.user = user;
    }
  
    public String addUser(User user) {
        try {
            User bankUser = new User();
            bankUser.setEmail(user.getEmail());
            bankUser.setFirstName(user.getFirstName());
            bankUser.setLastName(user.getLastName());
            bankUser.setPassword(user.getPassword());
            bankUser.setType(user.getType());
            bankUser.setIsValid("true");
//            bankUser.setPhoneNumber(user.getPhoneNumber());

            userRepository.addBankUser(bankUser);
            Account account = new Account(1000.0);
            account.setOwner(bankUser);

            bankUser.setAccount(account);
            editUser(bankUser);

        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class
                    .getName()).log(Level.SEVERE,
                            "cannot add user into database", ex);
        }
        return "login";

    }

    public void removeUser(Integer id) {
        System.out.println(id);
        try {
            userRepository.removeBankUser(id);
            userList.remove(id);
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class
                    .getName()).log(Level.SEVERE,
                            "cannot remove user", ex);
        }

    }

    public void editUser(User bankUser) {
        System.out.println(bankUser.getFirstName());
        try {
            userRepository.editBankUser(bankUser);
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class
                    .getName()).log(Level.SEVERE,
                            "cannot edit user", ex);
        }

    }







    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../index.xhtml";
    }

    public User getUserByUserId(Integer userId) {
        return userRepository.searchUserById(userId);
    }


}
