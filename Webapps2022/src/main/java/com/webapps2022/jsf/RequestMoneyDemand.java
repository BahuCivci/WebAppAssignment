/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.UserStorageService;
import com.webapps2022.entity.RequestMoney;
import com.webapps2022.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("requestMoneyDemand")
@SessionScoped
public class RequestMoneyDemand implements Serializable {

    @EJB
    UserStorageService userRepository;
    String supplier;
    String demander;
    List<String> potentialSupplier;
    String request;
    List<String> allRequests;
    double amount;


List<RequestMoney> demands;
List<RequestMoney> supplies;

    public RequestMoneyDemand() {


    }




    public String requestMoneyDemand() {

        demander = (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        //    System.out.println("The selected user is " + this.selectedUser);

        RequestMoney requestMoney = new RequestMoney();

        requestMoney.setDemander(demander);
        requestMoney.setSupplier(this.supplier);
        requestMoney.setAmount(amount);

        userRepository.demandMoney(requestMoney);

        return "transactionDemand.xhtml";

    }




    public List<RequestMoney> getDemands() {
demands =userRepository.getRequestDemander(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return demands;
    }

    public void setDemands(List<RequestMoney> demands) {
        this.demands = demands;
    }


    public List<RequestMoney> getSupplies() {
supplies =userRepository.getRequestSupplier(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return supplies;
    }

    public void setSupplies(List<RequestMoney> supplies) {
        this.supplies = supplies;
    }






    public UserStorageService getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserStorageService userRepository) {
        this.userRepository = userRepository;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<String> getPotentialSupplier() {
        potentialSupplier = userRepository.getAllEmail();
        return potentialSupplier;
    }

    public void setPotentialSupplier(List<String> potentialSupplier) {
        this.potentialSupplier = potentialSupplier;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRequest() {

        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public List<String> getAllRequests() {
        this.allRequests = userRepository.getAllRequests();
        return allRequests;
    }

    public void setAllRequests(List<String> allRequests) {
        this.allRequests = allRequests;
    }

}
