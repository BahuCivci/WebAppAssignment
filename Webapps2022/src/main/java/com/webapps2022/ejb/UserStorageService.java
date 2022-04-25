/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.Account;
import com.webapps2022.entity.RequestMoney;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import com.webapps2022.entity.User;
import com.webapps2022.entity.Transaction;
import javax.ejb.Remote;

@Local
public interface UserStorageService {

    public List<User> getAllUsers();

    public List<String> getAllRequests();

public List<RequestMoney> getRequestDemander (String email);
public List<RequestMoney> getRequestSupplier (String email);

    public List<String> getAllEmail();

    public void demandMoney(RequestMoney requestMoney);

    public void addBankUser(User user);

    public User searchUserById(Integer id);

    public User searchUserByEmail(String email);

    public void removeBankUser(Integer userId);

    public void editBankUser(User user);
public void editDemandTransaction(User bankUser);
public void editSupplyTransaction(User bankUser);

}
