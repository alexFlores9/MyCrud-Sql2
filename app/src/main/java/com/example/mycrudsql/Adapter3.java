package com.example.mycrudsql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter3 extends  RecyclerView.Adapter<Adapter3.ProViewHolder> {


    private Context mCtx;
    private List<dto_productos> proList;

    public Adapter3 (ListProd mCtx, List<dto_productos> proList){
        this.mCtx = mCtx;
        this.proList = proList;
    }


    @Override
    public ProViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout3,null);
        return new ProViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProViewHolder holder, int position) {
        dto_productos dtoP =proList.get(position);

        holder.textViewId.setText(String.valueOf(dtoP.getId_producto()));
        holder.textViewNombre.setText(dtoP.getNom_producto());
        holder.textViewDescripcion.setText(dtoP.getDes_producto());
        holder.textViewStock.setText(String.valueOf(dtoP.getStock()));
        holder.textViewPrecio.setText(String.valueOf(dtoP.getPrecio()));
        holder.textViewUnidad.setText(dtoP.getUnidad_medida());
        holder.textViewEstado.setText(String.valueOf(dtoP.getEstado_producto()));
        holder.textViewCategoria.setText(String.valueOf(dtoP.getCategoria()));
        holder.textViewFecha.setText(dtoP.getFecha_entrada());

    }

    @Override
    public int getItemCount() {
        return  proList.size();
    }

    public class ProViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewNombre, textViewDescripcion, textViewStock, textViewPrecio, textViewUnidad, textViewEstado, textViewCategoria, textViewFecha;
        public ProViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId =itemView.findViewById(R.id.textViewId);
            textViewNombre=itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion=itemView.findViewById(R.id.textViewDescripcion);
            textViewStock =itemView.findViewById(R.id.textViewStock);
            textViewPrecio=itemView.findViewById(R.id.textViewPrecio);
            textViewUnidad=itemView.findViewById(R.id.textViewUnidad);
            textViewEstado=itemView.findViewById(R.id.textViewEstado);
            textViewCategoria=itemView.findViewById(R.id.textViewCategoria);
            textViewFecha=itemView.findViewById(R.id.textViewFecha);
        }


    }
}
