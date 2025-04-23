package com.example.clientsmanagement.view.fragment;

import android.app.Activity;
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
import com.example.clientsmanagement.presenter.IPresenter;
import com.example.clientsmanagement.presenter.Presenter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgregarFragment.OnFragmentAgregarInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgregarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentAgregarInteractionListener mListener;

    private EditText nombreCliente, codigo, username, email, nombreDireccion, telefono, paginaWeb, nombreEmpresa;
    private RelativeLayout aceptar;

    private IPresenter iPresenter;

    public AgregarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarFragment newInstance(String param1, String param2) {
        AgregarFragment fragment = new AgregarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM1, param2);
        args.putSerializable(ARG_PARAM1, ARG_PARAM2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar, container, false);
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

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentAgregarInteractionListener) {
            mListener = (OnFragmentAgregarInteractionListener) context;
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

                String addId = String.valueOf(codigo.getText().toString());
                String addNameCliente = String.valueOf(nombreCliente.getText().toString());
                String addUsername = String.valueOf(username.getText().toString());
                String addEmail = String.valueOf(email.getText().toString());
                String addAddress = String.valueOf(nombreDireccion.getText().toString());
                String addPhone = String.valueOf(telefono.getText().toString());
                String addPaginaWeb = String.valueOf(paginaWeb.getText().toString());
                String addCompany = String.valueOf(nombreEmpresa.getText().toString());

                if (addId.isEmpty() || addNameCliente.isEmpty() || addUsername.isEmpty() ||
                    addEmail.isEmpty() || addAddress.isEmpty() || addPhone.isEmpty() ||
                    addPaginaWeb.isEmpty() || addCompany.isEmpty())
                {
                    Toast.makeText(getActivity(), "No puede dejar los campos vacíos.", Toast.LENGTH_LONG).show();
                    return;
                }

                Cliente client = new Cliente();
                client.setId(Integer.parseInt(addId));
                client.setName(addNameCliente);
                client.setUsername(addUsername);
                client.setEmail(addEmail);
                Address address = new Address();
                address.setStreet(addAddress);
                address.setSuite("");
                address.setCity("");
                client.setAddress(address);
                client.setPhone(addPhone);
                client.setWebsite(addPaginaWeb);
                Company company = new Company();
                company.setName(addCompany);
                client.setCompany(company);

                mListener.onFragmentAgregarInteraction(client);
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
    public interface OnFragmentAgregarInteractionListener {
        void onFragmentAgregarInteraction(Cliente client);
    }
}
