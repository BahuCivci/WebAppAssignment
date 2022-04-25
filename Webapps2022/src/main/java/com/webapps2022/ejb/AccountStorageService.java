/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import com.webapps2022.entity.Account;
import com.webapps2022.entity.User;
import com.webapps2022.entity.Transaction;
import javax.ejb.Remote;

@Local
public interface AccountStorageService {
    public void editAccount(Account account);
    
    public Account searchById(Integer id);

    
}
