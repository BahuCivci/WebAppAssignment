/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.AccountStorageService;
import com.webapps2022.ejb.TransactionStorageService;
import com.webapps2022.ejb.UserStorageService;
import com.webapps2022.entity.Account;
import com.webapps2022.entity.Transaction;
import com.webapps2022.entity.User;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author bahuc
 */
@Named("moneyController")
@SessionScoped
public class MoneyTransferController implements Serializable {

    @EJB
    private TransactionStorageService transactionRepository;
    @EJB
    private UserStorageService userRepository;
    private User user = null;
    @EJB
    private AccountStorageService accountRepository;
    List<String> potentialReceiver;
    String selectedUser;
    private String amount;
    private Integer fromId;
    private Integer toId;
    private String message;

    public MoneyTransferController() {
//        this.userId = Integer.valueOf(FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap()
//                .get("userId"));
//        System.out.println("ssss" + this.transactionId);
    }

    public String sendMoney() {

        User sender = userRepository.searchUserByEmail(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        System.out.println("The selected user is " + this.selectedUser);
        try {
            Account accountFrom = sender.getAccount();
            Account accountTo = userRepository.searchUserByEmail(this.selectedUser).getAccount();
            if (accountFrom.getBalance() - Double.parseDouble(this.amount) < 0) {
                return "amountError";
            } else {

                accountFrom.setBalance(accountFrom.getBalance() - Double.parseDouble(this.amount));
                accountTo.setBalance(accountTo.getBalance() + Double.parseDouble(this.amount));

                accountRepository.editAccount(accountFrom);
                accountRepository.editAccount(accountTo);

                Transaction transaction1 = new Transaction();
                transaction1.setUser(accountFrom.getOwner());
                transaction1.setTransactionType("MoneyOut");
                transaction1.setTransactionName("Money gets out from account " + this.fromId);
                Transaction transaction2 = new Transaction();
                transaction2.setUser(accountTo.getOwner());
                transaction2.setTransactionType("MoneyIn");
                transaction2.setTransactionName("Money comes in account " + this.toId);

                transactionRepository.addBankTransaction(transaction1);
                transactionRepository.addBankTransaction(transaction2);

            }
        } catch (Exception ex) {
            Logger.getLogger(MoneyTransferController.class.getName()).log(Level.SEVERE, "Error occurs while transfering money.", ex);
        }

        return "finish.xhtml";

    }

    public String transfer() {
        try {
            Account accountFrom = accountRepository.searchById(this.fromId);
            Account accountTo = accountRepository.searchById(this.toId);
            accountFrom.setBalance(accountFrom.getBalance() - Double.parseDouble(this.amount));
            accountTo.setBalance(accountTo.getBalance() + Double.parseDouble(this.amount));

            accountRepository.editAccount(accountFrom);
            accountRepository.editAccount(accountTo);

            Transaction transaction1 = new Transaction();
            transaction1.setUser(accountFrom.getOwner());
            transaction1.setTransactionType("MoneyOut");
            transaction1.setTransactionName("Money gets out from account " + this.fromId);
            Transaction transaction2 = new Transaction();
            transaction2.setUser(accountTo.getOwner());
            transaction2.setTransactionType("MoneyIn");
            transaction2.setTransactionName("Money comes in account " + this.toId);

            transactionRepository.addBankTransaction(transaction1);
            transactionRepository.addBankTransaction(transaction2);

        } catch (Exception ex) {
            Logger.getLogger(MoneyTransferController.class.getName()).log(Level.SEVERE, "Error occurs while transfering money.", ex);
        }

        return "finish.xhtml";
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransactionStorageService getTransactionRepository() {
        return transactionRepository;
    }

    public void setTransactionRepository(TransactionStorageService transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public UserStorageService getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserStorageService userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountStorageService getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountStorageService accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<String> getPotentialReceiver() {
        potentialReceiver = userRepository.getAllEmail();
        return potentialReceiver;
    }

    public void setPotentialReceiver(List<String> potentialReceiver) {
        this.potentialReceiver = potentialReceiver;
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }

}
