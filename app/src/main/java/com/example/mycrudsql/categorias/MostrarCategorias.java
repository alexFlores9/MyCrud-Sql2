package com.example.mycrudsql.categorias;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycrudsql.Adapter;
import com.example.mycrudsql.R;
import com.example.mycrudsql.Setting_VAR;
import com.example.mycrudsql.dto_categorias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MostrarCategorias extends Fragment {

    private RecyclerView listaRV;
    dto_categorias adaptador;
    List<dto_categorias> CategoriaLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_mostrar_categorias, container, false);

        listaRV = root.findViewById(R.id.listaRV);
        listaRV.setHasFixedSize(true);
        listaRV.setLayoutManager(new LinearLayoutManager(this.getContext()));

        CategoriaLista = new ArrayList<>();

        loadcategoria();
        return root;
    }


    private void loadcategoria(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Setting_VAR.URL_consultaAllCategorias,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject categorias = array.getJSONObject(i);

                                CategoriaLista.add(new dto_categorias(
                                        categorias.getInt("id_categoria"),
                                        categorias.getString("nom_categoria"),
                                        categorias.getInt("estado_categoria")
                                ));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Adapter adapter = new Adapter(getContext(), CategoriaLista );
                        listaRV.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}
