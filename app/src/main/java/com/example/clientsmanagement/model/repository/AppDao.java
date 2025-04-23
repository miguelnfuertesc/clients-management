package com.example.clientsmanagement.model.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.clientsmanagement.model.Cliente;

import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM tbCliente")
    public List<Cliente> getClientes();

    @Insert
    public void addCliente(Cliente cliente);

    @Update
    public void updateCliente(Cliente cliente);

    @Delete
    public void deleteCliente(Cliente cliente);

    @Query("DELETE FROM tbCliente")
    public void dropTbCliente();

    @Query("SELECT * FROM tbCliente WHERE id = :id")
    public Cliente getClienteById(Integer id);
}
