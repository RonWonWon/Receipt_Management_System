package com.receiptmanagement.ReceiptManagement.RECEIPT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "receipt")
public class RECEIPT {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="IdOrGenerated")
    @Column(name = "Id") 
    private Long id;

    @Column(name = "Name") 
    private String name;

    @Column(name = "Mobile") 
    private String mobile;

    @Column(name = "Amount") 
    private Float amount;

    @Column(name = "Pan_no") 
    private String pan_no;

    @Column(name = "Email") 
    private String email;

    @Column(name = "Description") 
    private String description;

    @Column(name = "Created_at")    
    private String created_at;

    
    public RECEIPT() {
    }


    public RECEIPT(Long id, String name, String mobile, Float amount, String pan_no, String email, String description,
            String created_at) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.amount = amount;
        this.pan_no = pan_no;
        this.email = email;
        this.description = description;
        this.created_at = created_at;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public Float getAmount() {
        return amount;
    }


    public void setAmount(Float amount) {
        this.amount = amount;
    }


    public String getPan_no() {
        return pan_no;
    }


    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getCreated_at() {
        return created_at;
    }


    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    
}
