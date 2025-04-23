package com.example.clientsmanagement.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientsmanagement.R;
import com.example.clientsmanagement.model.Address;
import com.example.clientsmanagement.model.Cliente;
import com.example.clientsmanagement.model.Company;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActualizarFragment.OnFragmentActualizarInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActualizarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActualizarFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private Cliente mParam1;
    private OnFragmentActualizarInteractionListener mListener;
    private EditText nombreCliente, codigo, username, email, nombreDireccion, telefono, paginaWeb, nombreEmpresa;
    private RelativeLayout aceptar;

    public ActualizarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cliente Parameter 1.
     * @return A new instance of fragment ActualizarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActualizarFragment newInstance(Cliente cliente) {
        ActualizarFragment fragment = new ActualizarFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, cliente);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Cliente) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualizar, container, false);
        nombreCliente = (EditText) view.findViewById(R.id.edtNombreCliente);
        codigo = (EditText) view.findViewById(R.id.edtCodigo);
        username = (EditText) view.findViewById(R.id.edtUsername);
        email = (EditText) view.findViewById(R.id.edtEmail);
        nombreDireccion = (EditText) view.findViewById(R.id.edtNombreDireccion);
        telefono = (EditText) view.findViewById(R.id.edtTelefono);
        paginaWeb = (EditText) view.findViewById(R.id.edtPaginaWeb);
        nombreEmpresa = (EditText) view.findViewById(R.id.edtNombreEmpresa);
        aceptar = (RelativeLayout) view.findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(this);

        nombreCliente.setText(mParam1.getName());

        StringBuilder idString = new StringBuilder();
        idString.append("000");
        idString.append(String.valueOf(mParam1.getId()));
        codigo.setText(idString);

        username.setText(mParam1.getUsername());
        email.setText(mParam1.getEmail());

        StringBuilder addressString = new StringBuilder();
        addressString.append(mParam1.getAddress().getStreet());
        addressString.append("");
        addressString.append(mParam1.getAddress().getSuite());
        addressString.append("");
        addressString.append(mParam1.getAddress().getCity());
        nombreDireccion.setText(addressString);

        telefono.setText(mParam1.getPhone());
        paginaWeb.setText(mParam1.getWebsite());
        nombreEmpresa.setText(mParam1.getCompany().getName());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentActualizarInteractionListener) {
            mListener = (OnFragmentActualizarInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==aceptar.getId()){
            if(mListener != null){
                onClickAnimation(aceptar);

                String updateId = String.valueOf(codigo.getText().toString());
                String updateNameCliente = String.valueOf(nombreCliente.getText().toString());
                String updateUsername = String.valueOf(username.getText().toString());
                String updateEmail = String.valueOf(email.getText().toString());
                String updateNameAddress = String.valueOf(nombreDireccion.getText().toString());
                String updatePhone = String.valueOf(telefono.getText().toString());
                String updatePaginaWeb = String.valueOf(paginaWeb.getText().toString());
                String updateNameCompany = String.valueOf(nombreEmpresa.getText().toString());

                if (updateId.isEmpty() || updateNameCliente.isEmpty() || updateUsername.isEmpty() ||
                    updateEmail.isEmpty() || updateNameAddress.isEmpty() || updatePhone.isEmpty() ||
                    updatePaginaWeb.isEmpty() || updateNameCompany.isEmpty())
                {
                    Toast.makeText(getActivity(), "No puede dejar los campos vacíos.", Toast.LENGTH_LONG).show();
                    return;
                }

                Cliente client = new Cliente();
                client.setId(Integer.parseInt(updateId));
                client.setName(updateNameCliente);
                client.setUsername(updateUsername);
                client.setEmail(updateEmail);
                Address address = new Address();
                address.setStreet(updateNameAddress);
                client.setAddress(address);
                client.setPhone(updatePhone);
                client.setWebsite(updatePaginaWeb);
                Company company = new Company();
                company.setName(updateNameCompany);
                client.setCompany(company);

                mListener.onFragmentActualizarInteraction(client);
            }
        }
    }

    void onClickAnimation(RelativeLayout relativeLayout) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(40);
        animation.setRepeatCount(0);
        relativeLayout.startAnimation(animation);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentActualizarInteractionListener {
        void onFragmentActualizarInteraction(Cliente client);
    }
}
