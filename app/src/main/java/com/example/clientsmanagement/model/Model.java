package com.example.clientsmanagement.model;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.clientsmanagement.model.repository.AppDatabase;
import com.example.clientsmanagement.model.rest.ApiClient;
import com.example.clientsmanagement.model.rest.ApiService;
import com.example.clientsmanagement.presenter.IPresenter;
import com.example.clientsmanagement.view.IView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements IModel {
    private IPresenter iPresenter;
    private static AppDatabase appDatabase;

    public Model(IPresenter iPresenter, Context context) {
        this.iPresenter = iPresenter;
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase").allowMainThreadQueries().build();
    }

    @Override
    public void obtenerData(Context context) {
        if(iPresenter!=null){
            initializeSSLContext(context);
            ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Cliente>> call = apiService.getUsers();
            call.enqueue(new Callback<List<Cliente>>() {
                @Override
                public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                    Log.d("API_RESPONSE", String.valueOf(response.isSuccessful()));
                    Log.d("OBJECTS", String.valueOf(response.body().size()));

                    //appDatabase.appDao().dropTbCliente();
                    List<Cliente> clientesResponse = response.body();
                    for(int i=0; i<clientesResponse.size(); i++){
                        Cliente cliente = appDatabase.appDao().getClienteById(clientesResponse.get(i).getId());
                        if(cliente==null){
                            appDatabase.appDao().addCliente(clientesResponse.get(i));
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Cliente>> call, Throwable t) {
                    Log.d("API_ERROR", String.valueOf(t.toString()));
                    iPresenter.mostrarError("Se ha caído el endpoint");
                }
            });
        }
    }

    @Override
    public void listarCliente() {
        if(iPresenter!=null){
            List<Cliente> clientes = appDatabase.appDao().getClientes();
            iPresenter.mostrarCliente(clientes);
        }
    }

    @Override
    public void agregarCliente(Cliente client) {
        if (iPresenter != null) {
            appDatabase.appDao().addCliente(client);
        }
    }

    @Override
    public void actualizarCliente(Cliente client) {
        if (iPresenter != null) {
            appDatabase.appDao().updateCliente(client);
        }
    }

    @Override
    public void eliminarCliente(String id) {
        if(iPresenter!=null){
            Cliente cliente = new Cliente();
            cliente.setId(Integer.parseInt(id));

            appDatabase.appDao().deleteCliente(cliente);
        }
    }

    public static void initializeSSLContext(Context context){
        try {
            SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            ProviderInstaller.installIfNeeded(context.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

}
