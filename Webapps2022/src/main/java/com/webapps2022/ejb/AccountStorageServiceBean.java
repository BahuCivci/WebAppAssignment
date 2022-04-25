/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.webapps2022.entity.Account;
import com.webapps2022.entity.User;
import com.webapps2022.entity.Transaction;
import javax.ejb.Stateless;
import javax.persistence.Query;


@Stateless
public class AccountStorageServiceBean implements AccountStorageService{

    @PersistenceContext(unitName = "WebappsDBPU")
    EntityManager entityManager;
    
    @Override
    public void editAccount(Account account) {
        entityManager.merge(account);
    }

    @Override
    public Account searchById(Integer id) {
        Account account =  entityManager.find(Account.class, id);
        return account;
    }
}
