/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.entity.Transaction;
import java.io.Serializable;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author bahuc
 */
@Named("transactionController")
@SessionScoped
public class TransactionController implements Serializable{
    
    private Integer transactionId;
    private Transaction transaction;
    
    
    public TransactionController() {
        this.transactionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("transactionId"));
        System.out.println("ssss" + this.transactionId);
    }
    
    
    public Transaction getTransaction() {
        if (transaction == null) {
            System.out.println(this.transactionId);
            // Get application context bean MovieApplication 
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            TransactionManagedBean transactionManagedBean = (TransactionManagedBean) FacesContext.getCurrentInstance()
                    .getApplication()
                    .getELResolver()
                    .getValue(context, null, "transactionManagedBean");
            // -1 to movieId since we +1 in JSF (to always have positive movie id!) 
            return transactionManagedBean.getTransactionById(transactionId);
        }
        return transaction;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }



}