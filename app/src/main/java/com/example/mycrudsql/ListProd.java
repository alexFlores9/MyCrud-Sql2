package com.example.mycrudsql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListProd extends AppCompatActivity {
    private static final String URL_ListProd = "http://192.168.43.105/service2020/listProd.php";

    List<dto_productos> proList;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prod);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        proList = new ArrayList<>();

        loadproductos();
    }

    private void loadproductos(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ListProd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject productos = array.getJSONObject(i);

                        proList.add(new dto_productos(
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

                    Adapter3 adapter3 = new Adapter3(ListProd.this, proList);
                    recyclerView.setAdapter(adapter3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);

    }
}