package com.webapps2022.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-21T01:59:26")
@StaticMetamodel(RequestMoney.class)
public class RequestMoney_ { 

    public static volatile SingularAttribute<RequestMoney, Double> amount;
    public static volatile SingularAttribute<RequestMoney, Integer> requestId;
    public static volatile SingularAttribute<RequestMoney, String> supplier;
    public static volatile SingularAttribute<RequestMoney, String> demander;

}