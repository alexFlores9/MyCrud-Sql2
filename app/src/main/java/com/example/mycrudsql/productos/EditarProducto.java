package com.example.mycrudsql.productos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mycrudsql.MySingleton;
import com.example.mycrudsql.R;
import com.example.mycrudsql.Setting_VAR;
import com.example.mycrudsql.dto_categorias;
import com.example.mycrudsql.dto_productos;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class EditarProducto extends Fragment {
//

    private TextInputLayout ti_id, ti_nombre_prod, ti_descripcion, ti_stock,
            ti_precio, ti_unidadmedida;
    private EditText et_id, et_nombre_prod, et_descripcion, et_stock,
            et_precio, et_unidadmedida, et_fecha;
    private Spinner sp_estadoProductos, sp_fk_categoria;
    private TextView tv_fechahora;
    private Button btnedit, btndelete;
    ProgressDialog progressDialog;
    ArrayList<String> lista = null;
    ArrayList<dto_categorias> listaCategorias;

    String elementos[] = {"Uno", "Dos", "Tres", "Cuatro", "Cinco"};
    final String[] elementos1 = new String[]{
            "Seleccione",
            "1",
            "2",
            "3",
            "4",
            "5"
    };
    String idcategoria = "";
    String nombrecategoria = "";
    int conta = 0;
    String datoStatusProduct = "";

    dto_productos dto = new dto_productos();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editar_producto, container, false);

        ti_id = view.findViewById(R.id.ti_id);
        ti_nombre_prod = view.findViewById(R.id.ti_nombre_prod);
        ti_descripcion = view.findViewById(R.id.ti_descripcion);

        et_id = view.findViewById(R.id.et_id);
        et_nombre_prod = view.findViewById(R.id.et_nombre_prod);
        et_descripcion = view.findViewById(R.id.et_descripcion);
        et_stock = view.findViewById(R.id.et_stock);
        et_precio = view.findViewById(R.id.et_precio);
        et_unidadmedida = view.findViewById(R.id.et_unidadmedida);
        sp_estadoProductos = view.findViewById(R.id.sp_estadoProductos);
        et_fecha=view.findViewById(R.id.et_fecha);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.estadoCategorias, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estadoProductos.setAdapter(adapter1);

        sp_fk_categoria = view.findViewById(R.id.sp_fk_categoria);
        tv_fechahora = view.findViewById(R.id.tv_fechahora);
        tv_fechahora.setText(timedate());
        btnedit = view.findViewById(R.id.btneditar);
        btndelete = view.findViewById(R.id.btneliminar);

        //Boton Editar
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                String nombre = et_nombre_prod.getText().toString();
                String descripcion = et_descripcion.getText().toString();
                String stock = et_stock.getText().toString();
                String precio = et_precio.getText().toString();
                String unidad = et_unidadmedida.getText().toString();
                String estado = sp_estadoProductos.getSelectedItem().toString();
                String categoria = sp_fk_categoria.getSelectedItem().toString();
                String  fecha  = et_fecha.getText().toString();

                editarPro(id, nombre, descripcion, stock, precio, unidad, estado, categoria, fecha);
                Navigation.findNavController(view).navigate(R.id.nav_mostrarProductos);
            }
        });

        //Boton Eliminar
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();

                // Log.i(TAG, "onClick -> id: " + id );

                eliminarPro(Integer.parseInt(id));
                Navigation.findNavController(view).navigate(R.id.nav_mostrarProductos);
            }
        });

        Bundle b = getArguments();

        et_id.setText(b.getString("id"));
        et_nombre_prod.setText(b.getString("nombre"));
        et_descripcion.setText(b.getString("descripcion"));
        et_stock.setText(b.getString("stock"));
        et_precio.setText(b.getString("precio"));
        et_unidadmedida.setText(b.getString("unidad"));
        sp_estadoProductos.setSelection(Integer.parseInt(b.getString("estado")));
        et_fecha.setText(b.getString("fecha"));

        sp_estadoProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_estadoProductos.getSelectedItemPosition() > 0) {
                    datoStatusProduct =  sp_estadoProductos.getSelectedItem().toString();
                } else {
                    datoStatusProduct = "";
                }
                Toast.makeText(getContext(), "" + datoStatusProduct, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fk_categorias(getContext(), b.getString("categoria"));

        sp_fk_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (conta >= 1 && sp_fk_categoria.getSelectedItemPosition() > 0) {
                    String item_spinner = sp_fk_categoria.getSelectedItem().toString();
                    String s[] = item_spinner.split("~");
                    idcategoria = s[0].trim();
                    nombrecategoria = s[1];
                    Toast toast = Toast.makeText(getContext(), "Id cat: " + idcategoria + "\n" + "Nombre Categoria: " + nombrecategoria, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    idcategoria = "";
                    nombrecategoria = "";
                }
                conta++;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }


    private String timedate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fecha = sdf.format(cal.getTime());
        return fecha;
    }

    public void fk_categorias(final Context context, final String Catid){
        listaCategorias = new ArrayList<dto_categorias>();
        lista = new ArrayList<String>();
        lista.add("Seleccione Categoria");
        String url = Setting_VAR.URL_consultaAllCategorias;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    int totalEncontrados = array.length();
                    // Toast.makeText(context, "Total:"+totalEncontrados, Toast.LENGTH_SHORT).show();

                    dto_categorias obj_categorias = null;

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject categoriasObject = array.getJSONObject(i);
                        int id_categoria = categoriasObject.getInt("id_categoria");
                        String nombre_categoria = categoriasObject.getString("nom_categoria");
                        int estado_categoria = Integer.parseInt(categoriasObject.getString("estado_categoria"));

                        obj_categorias = new dto_categorias(id_categoria, nombre_categoria, estado_categoria);

                        listaCategorias.add(obj_categorias);

                        lista.add(listaCategorias.get(i).getId_categoria()+" ~ "+listaCategorias.get(i).getNom_categoria());

                        ArrayAdapter<String> adaptador =new ArrayAdapter<String> (getContext(),android.R.layout.simple_spinner_item, lista);

                        sp_fk_categoria.setAdapter(adaptador);

                        Log.i("Id Categoria", String.valueOf(obj_categorias.getId_categoria()));
                        Log.i("Nombre Categoria", obj_categorias.getNom_categoria());
                        Log.i("Estado Categoria", String.valueOf(obj_categorias.getEstado_categoria()));
                    }

                    int selectedPosition = 0;
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).contains(Catid)){
                            selectedPosition = i;
                        }
                    }

                    sp_fk_categoria.setSelection(selectedPosition);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error. Compruebe su acceso a Internet.", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void editarPro(final String id, final String nom_producto, final String des_producto, final String stock, final String precio, final String unidad_medida, final String estado_producto, final String categoria ,final String fecha) {
        final StringRequest request = new StringRequest(Request.Method.POST, Setting_VAR.URL_editarProductos, new Response.Listener<String>() {
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
                Toast.makeText(getContext(), "No se puede guardar.\n" + "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                List<dto_productos> ListaProducto;
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", String.valueOf(id));
                map.put("nombre", nom_producto);
                map.put("descripcion", des_producto);
                map.put("stock", stock);
                map.put("precio", precio);
                map.put("unidad", unidad_medida);
                map.put("estado", estado_producto);
                map.put("categoria", categoria);
                map.put("fecha", fecha);
                return map;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    private void eliminarPro(final int id_producto) {
        final StringRequest request = new StringRequest(Request.Method.POST, Setting_VAR.URL_Eliminar_pro, new Response.Listener<String>() {
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
                map.put("id", String.valueOf(id_producto));
                return map;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

}