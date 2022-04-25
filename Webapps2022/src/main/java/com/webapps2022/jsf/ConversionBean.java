/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.AccountStorageService;
import com.webapps2022.ejb.TransactionStorageService;
import com.webapps2022.ejb.TypeStorageService;
import com.webapps2022.ejb.UserStorageService;
import com.webapps2022.entity.Account;
import com.webapps2022.entity.User;
import com.webapps2022.restservice.CurrencyClass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Named("conversion")
@SessionScoped
public class ConversionBean implements Serializable {
    
    @EJB
    TransactionStorageService transactionStorageService;
    @EJB
    AccountStorageService accountStorageService;
    String currency1;
    String currency2;
    String fromTo;
    double amount;
    User user;
    Account account;
    CurrencyClass currency;
    
    @EJB
    UserStorageService userRepository;
    
    private List<String> currencies;
    
    public ConversionBean() {
        
        currencies = new ArrayList<String>();
        currencies.add("GBP");
        currencies.add("EUR");
        currencies.add("USD");
        
    }
    
    public AccountStorageService getAccountStorageService() {
        return accountStorageService;
    }
    
    public void setAccountStorageService(AccountStorageService accountStorageService) {
        this.accountStorageService = accountStorageService;
    }
    
    public String getFromTo() {
        return fromTo;
    }
    
    public void setFromTo(String fromTo) {
        this.fromTo = fromTo;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public CurrencyClass getCurrency() {
        return currency;
    }
    
    public void setCurrency(CurrencyClass currency) {
        this.currency = currency;
    }
    
    public UserStorageService getUserRepository() {
        return userRepository;
    }
    
    public void setUserRepository(UserStorageService userRepository) {
        this.userRepository = userRepository;
    }
    
    public TransactionStorageService getTransactionStorageService() {
        return transactionStorageService;
    }
    
    public void setTransactionStorageService(TransactionStorageService transactionStorageService) {
        this.transactionStorageService = transactionStorageService;
    }
    
    public List<String> getCurrencies() {
        return currencies;
    }
    
    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }
    
    public String getCurrency1() {
        return currency1;
    }
    
    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }
    
    public String getCurrency2() {
        return currency2;
    }
    
    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String convert() {
        this.user = userRepository.searchUserByEmail(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        this.currency1 = this.user.getAccount().getCurrencyType();
        this.account = this.user.getAccount();
        
        this.amount = this.account.getBalance();
        fromTo = this.currency1 + "to" + this.currency2;
        
        currency = transactionStorageService.getCurrentFx(this.currency1, this.currency2, this.fromTo);
        
        double newAmount = currency.getExchangeRate() * this.amount;
        this.account.setBalance(newAmount);
        this.account.setCurrencyType(this.currency2);
        accountStorageService.editAccount(this.account);
        
        return "userDetails";
    }
    
}
