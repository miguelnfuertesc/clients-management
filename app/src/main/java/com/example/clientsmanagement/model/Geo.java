package com.example.clientsmanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geo implements Serializable {
    @SerializedName("lat") //Gson
    @Expose //Gson
    private String lat;

    @SerializedName("lng") //Gson
    @Expose //Gson
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

}