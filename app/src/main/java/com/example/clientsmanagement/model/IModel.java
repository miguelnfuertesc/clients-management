package com.example.clientsmanagement.model;

import android.content.Context;

public interface IModel {
    void obtenerData(Context context);
    void listarCliente();
    void agregarCliente(Cliente client);
    void actualizarCliente(Cliente client);
    void eliminarCliente(String id);
}
