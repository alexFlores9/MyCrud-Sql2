package com.example.mycrudsql.productos;

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
import com.example.mycrudsql.Adapter3;
import com.example.mycrudsql.R;
import com.example.mycrudsql.Setting_VAR;
import com.example.mycrudsql.dto_productos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MostrarProductos extends Fragment {

    private RecyclerView listaR;
    dto_productos adaptador3;
    List<dto_productos> ProductoLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_mostrar_productos, container, false);

        listaR = root.findViewById(R.id.listaR);
        listaR.setHasFixedSize(true);
        listaR.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ProductoLista = new ArrayList<>();

        loadproductos();
        return root;
    }


    private void  loadproductos(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Setting_VAR.URL_consultaAllPro,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject productos = array.getJSONObject(i);

                                ProductoLista.add(new dto_productos(
                                        productos.getInt("id_producto"),
                                        productos.getString("nom_producto"),
                                        productos.getString("des_producto"),
                                        productos.getDouble("stock"),
                                        productos.getDouble("precio"),
                                        productos.getString("unidad_medida"),
                                        productos.getInt("estado_producto"),
                                        productos.getInt("categoria"),
                                        productos.getString("fecha_entrada")
                                ));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Adapter3 adapter3 = new Adapter3(getContext(), ProductoLista );
                        listaR.setAdapter(adapter3);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}