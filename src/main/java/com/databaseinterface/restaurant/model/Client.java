package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private double discount;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false, unique = true)
    private String telephone;
    public Client(){}

    public Client(int id, double discount, String name, String mail, String telephone) {
        this.id = id;
        this.discount = discount;
        this.name = name;
        this.mail = mail;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
