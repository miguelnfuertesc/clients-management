package com.example.clientsmanagement.model;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable {
    @SerializedName("street") //Gson
    @Expose //Gson
    private String street;

    @SerializedName("suite") //Gson
    @Expose //Gson
    private String suite;

    @SerializedName("city") //Gson
    @Expose //Gson
    private String city;

    @SerializedName("zipcode") //Gson
    @Expose //Gson
    private String zipcode;

    @Embedded(prefix = "geo_")  //Room Library
    @SerializedName("geo") //Gson
    @Expose //Gson
    private Geo geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

}
