package com.example.jayeshp.sqldemo;

import android.content.Context;


public class Contact {

    int id;
    String name;
    String phone;
    String email;
    String address;


    public Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email=email;
        this.address=address;
    }

    public Contact() {

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
