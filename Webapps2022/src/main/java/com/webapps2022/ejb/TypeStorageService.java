/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.TransactionType;
import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface TypeStorageService {

    public void addTransactionType(TransactionType type);
    
}
