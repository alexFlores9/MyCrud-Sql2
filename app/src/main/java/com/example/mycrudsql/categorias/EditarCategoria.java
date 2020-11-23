package com.example.mycrudsql.categorias;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mycrudsql.dto_categorias;
import com.example.mycrudsql.MySingleton;
import com.example.mycrudsql.R;
import com.example.mycrudsql.Setting_VAR;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
public class EditarCategoria extends Fragment {

    private static final String TAG = "EditarCategoria";
    private TextInputLayout ti_idcategoria, ti_namecategoria;
    private EditText et_idcategoria, et_namecategoria;
    private Spinner sp_estado;
    private Button btnSave, btnNew, btnEditar, btnEliminar;
    String datoSelect = "";

    private List<dto_categorias> CategoriaLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_editar_categoria, container, false);

        ti_idcategoria = root.findViewById(R.id.ti_idcategoria);
        ti_namecategoria = root.findViewById(R.id.ti_namecategoria);

        et_idcategoria = root.findViewById(R.id.et_idcategoria);
        et_namecategoria = root.findViewById(R.id.et_namecategoria);
        sp_estado = root.findViewById(R.id.sp_estado);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.estadoCategorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estado.setAdapter(adapter);

        et_idcategoria.setText(getArguments().getString("id"));
        et_namecategoria.setText(getArguments().getString("nombre"));
        sp_estado.setSelection(adapter.getPosition(getArguments().getString("estado")));

        sp_estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_estado.getSelectedItemPosition() > 0) {
                    datoSelect = sp_estado.getSelectedItem().toString();
                } else {
                    datoSelect = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnEditar = root.findViewById(R.id.btnEdit);
        btnEliminar = root.findViewById(R.id.btnEliminar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = et_idcategoria.getText().toString();
                String name = et_namecategoria.getText().toString();
                String estado = sp_estado.getSelectedItem().toString();

                Log.i(TAG, "onClick -> id: " + id + ", name: " + name + ", estado: " + estado);

                editarCat(Integer.parseInt(id), name, Integer.parseInt(estado));
                Navigation.findNavController(v).navigate(R.id.nav_mostrarCategorias);
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_idcategoria.getText().toString();

              //  Log.i(TAG, "onClick -> id: " + id );

                eliminarCat(Integer.parseInt(id));
                Navigation.findNavController(v).navigate(R.id.nav_mostrarCategorias);
            }
        });
        return root;
    }


    private void editarCat(final int id_categoria, final String nom_categoria, final int estado_categoria ) {
        final StringRequest request = new StringRequest(Request.Method.POST, Setting_VAR.URL_Editar_Categoria, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject resquestJSON = null;
                Log.i(TAG, "response:" + response);
                try {
                    resquestJSON = new JSONObject(response.toString());
                    String estado = resquestJSON.getString("estado");
                    String mensaje = resquestJSON.getString("mensaje");

                    if (estado.equals("1")) {
                        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                        Log.i(TAG ,"estado" + estado);
                    } else if (estado.equals("2")) {
                        Toast.makeText(getContext(), "" + mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), "No se puede editar.\n" + "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                List<dto_categorias> CategoriaLista;
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", String.valueOf(id_categoria));
                map.put("nombre", nom_categoria);
                map.put("estado", String.valueOf(estado_categoria));
                return map;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    private void eliminarCat(final int id_categoria) {
        final StringRequest request = new StringRequest(Request.Method.POST, Setting_VAR.URL_Eliminar_Categoria, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject resquestJSON = null;
                Log.i(TAG, "response:" + response);
                try {
                    resquestJSON = new JSONObject(response.toString());
                    String estado = resquestJSON.getString("estado");
                    String mensaje = resquestJSON.getString("mensaje");

                    if (estado.equals("1")) {
                        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                        Log.i(TAG ,"estado" + estado);
                    } else if (estado.equals("2")) {
                        Toast.makeText(getContext(), "" + mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), "No se puede eliminar.\n" + "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", String.valueOf(id_categoria));
                return map;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

}