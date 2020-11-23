package com.example.mycrudsql;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycrudsql.categorias.MostrarCategorias;

import java.util.List;

public class Adapter extends   RecyclerView.Adapter<Adapter.CatViewHolder> {


    private static final String TAG = "Adapter";
    private Context mCtx;
    private List<dto_categorias> categoriaList;

    private TextView getTextView(View v, int id){
        return v.findViewById(id);
    }

   public Adapter (Context mCtx, List<dto_categorias> categoriaLista){
       this.mCtx = mCtx;
       this.categoriaList = categoriaLista;
   }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false);
            CatViewHolder view = new CatViewHolder(v);
            return  view;
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
       dto_categorias dtoC =categoriaList.get(position);
       holder.textViewId.setText(String.valueOf(dtoC.getId_categoria()));
       holder.textViewNombre.setText(dtoC.getNom_categoria());
       holder.textViewEstado.setText(String.valueOf(dtoC.getEstado_categoria()));
    }

    @Override
    public int getItemCount() {
        return  categoriaList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
       TextView textViewId,textViewNombre,textViewEstado;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId =itemView.findViewById(R.id.textViewId);
            textViewNombre =itemView.findViewById(R.id.textViewNombre);
            textViewEstado =itemView.findViewById(R.id.textViewEstado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String id = getTextView(v, R.id.textViewId).getText().toString();
                    String nombre = getTextView(v, R.id.textViewNombre).getText().toString();
                    String estado = getTextView(v, R.id.textViewEstado).getText().toString();

                    Bundle b = new Bundle();
                    b.putString("id", id);
                    b.putString("nombre", nombre);
                    b.putString("estado", estado);

                    Navigation.findNavController(v).navigate(R.id.nav_editarCategoria, b);
                }
            });
        }
    }
}

