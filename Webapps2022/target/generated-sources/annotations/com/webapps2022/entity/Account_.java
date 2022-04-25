package com.webapps2022.entity;

import com.webapps2022.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-21T01:59:26")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, User> owner;
    public static volatile SingularAttribute<Account, String> currencyType;
    public static volatile SingularAttribute<Account, Integer> accountId;
    public static volatile SingularAttribute<Account, Double> balance;

}