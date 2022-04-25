package com.webapps2022.ejb;

import com.webapps2022.entity.Account;
import com.webapps2022.entity.SystemUserGroup;
import com.webapps2022.entity.User;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author parisis
 */
@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    public UserService() {
    }

    public void registerUser(String email, String password, String firstName, String lastName, String type) {
        try {
            User sys_user;
            SystemUserGroup sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = password;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);

            // apart from the default constructor which is required by JPA
            // you need to also implement a constructor that will make the following code succeed
            Account account = new Account(1000.0);
            sys_user = new User(firstName, lastName, email, paswdToStoreInDB, type, "true", account);
            account.setOwner(sys_user);

            if (email.equals("admin@gmail.com") && password.equals("admin")) {
                sys_user_group = new SystemUserGroup(email, "admins");
            } else {

                sys_user_group = new SystemUserGroup(email, "users");
            }

            em.persist(sys_user);
            em.persist(sys_user_group);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserStorageServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//  public static final void registerAdmin(String email, String password, String firstName, String lastName, String type) {
//        try {
//            User sys_admin;
//            SystemUserGroup sys_user_group;
//
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            String passwd = password;
//            md.update(passwd.getBytes("UTF-8"));
//            byte[] digest = md.digest();
//            BigInteger bigInt = new BigInteger(1, digest);
//            String paswdToStoreInDB = bigInt.toString(16);
//            
//
//
//            // apart from the default constructor which is required by JPA
//            // you need to also implement a constructor that will make the following code succeed
//            Account account = new Account(1000.0);
//            sys_admin = new User(firstName, lastName, email, paswdToStoreInDB, type, "true", account);
//            account.setOwner(sys_admin);
//
//
//            sys_user_group = new SystemUserGroup(email, "admins");
//
//            em1.persist(sys_admin);
//            em1.persist(sys_user_group);
//
//        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
//            Logger.getLogger(UserStorageServiceBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
