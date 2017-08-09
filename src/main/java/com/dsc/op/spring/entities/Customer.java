package com.dsc.op.spring.entities;

import java.io.Serializable;

/**
 *
 * @author COETO
 */
public class Customer implements Serializable{
    private Integer id;
    private String name;
    private String address;
    private String number;

    public Customer() {
    }

    public Customer(Integer id, String name, String address, String number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", address=" + address + ", number=" + number + '}';
    }
    
}
