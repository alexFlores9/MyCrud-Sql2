package com.example.mycrudsql;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycrudsql.categorias.MostrarCategorias;

import java.util.List;

public class Adapter extends   RecyclerView.Adapter<Adapter.CatViewHolder> {


    private static final String TAG = "Adapter";
    private Context mCtx;
    private List<dto_categorias> categoriaList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_idCat, tv_NombreCat, tv_Estado_Cat;
//        Button btneditarCat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idCat = itemView.findViewById(R.id.textViewId);
            tv_NombreCat = itemView.findViewById(R.id.textViewNombre);
            tv_Estado_Cat = itemView.findViewById(R.id.textViewPrecio);
//            btneditarCat = itemView.findViewById(R.id.btnEditarCat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = getTextView(v, R.id.textViewId).getText().toString();
                    String nombre = getTextView(v, R.id.textViewNombre).getText().toString();
                    String estado = getTextView(v, R.id.textViewPrecio).getText().toString();
                    Log.i(TAG, "Id: " + id + ", Nombre: " + nombre + ", Estado: " + estado);
                    Bundle b = new Bundle();
                    b.putString("id", id);
                    b.putString("nombre", nombre);
                    b.putString("estado", estado);
                    Navigation.findNavController(v).navigate(R.id.editarCategoria, b);
                }
            });
        }
    }

    private TextView getTextView(View v, int id){
        return v.findViewById(id);
    }










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
        }
    }
}

