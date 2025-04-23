package com.example.clientsmanagement.presenter;

import android.content.Context;

import com.example.clientsmanagement.model.Cliente;

import java.io.Serializable;
import java.util.List;

public interface IPresenter {
    /*----IView----*/
    void mostrarError(String string);
    void mostrarCliente(List<Cliente> clientes);

    /*----IModel----*/
    void obtenerData(Context context);
    void listarCliente();
    void agregarCliente(Cliente client);
    void actualizarCliente(Cliente client);
    void eliminarCliente(String id);
}
