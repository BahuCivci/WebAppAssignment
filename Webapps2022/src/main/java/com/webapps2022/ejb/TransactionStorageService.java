/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.Account;
import com.webapps2022.entity.User;
import java.util.List;
import javax.ejb.Local;
import com.webapps2022.entity.Transaction;
import com.webapps2022.restservice.CurrencyClass;
import java.util.Date;
import java.util.Set;
import javax.ejb.Remote;

@Local
public interface TransactionStorageService {

    public void addBankTransaction(Transaction transaction) ;

    public Transaction searchTransactionById(String id);

    public List<Transaction> getAllTransactions() ;
 
    public void removeTransaction(String transactionId);
    public CurrencyClass getCurrentFx(String currency1, String currency2, String fromTo);
    public void editTransaction(Transaction transaction) ;

    public Set<Transaction> searchTransactionByUser(User user) ;
    
    public List<Transaction> searchByTypeOrName(String input);
}
