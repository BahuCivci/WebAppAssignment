package com.webapps2022.entity;

import com.webapps2022.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-21T01:59:26")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, String> transactionType;
    public static volatile SingularAttribute<Transaction, String> isValid;
    public static volatile SingularAttribute<Transaction, String> description;
    public static volatile SingularAttribute<Transaction, String> transactionName;
    public static volatile SingularAttribute<Transaction, User> user;
    public static volatile SingularAttribute<Transaction, Integer> transactionId;
    public static volatile SingularAttribute<Transaction, String> createDate;

}