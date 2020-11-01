package com.example.mycrudsql.usuarios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mycrudsql.Login;
import com.example.mycrudsql.MainActivity;
import com.example.mycrudsql.MySingleton;
import com.example.mycrudsql.R;
import com.example.mycrudsql.Setting_VAR;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Usuarios extends Fragment  implements View.OnClickListener{
    private TextInputLayout ti_idusuario, ti_nombre, ti_apellido, ti_correo,  ti_usuario, ti_clave, ti_tipo, ti_estado, ti_pregunta, ti_respuesta;
    private EditText et_idusuario, et_nombre, et_apellido, et_correo,  et_usuario, et_clave,et_tipo, et_estado,et_pregunta, et_respuesta;
    private Spinner sp_estado,sp_tipo,sp_pregunta;
    private Button btnSaveu, btnNewu;
    String datoSelect = "";
    String datoSelectt = "";
    String datoSelectp = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_usuarios, container, false);
        ti_idusuario = root.findViewById(R.id.ti_idusuario);
        ti_nombre = root.findViewById(R.id.ti_nombre);
        ti_apellido = root.findViewById(R.id.ti_apellido);
        ti_correo = root.findViewById(R.id.ti_correo);
        ti_usuario = root.findViewById(R.id.ti_usuario);
        ti_clave = root.findViewById(R.id.ti_clave);
       // ti_tipo = root.findViewById(R.id.ti_tipo);

        ti_respuesta = root.findViewById(R.id.ti_respuesta);
        //ti_pregunta = root.findViewById(R.id.ti_pregunta);

        et_idusuario = root.findViewById(R.id.et_idusuario);
        et_nombre = root.findViewById(R.id.et_nombre);
        et_apellido = root.findViewById(R.id.et_apellido);
        et_correo = root.findViewById(R.id.et_correo);
        et_usuario = root.findViewById(R.id.et_usuario);
        et_clave = root.findViewById(R.id.et_clave);
       // et_tipo = root.findViewById(R.id.et_tipo);
        et_respuesta = root.findViewById(R.id.et_respuesta);
       // et_pregunta = root.findViewById(R.id.et_pregunta);




        sp_estado = root.findViewById(R.id.sp_estado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.pre2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estado.setAdapter(adapter);
        sp_estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_estado.getSelectedItemPosition()>0) {
                    datoSelect = sp_estado.getSelectedItem().toString();
                }else{
                    datoSelect = "";
                }
                //Toast.makeText(getContext(), ""+datoSelect, Toast.LENGTH_SHORT).show();
 /*
 if(conta>=1 && sp_especialista.getSelectedItemPosition()>0){
 //Toast toast = Toast.makeText(getApplicationContext(), sp_especialista.getSelectedItem().toString(),
Toast.LENGTH_SHORT);
 //toast.setGravity(Gravity.CENTER, 0, 0);
 //toast.show();
 String dato_SP = sp_especialista.getSelectedItem().toString();
 String s[] = dato_SP.split("~");
 documento = s[0].trim();
 String nombres = s[1];
 }else{
 }
 conta++;
 */
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp_tipo= root.findViewById(R.id.sp_tipo);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.pre, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipo.setAdapter(adapter2);
        sp_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_tipo.getSelectedItemPosition()>0) {
                    datoSelectt = sp_tipo.getSelectedItem().toString();
                }else{
                    datoSelectt = "";
                }
                //Toast.makeText(getContext(), ""+datoSelect, Toast.LENGTH_SHORT).show();
 /*
 if(conta>=1 && sp_especialista.getSelectedItemPosition()>0){
 //Toast toast = Toast.makeText(getApplicationContext(), sp_especialista.getSelectedItem().toString(),
Toast.LENGTH_SHORT);
 //toast.setGravity(Gravity.CENTER, 0, 0);
 //toast.show();
 String dato_SP = sp_especialista.getSelectedItem().toString();
 String s[] = dato_SP.split("~");
 documento = s[0].trim();
 String nombres = s[1];
 }else{
 }
 conta++;
 */
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp_pregunta= root.findViewById(R.id.sp_pregunta);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.preg, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_pregunta.setAdapter(adapter3);
        sp_pregunta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_pregunta.getSelectedItemPosition()>0) {
                    datoSelectp = sp_pregunta.getSelectedItem().toString();
                }else{
                    datoSelectp = "";
                }
                //Toast.makeText(getContext(), ""+datoSelect, Toast.LENGTH_SHORT).show();
 /*
 if(conta>=1 && sp_especialista.getSelectedItemPosition()>0){
 //Toast toast = Toast.makeText(getApplicationContext(), sp_especialista.getSelectedItem().toString(),
Toast.LENGTH_SHORT);
 //toast.setGravity(Gravity.CENTER, 0, 0);
 //toast.show();
 String dato_SP = sp_especialista.getSelectedItem().toString();
 String s[] = dato_SP.split("~");
 documento = s[0].trim();
 String nombres = s[1];
 }else{
 }
 conta++;
 */
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnSaveu = root.findViewById(R.id.btnSaveu);
        btnNewu = root.findViewById(R.id.btnNewu);
        btnSaveu.setOnClickListener(this);
        btnNewu.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSaveu:
                //save_server(getContext(),);
                String id = et_idusuario.getText().toString();
                String nombre = et_nombre.getText().toString();
                String apellido = et_apellido.getText().toString();
                String correo = et_correo.getText().toString();
                String usuario = et_usuario.getText().toString();
                String clave = et_clave.getText().toString();

              //  String pregunta = et_pregunta.getText().toString();
                String respuesta = et_respuesta.getText().toString();

                if (id.length() == 0){
                    et_idusuario.setError("Campo obligatorio");
                }else if(nombre.length() == 0){
                    et_nombre.setError("Campo obligatorio");
                    //}else if(!datoSelect.isEmpty()){
                }else if(apellido.length() == 0){
                    et_apellido.setError("Campo obligatorio");
                    //}else if(!datoSelect.isEmpty()){
                }else if(correo.length() == 0){
                    et_correo.setError("Campo obligatorio");
                    //}else if(!datoSelect.isEmpty()){
                }else if(usuario.length() == 0){
                    et_usuario.setError("Campo obligatorio");
                    //}else if(!datoSelect.isEmpty()){
                }else if(clave.length() == 0){
                    et_clave.setError("Campo obligatorio");
                    //}else if(!datoSelect.isEmpty()){
                }else if(respuesta.length() == 0){
                    et_respuesta.setError("Campo obligatorio");
                    //}else if(!datoSelect.isEmpty()){
                }else if(sp_estado.getSelectedItemPosition() > 0){
                    //Acciones para guardar registro en la base de datos.
                    Toast.makeText(getContext(), "Bien...", Toast.LENGTH_SHORT).show();
                    save_server(getContext(), Integer.parseInt(id), nombre,apellido,correo,usuario,clave,Integer.parseInt(datoSelectt),Integer.parseInt(datoSelect),(datoSelectp),respuesta);




                }
                break;
            case R.id.btnNewu:
                new_categories();
                break;
            default:
        }
    }
    private void save_server(final Context context, final int id, final String nombre, final String apellido, final String correo, final String usuario, final String clave, final int
            tipo, final int estado, final String pregunta, final String respuesta) {
        StringRequest request = new StringRequest(Request.Method.POST, Setting_VAR.URL_guardar_usuario,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject requestJSON = null;
                        try {
                            requestJSON = new JSONObject(response.toString());
                            String estado = requestJSON.getString("estado");
                            String mensaje = requestJSON.getString("mensaje");
                            if(estado.equals("1")){
                                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                            }else if(estado.equals("2")){
                                Toast.makeText(context, ""+mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se puedo guardar. \n" +
                        "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", String.valueOf(id));
                map.put("nombre", nombre);
                map.put("apellido", apellido);
                map.put("correo", correo);
                map.put("usuario", usuario);
                map.put("clave", clave);
                map.put("tipo", String.valueOf(tipo));
                map.put("estado", String.valueOf(estado));
                map.put("pregunta", pregunta);
                map.put("respuesta", respuesta);
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
    private void new_categories() {
        et_idusuario.setText(null);
        et_nombre.setText(null);
        et_apellido.setText(null);
        et_correo.setText(null);
        et_usuario.setText(null);
        et_clave.setText(null);
        et_tipo.setText(null);
        et_estado.setText(null);
        et_respuesta.setText(null);

        sp_estado.setSelection(0);
    }
}