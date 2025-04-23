package com.example.clientsmanagement.view.fragment;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// R import
import com.example.clientsmanagement.R;
import com.example.clientsmanagement.model.Cliente;
import com.example.clientsmanagement.view.fragment.ListarFragment.OnFragmentListarInteractionListener;

import java.util.List;

public class ListarRecyclerViewAdapter extends RecyclerView.Adapter<ListarRecyclerViewAdapter.ViewHolder> {
    private final List<Cliente> mValues;
    private final OnFragmentListarInteractionListener mListener;
    private SparseBooleanArray selectedItems;
    public CardView lastCard;

    public ListarRecyclerViewAdapter(List<Cliente> clientes, OnFragmentListarInteractionListener listener) {
        mValues = clientes;
        mListener = listener;
        selectedItems = new SparseBooleanArray();
        lastCard = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_listar_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.cliente = mValues.get(position);
        holder.nombreCliente.setText(mValues.get(position).getName());


        StringBuilder idString = new StringBuilder();
        idString.append("000");
        idString.append(String.valueOf(mValues.get(position).getId()));
        holder.codigo.setText(idString);

        holder.username.setText(mValues.get(position).getUsername());
        holder.email.setText(mValues.get(position).getEmail());

        StringBuilder addressString = new StringBuilder();
        addressString.append(mValues.get(position).getAddress().getStreet());
        addressString.append(" ");
        addressString.append(mValues.get(position).getAddress().getSuite());
        addressString.append(" ");
        addressString.append(mValues.get(position).getAddress().getCity());
        holder.nombreDireccion.setText(addressString);

        holder.telefono.setText(mValues.get(position).getPhone());
        holder.paginaWeb.setText(mValues.get(position).getWebsite());
        holder.nombreEmpresa.setText(mValues.get(position).getCompany().getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public Cliente cliente;
        public final CardView cardViewItem;
        public final TextView nombreCliente;
        public final TextView codigo;
        public final TextView username;
        public final TextView email;
        public final TextView nombreDireccion;
        public final TextView telefono;
        public final TextView paginaWeb;
        public final TextView nombreEmpresa;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cardViewItem = (CardView) view.findViewById(R.id.cardViewItem);
            nombreCliente = (TextView) view.findViewById(R.id.tvNombreCliente);
            codigo = (TextView) view.findViewById(R.id.tvCodigo);
            username = (TextView) view.findViewById(R.id.tvUsername);
            email = (TextView) view.findViewById(R.id.tvEmail);
            nombreDireccion = (TextView) view.findViewById(R.id.tvNombreDireccion);
            telefono = (TextView) view.findViewById(R.id.tvTelefono);
            paginaWeb = (TextView) view.findViewById(R.id.tvPaginaWeb);
            nombreEmpresa = (TextView) view.findViewById(R.id.tvNombreEmpresa);
            cardViewItem.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombreCliente.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==cardViewItem.getId()){
                if(null != mListener){

                    if(selectedItems.get(getAdapterPosition())) {
                        selectedItems.put(getAdapterPosition(), false);
                        if(lastCard!=null){
                            lastCard.setSelected(false);
                        }
                        cardViewItem.setSelected(true);
                    } else{
                        selectedItems.put(getAdapterPosition(), true);
                        if(lastCard!=null){
                            lastCard.setSelected(false);
                        }
                        cardViewItem.setSelected(true);
                    }

                    lastCard = cardViewItem;

                    mListener.OnFragmentListarInteractionListener(cliente);
                    Log.d("ADAPTER NUMBER", String.valueOf(cliente.getName()));
                }
            }
        }


    }
}
