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



                    String id_p= getTextView(vv, R.id.textViewId).getText().toString();
                    String nom_p= getTextView(vv, R.id.textViewNombre).getText().toString();
                    String des_p= getTextView(vv, R.id.textViewDescripcion).getText().toString();
                    String st_p= getTextView(vv, R.id.textViewStock).getText().toString();
                    String pre_p= getTextView(vv, R.id.textViewPrecio).getText().toString();
                    String uni_p= getTextView(vv, R.id.textViewUnidad).getText().toString();
                    String est_p= getTextView(vv, R.id.textViewEstado).getText().toString();
                    String cat_p= getTextView(vv, R.id.textViewCategoria).getText().toString();
                    String fecha_p= getTextView(vv, R.id.textViewFecha).getText().toString();

                    Bundle bb = new Bundle();

                   bb.putString("id_p", id_p);
                    bb.putString("nom_p", nom_p);
                    bb.putString("des_p", des_p);
                    bb.putString("st_p", st_p);
                    bb.putString("pre_p", pre_p);
                    bb.putString("uni_p", uni_p);
                    bb.putString("est_p", est_p);
                    bb.putString("cat_p", cat_p);
                    bb.putString("fecha", fecha_p);

                    Navigation.findNavController(vv).navigate(R.id.nav_editarProducto, bb);
                }
            });
        }


    }
}
