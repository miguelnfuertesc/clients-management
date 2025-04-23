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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clientsmanagement.R;
import com.example.clientsmanagement.model.Cliente;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EliminarFragment.OnFragmentEliminarInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EliminarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EliminarFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private Cliente mParam1;
    private OnFragmentEliminarInteractionListener mListener;
    private TextView nombreCliente, codigo, username, email, nombreDireccion, telefono, paginaWeb, nombreEmpresa;
    private RelativeLayout aceptar;

    public EliminarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cliente Parameter 1.
     * @return A new instance of fragment EliminarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EliminarFragment newInstance(Cliente cliente) {
        EliminarFragment fragment = new EliminarFragment();
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
        View view = inflater.inflate(R.layout.fragment_eliminar, container, false);
        nombreCliente = (TextView) view.findViewById(R.id.tvNombreCliente);
        codigo = (TextView) view.findViewById(R.id.tvCodigo);
        username = (TextView) view.findViewById(R.id.tvUsername);
        email = (TextView) view.findViewById(R.id.tvEmail);
        nombreDireccion = (TextView) view.findViewById(R.id.tvNombreDireccion);
        telefono = (TextView) view.findViewById(R.id.tvTelefono);
        paginaWeb = (TextView) view.findViewById(R.id.tvPaginaWeb);
        nombreEmpresa = (TextView) view.findViewById(R.id.tvNombreEmpresa);
        aceptar = (RelativeLayout) view.findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(this);

        nombreCliente.setText(mParam1.getName());

        StringBuilder idBuilder = new StringBuilder();
        idBuilder.append("000");
        idBuilder.append(String.valueOf(mParam1.getId()));
        codigo.setText(idBuilder);

        username.setText(mParam1.getUsername());
        email.setText(mParam1.getEmail());

        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(mParam1.getAddress().getStreet());
        addressBuilder.append(" ");
        addressBuilder.append(mParam1.getAddress().getSuite());
        addressBuilder.append(" ");
        addressBuilder.append(mParam1.getAddress().getCity());
        nombreDireccion.setText(addressBuilder);

        telefono.setText(mParam1.getPhone());
        paginaWeb.setText(mParam1.getWebsite());
        nombreEmpresa.setText(mParam1.getCompany().getName());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentEliminarInteractionListener) {
            mListener = (OnFragmentEliminarInteractionListener) context;
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

                String deleteId = String.valueOf(codigo.getText().toString());
                mListener.onFragmentEliminarInteraction(deleteId);
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
    public interface OnFragmentEliminarInteractionListener {
        void onFragmentEliminarInteraction(String id);
    }
}
