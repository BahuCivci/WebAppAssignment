/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.TransactionStorageService;
import com.webapps2022.entity.Transaction;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("transactionManagedBean")
@ApplicationScoped
public class TransactionManagedBean implements Serializable {
    @EJB
    private TransactionStorageService transactionRepository;
    private List<Transaction> allTransactions;
    private String welcome;
    public TransactionManagedBean() {
        welcome = "welcome to online banking!";
    }
    
//    
    
    public List<Transaction> getAllTransactions() {
        try {
            this.allTransactions = transactionRepository.getAllTransactions();
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE, 
                    "cannot get all transaction records!", ex);
        }
        return this.allTransactions;
    }
    
    public Transaction getTransactionById(Integer id) {
        Transaction resultTransc = null;
        for(Transaction transaction: this.allTransactions) {
            if(transaction.getTransactionId() == id) {
                resultTransc = transaction;
                break;
            }
        }
        return resultTransc;
    }
//    public List<BankTransaction> searchByNameOrTypeOrId(String queryContent) {
//        List<BankTransaction> transactions = new ArrayList<>();
//        if (queryContent.matches("\\d+")) {
//            try {
//                BankTransaction transaction = transactionRepository.searchTransactionById(Integer.parseInt(queryContent));
//                transactions.add(transaction);
//            } catch (Exception ex) {
//                Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE, "Can not convert string to integer.", ex);
//            }
//        }
//        else {
//            try {
//                transactions = transactionRepository.searchByTypeOrName(queryContent);
//            } catch (Exception ex) {
//                Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE, "Can not search by transaction name or type.", ex);
//            }
//        }
//        return transactions;
//    }
    public String navigateToTransactionPage() {
        return "transaction";
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public TransactionStorageService getTransactionRepository() {
        return transactionRepository;
    }

    public void setTransactionRepository(TransactionStorageService transactionRepository) {
        this.transactionRepository = transactionRepository;
    }



    
}                                                                   