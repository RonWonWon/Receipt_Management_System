package com.receiptmanagement.ReceiptManagement.LOGIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class LOGIN {
    
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Password")
    private String password;

    public LOGIN() {
    }

    public LOGIN(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
