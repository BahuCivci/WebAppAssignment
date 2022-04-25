package com.webapps2022.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@NamedQueries({
    @NamedQuery(name = "findAllUsers", query = "select u from sysUser u"),
    @NamedQuery(name = "findAllEmails", query = "select u.email from sysUser u"),
    @NamedQuery(
            name = "findUser",
            query = "select u from sysUser u WHERE u.email = :email"
    ),

    @NamedQuery(
            name = "findUserFromEmail",
            query = "select u from sysUser u WHERE u.email = :email"
    )
})
@Entity(name = "sysUser")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    Integer userId;
    String firstName;
    String lastName;

    String email;
    String password;
    String type;

    String isValid;
    @OneToMany(mappedBy = "user")
    @JoinColumn(nullable = true)
    private Set<Transaction> transactions;
    @OneToOne(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private Account account;


    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String type, String isValid, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.isValid = isValid;
        this.account = account;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static User copyWithoutId(User oldUser) {
        User newUser = new User();
//        newUser.setAccount(oldUser.getAccount());

        newUser.setEmail(oldUser.getEmail());
        newUser.setFirstName(oldUser.getFirstName());
        newUser.setIsValid(oldUser.getIsValid());
        newUser.setLastName(oldUser.getLastName());
        newUser.setPassword(oldUser.getPassword());

        newUser.setType(oldUser.getType());

        return newUser;
    }



}
