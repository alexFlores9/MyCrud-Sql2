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

public class PruebaList extends AppCompatActivity {


    private static final String URL_ListCat = "http://192.168.43.105/service2020/listCat.php";

    List<dto_categorias> categoriaList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoriaList = new ArrayList<>();

        loadcategorias();
    }
    private void loadcategorias(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ListCat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject categorias = array.getJSONObject(i);

                        categoriaList.add(new dto_categorias(
                                categorias.getInt("id_categoria"),
                                categorias.getString("nom_categoria"),
                                categorias.getInt("estado_categoria")
                        ));
                    }

                    Adapter adapter = new Adapter(PruebaList.this, categoriaList);
                    recyclerView.setAdapter(adapter);
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