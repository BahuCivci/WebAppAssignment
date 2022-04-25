/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.Account;
import com.webapps2022.entity.User;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.webapps2022.entity.Transaction;
import com.webapps2022.restservice.CurrencyClass;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Stateless
public class TransactionStorageServiceBean implements TransactionStorageService {

    @PersistenceContext(unitName = "WebappsDBPU")
    EntityManager entityManager;

    @Override
    public CurrencyClass getCurrentFx(String currency1, String currency2, String fromTo) {
        Client client = ClientBuilder.newClient();
        WebTarget conversionResource = client.target("http://localhost:10000/Webapps2022/conversion)")
                .path("{currency1}")
                .resolveTemplate("currency1", currency1)
                .path("{currency2}")
                .resolveTemplate("currency2", currency2)
                .path("{fromTo}")
                .resolveTemplate("fromTo", fromTo);
        Invocation.Builder builder = conversionResource.request(MediaType.APPLICATION_JSON);
        CurrencyClass response = builder.get(CurrencyClass.class);
        client.close();
        return response;
    }

    @Override
    public void addBankTransaction(Transaction transaction) {
        entityManager.persist(transaction);
    }

    @Override
    public Transaction searchTransactionById(String id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
//        property.getTags().size();//this line is used to get tag entity from TAG table for a LAZY fetch mode
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery cQuery = builder.createQuery(BankTransaction.class);
//        Root<BankTransaction> p = cQuery.from(BankTransaction.class);
//        cQuery.select(p);
//        return entityManager.createQuery(cQuery).getResultList();
        Query query = entityManager.createNamedQuery("getAllTransactions");
        return query.getResultList();
    }

    @Override
    public void removeTransaction(String transactionId) {
        Transaction transaction = this.searchTransactionById(transactionId);
        entityManager.remove(transaction);
    }

    @Override
    public void editTransaction(Transaction transaction) {
        entityManager.merge(transaction);
    }

    @Override
    public Set<Transaction> searchTransactionByUser(User user) {
        User userFull = entityManager.find(User.class, user.getUserId());
        userFull.getTransactions().size();
        return userFull.getTransactions();
    }

    @Override
    public List<Transaction> searchByTypeOrName(String input) {
        Query query = entityManager.createNamedQuery("Transaction.getByIdOrTypeOrName");
        query.setParameter("name", input);
        query.setParameter("type", input);
        return query.getResultList();
    }
}
