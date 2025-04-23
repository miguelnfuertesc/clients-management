package com.example.clientsmanagement.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "tbCliente")
public class Cliente implements Serializable {
    @PrimaryKey //Room Library
    @SerializedName("id") //Gson
    @Expose //Gson
    private Integer id;

    @SerializedName("name") //Gson
    @Expose //Gson
    private String name;

    @SerializedName("username") //Gson
    @Expose //Gson
    private String username;

    @SerializedName("email") //Gson
    @Expose //Gson
    private String email;

    @Embedded(prefix = "address_")  //Room Library
    @SerializedName("address") //Gson
    @Expose //Gson
    private Address address;

    @SerializedName("phone") //Gson
    @Expose //Gson
    private String phone;

    @SerializedName("website") //Gson
    @Expose //Gson
    private String website;

    @Embedded(prefix = "company_")  //Room Library
    @SerializedName("company") //Gson
    @Expose //Gson
    private Company company;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
