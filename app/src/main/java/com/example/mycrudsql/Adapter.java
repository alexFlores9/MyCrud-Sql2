package com.example.mycrudsql;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycrudsql.categorias.MostrarCategorias;

import java.util.List;

public class Adapter extends   RecyclerView.Adapter<Adapter.CatViewHolder> {

    private Context mCtx;
    private List<dto_categorias> categoriaList;

   public Adapter (PruebaList mCtx, List<dto_categorias>categoriaList){
       this.mCtx = mCtx;
       this.categoriaList = categoriaList;
   }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.list_layout,null);
            return new CatViewHolder(view);


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

    public class CatViewHolder extends RecyclerView.ViewHolder {
       TextView textViewId,textViewNombre,textViewEstado;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId =itemView.findViewById(R.id.textViewId);
            textViewNombre =itemView.findViewById(R.id.textViewNombre);
            textViewEstado =itemView.findViewById(R.id.textViewEstado);
        }
    }
}

