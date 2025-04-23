package com.example.clientsmanagement.presenter;

import android.content.Context;

import com.example.clientsmanagement.model.Cliente;
import com.example.clientsmanagement.model.IModel;
import com.example.clientsmanagement.model.Model;
import com.example.clientsmanagement.view.IView;

import java.io.Serializable;
import java.util.List;

public class Presenter implements IPresenter {
    private IView iView;
    private IModel iModel;

    public Presenter(IView iView){
        this.iView = iView;
        this.iModel = new Model(this, (Context) iView);
    }

    @Override
    public void mostrarError(String string) {
        if (iView != null) {
            iView.mostrarError(string);
        }
    }

    @Override
    public void mostrarCliente(List<Cliente> clientes) {
        if (iView != null) {
            iView.mostrarCliente(clientes);
        }
    }

    @Override
    public void obtenerData(Context context) {
        if (iView != null) {
            iModel.obtenerData(context);
        }
    }

    @Override
    public void listarCliente() {
        if (iView != null) {
            iModel.listarCliente();
        }
    }

    @Override
    public void agregarCliente(Cliente client) {
        if (iView != null) {
            iModel.agregarCliente(client);
        }
    }

    @Override
    public void actualizarCliente(Cliente client) {
        if (iView != null) {
            iModel.actualizarCliente(client);
        }
    }

    @Override
    public void eliminarCliente(String id) {
        if (iView!=null) {
            iModel.eliminarCliente(id);
        }
    }
}
