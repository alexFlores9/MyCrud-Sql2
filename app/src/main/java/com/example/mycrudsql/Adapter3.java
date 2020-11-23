package com.example.mycrudsql;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycrudsql.productos.EditarProducto;

import java.util.List;

public class Adapter3 extends  RecyclerView.Adapter<Adapter3.ProViewHolder> {

    private static final String TAG = "Adapter";
    private Context mCtx;
    private List<dto_productos> proList;

    public Adapter3 (Context mCtx, List<dto_productos> proList){
        this.mCtx = mCtx;
        this.proList = proList;
    }

    private TextView getTextView(View v, int id_p){
        return v.findViewById(id_p);
    }



    @Override
    public ProViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout3, viewGroup, false);
        ProViewHolder viewHolder = new ProViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  ProViewHolder holder, int position) {

        holder.textViewId.setText(String.valueOf(proList.get(position).getId_producto()));
        holder.textViewNombre.setText(proList.get(position).getNom_producto());
        holder.textViewDescripcion.setText(proList.get(position).getDes_producto());
        holder.textViewStock.setText(String.valueOf(proList.get(position).getStock()));
        holder.textViewPrecio.setText(String.valueOf(proList.get(position).getPrecio()));
        holder.textViewUnidad.setText(proList.get(position).getUnidad_medida());
        holder.textViewEstado.setText(String.valueOf(proList.get(position).getEstado_producto()));
        holder.textViewCategoria.setText(String.valueOf(proList.get(position).getCategoria()));
        holder.textViewFecha.setText(proList.get(position).getFecha_entrada());

    }

    @Override
    public int getItemCount() {
        return  proList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ProViewHolder extends RecyclerView.ViewHolder {

        TextView textViewId, textViewNombre, textViewDescripcion, textViewStock, textViewPrecio, textViewUnidad, textViewEstado, textViewCategoria, textViewFecha;
        public ProViewHolder(View itemView) {

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vv) {

                    String id= getTextView(vv, R.id.textViewId).getText().toString();
                    String nombre= getTextView(vv, R.id.textViewNombre).getText().toString();
                    String descripcion = getTextView(vv, R.id.textViewDescripcion).getText().toString();
                    String stock = getTextView(vv, R.id.textViewStock).getText().toString();
                    String precio = getTextView(vv, R.id.textViewPrecio).getText().toString();
                    String unidad = getTextView(vv, R.id.textViewUnidad).getText().toString();
                    String estado = getTextView(vv, R.id.textViewEstado).getText().toString();
                    String categoria = getTextView(vv, R.id.textViewCategoria).getText().toString();
                    String fecha = getTextView(vv, R.id.textViewFecha).getText().toString();

                    Bundle bb = new Bundle();

                   bb.putString("id", id);
                    bb.putString("nombre", nombre);
                    bb.putString("descripcion", descripcion);
                    bb.putString("estado", estado);
                    bb.putString("precio", precio);
                    bb.putString("unidad", unidad);
                    bb.putString("estado", estado);
                    bb.putString("categoria", categoria);
                    bb.putString("fecha", fecha);

                  Navigation.findNavController(vv).navigate(R.id.nav_editarProducto, bb);
                }
            });
        }


    }
}
