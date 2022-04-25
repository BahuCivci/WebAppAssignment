/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.Account;
import com.webapps2022.entity.RequestMoney;
import com.webapps2022.entity.SystemUserGroup;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.webapps2022.entity.User;
import com.webapps2022.entity.Transaction;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class UserStorageServiceBean implements UserStorageService {

    @PersistenceContext(unitName = "WebappsDBPU")
    EntityManager entityManager;

    public UserStorageServiceBean() {
    }

//    @Override
//    public void registerUser(String email, String password, String firstName, String lastName, Account account) {
//        //try-catch
//
//        try {
//            User user = new User(email, password, firstName, lastName, new Account(1000));
//            
//
//            entityManager.persist(user);
//            entityManager.persist(account);
//            
//        } catch (Exception ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    @Override
    public List<User> getAllUsers() {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery cQuery = builder.createQuery(User.class);
//        Root<User> cp = cQuery.from(User.class);
//        cQuery.select(cp);
//        cQuery.where(builder.equal(cp.get("isValid"), "true"));
//        return entityManager.createQuery(cQuery).getResultList();
        return entityManager.createNamedQuery("findAllUsers").getResultList();
    }
@Override
public List<RequestMoney> getRequestDemander (String email){

return entityManager.createNamedQuery("getAllRequestsByDemander", RequestMoney.class).setParameter("demander", email).getResultList();
}
@Override
public List<RequestMoney> getRequestSupplier (String email){

return entityManager.createNamedQuery("getAllRequestsBySupplier", RequestMoney.class).setParameter("supplier", email).getResultList();
}


    @Override
    public List<String> getAllEmail() {
        return entityManager.createNamedQuery("findAllEmails").getResultList();
    }

    @Override
    public List<String> getAllRequests() {

        return entityManager.createNamedQuery("getAllRequests").getResultList();

    }

    @Override
    public void addBankUser(User user) {
        entityManager.persist(user);

    }

//    @Override
//    public User authenticate(String email, String password) {
//
//        List<User> userList = entityManager.createNamedQuery("findUser", User.class).setParameter("email", email).setParameter("password", password).getResultList();
//        if (userList.isEmpty()) {
//            return null;
//        }
//        User firstUser = userList.get(0);
//        return firstUser;
//    }
    @Override
    public void demandMoney(RequestMoney requestMoney) {

        entityManager.persist(requestMoney);

    }

    @Override
    public User searchUserById(Integer id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User searchUserByEmail(String email) {
        User user = entityManager.createNamedQuery("findUser", User.class).setParameter("email", email).getSingleResult();
        return user;
    }

//    @Override
//    public User authenticate(String email, String pass) throws Exception {
//        List<User> userList = entityManager.createNamedQuery("findAllUsers", User.class)
//    }
    @Override
    public void removeBankUser(Integer userId) {
        User user = this.searchUserById(userId);
        user.setIsValid("false");
        //change the isValid to false instead of deleting it 
        entityManager.merge(user);
        System.out.println("Delete user successfully.");
    }

    @Override
    public void editBankUser(User bankUser) {
        entityManager.merge(bankUser);
        System.out.println("Update user successfully.");
    }

    @Override
    public void editDemandTransaction(User bankUser) {
        entityManager.merge(bankUser);
        System.out.println("Update user successfully.");
    }

    @Override
    public void editSupplyTransaction(User bankUser) {
        entityManager.merge(bankUser);
        System.out.println("Update user successfully.");
    }
}
