/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.TransactionType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TypeStoreServiceBean implements TypeStorageService{

    @PersistenceContext(unitName = "WebappsDBPU")
    EntityManager entityManager;

    @Override
    public void addTransactionType(TransactionType type) {
        entityManager.persist(type);
    }
    
    
}
