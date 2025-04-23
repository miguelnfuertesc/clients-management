package com.example.clientsmanagement.model.rest;

import com.example.clientsmanagement.model.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/users")
    Call<List<Cliente>> getUsers();
}
