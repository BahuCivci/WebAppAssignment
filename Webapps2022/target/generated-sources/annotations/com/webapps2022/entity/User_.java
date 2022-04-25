package com.webapps2022.entity;

import com.webapps2022.entity.Account;
import com.webapps2022.entity.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-21T01:59:26")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> isValid;
    public static volatile SingularAttribute<User, String> type;
    public static volatile SetAttribute<User, Transaction> transactions;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Account> account;

}