package com.example.clientsmanagement.view;

import com.example.clientsmanagement.model.Cliente;

import java.util.List;

public interface IView {
    void mostrarError(String string);
    void mostrarCliente(List<Cliente> clientes);
}
