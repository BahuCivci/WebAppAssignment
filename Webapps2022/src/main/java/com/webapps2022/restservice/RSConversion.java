/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.restservice;

import java.util.Calendar;
import java.util.HashMap;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/conversion")
public class RSConversion {

    private final HashMap<String, CurrencyClass> currencies;

    // for testing purposes, a sample employee is added in the hashmap where all Employee objects are stored
    // when the Singleton object is instantiated
    // after it is instantiated, the @PostConstruct method will be called
    public RSConversion() {

        currencies = new HashMap<>();
        CurrencyClass c1 = new CurrencyClass();
        c1.setFromTo("GBPtoUSD");
        c1.setExchangeRate(1.30);
        c1.setCurrency1("GBP");
        c1.setCurrency2("USD");
        currencies.put(c1.getFromTo(), c1);
CurrencyClass c2 = new CurrencyClass();
        c2.setFromTo("GBPtoEUR");
        c2.setExchangeRate(1.20);
        c2.setCurrency1("GBP");
        c2.setCurrency2("EUR");
        currencies.put(c2.getFromTo(), c2);

CurrencyClass c3 = new CurrencyClass();
        c3.setFromTo("USDtoGBP");
        c3.setExchangeRate(0.76);
        c3.setCurrency1("USD");
        c3.setCurrency2("GBP");
        currencies.put(c3.getFromTo(), c3);

CurrencyClass c4 = new CurrencyClass();
        c4.setFromTo("USDtoEUR");
        c4.setExchangeRate(0.92);
        c4.setCurrency1("USD");
        c4.setCurrency2("EUR");
        currencies.put(c4.getFromTo(), c4);
CurrencyClass c5 = new CurrencyClass();
        c5.setFromTo("EURtoGBP");
        c5.setExchangeRate(0.82);
        c5.setCurrency1("EUR");
        c5.setCurrency2("GBP");
        currencies.put(c5.getFromTo(), c5);

CurrencyClass c6 = new CurrencyClass();
        c6.setFromTo("EURtoUSD");
        c6.setExchangeRate(1.07);
        c6.setCurrency1("EUR");
        c6.setCurrency2("USD");
        currencies.put(c6.getFromTo(), c6);

    }

    @GET
    @Path("/{currency1}/{currency2}/{fromTo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConversion(@PathParam("currency1") String currency1, @PathParam("currency2") String currency2, @PathParam("fromTo") String fromTo ) {
        CurrencyClass currency = currencies.get(fromTo);
        if (currency == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(currency).build();
        }
    }
}
