package com.example.clientsmanagement.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.clientsmanagement.R;
import com.example.clientsmanagement.model.Cliente;
import com.example.clientsmanagement.presenter.IPresenter;
import com.example.clientsmanagement.presenter.Presenter;
import com.example.clientsmanagement.view.fragment.ActualizarFragment;
import com.example.clientsmanagement.view.fragment.AgregarFragment;
import com.example.clientsmanagement.view.fragment.EliminarFragment;
import com.example.clientsmanagement.view.fragment.ListarFragment;

import java.util.ArrayList;
import java.util.List;

public class GestionDeClientesActivity extends FragmentActivity implements View.OnClickListener, IView,
        ListarFragment.OnFragmentListarInteractionListener, AgregarFragment.OnFragmentAgregarInteractionListener,
        ActualizarFragment.OnFragmentActualizarInteractionListener, EliminarFragment.OnFragmentEliminarInteractionListener {
    public static List<Cliente> clientes = new ArrayList<Cliente>();
    public Cliente currentCliente = null;
    private RelativeLayout btnHome, btnAgregar, btnActualizar, btnEliminar;
    private IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_clientes);
        btnHome = (RelativeLayout) findViewById(R.id.btnHome);
        btnAgregar = (RelativeLayout) findViewById(R.id.btnAgregar);
        btnActualizar = (RelativeLayout) findViewById(R.id.btnActualizar);
        btnEliminar = (RelativeLayout) findViewById(R.id.btnEliminar);
        btnHome.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

        iPresenter = new Presenter(this);   // initial state of MVP Flow
        iPresenter.obtenerData(this);
        iPresenter.listarCliente();
        buildFragment(ListarFragment.newInstance(ListarFragment.ONE_COLUMN_FRAGMENT));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*----MVP Input----*/
    @Override
    public void OnFragmentListarInteractionListener(Cliente cliente) {
        currentCliente = cliente;
    }

    @Override
    public void onFragmentAgregarInteraction(Cliente client) {
        iPresenter.agregarCliente(client);
        iPresenter.listarCliente();
        buildFragment(ListarFragment.newInstance(ListarFragment.ONE_COLUMN_FRAGMENT));
    }

    @Override
    public void onFragmentActualizarInteraction(Cliente client) {
        iPresenter.actualizarCliente(client);
        iPresenter.listarCliente();
        buildFragment(ListarFragment.newInstance(ListarFragment.ONE_COLUMN_FRAGMENT));
    }

    @Override
    public void onFragmentEliminarInteraction(String id) {
        iPresenter.eliminarCliente(id);
        iPresenter.listarCliente();
        buildFragment(ListarFragment.newInstance(ListarFragment.ONE_COLUMN_FRAGMENT));
    }


    /*----MVP Output----*/
    @Override
    public void mostrarError(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarCliente(List<Cliente> clientes) {
        Log.d("DATA_CLIENTES", String.valueOf(clientes.size()));
        this.clientes = clientes;
    }

    /*----Activity Events----*/
    @Override
    public void onClick(View v) {
        if(v.getId()==btnHome.getId()){
            onClickAnimation(btnHome);
            iPresenter.listarCliente();
            buildFragment(ListarFragment.newInstance(ListarFragment.ONE_COLUMN_FRAGMENT));
            currentCliente = null;
        }
        else if(v.getId()==btnAgregar.getId()) {
            onClickAnimation(btnAgregar);
            buildFragment(AgregarFragment.newInstance("Demo", "1"));
            currentCliente = null;
        }
        else if(v.getId()==btnActualizar.getId()) {
            if(currentCliente==null){
                mostrarError("Seleccione un cliente");
                return;
            }
            onClickAnimation(btnActualizar);
            buildFragment(ActualizarFragment.newInstance(currentCliente));
            currentCliente = null;
        }
        else if(v.getId()==btnEliminar.getId()) {
            if(currentCliente==null){
                mostrarError("Seleccione un cliente");
                return;
            }
            onClickAnimation(btnEliminar);
            buildFragment(EliminarFragment.newInstance(currentCliente));
            currentCliente = null;
        }
    }

    void buildFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainFrameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    void onClickAnimation(RelativeLayout relativeLayout) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(40);
        animation.setRepeatCount(0);
        relativeLayout.startAnimation(animation);
    }
}
