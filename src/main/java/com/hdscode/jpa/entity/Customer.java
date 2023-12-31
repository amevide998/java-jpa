package com.hdscode.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_t_customers")
public class Customer {

    @Id
    private String id;

    private String name;

    @Column(name = "primary_email")
    private String primaryEmail;

    private Byte age;

    private Boolean married;

    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @Transient
    private String fullname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public CustomerType getCustomerType() {
        return type;
    }

    public void setCustomerType(CustomerType customerType) {
        this.type = customerType;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
