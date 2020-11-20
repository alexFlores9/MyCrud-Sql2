package com.example.mycrudsql;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycrudsql.usuarios.Usuarios;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText edtUsuario,edtPassword;
    Button btnLogin,btnusu,btnacerca;

    modal_Toast_Custom mo = new modal_Toast_Custom();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_aviso)
                    .setTitle("Warning")
                    .setMessage("Realmente desea salir?")
                    .setNegativeButton(android.R.string.cancel,null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario=findViewById(R.id.edtUsuario);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnusu=findViewById(R.id.btnusu);
        btnacerca=findViewById(R.id.btnacerca);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario("http://192.168.43.105/service2020/validar_usuario.php");
            }
        });

        btnusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                Usuarios us = new Usuarios();
                fm.beginTransaction().replace(R.id.contenedor,us).commit();
            }
        });

        btnacerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mo.dialogAbout(Login.this);
            }
        });

    }
    private void validarUsuario(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             if (!response.isEmpty()){
                 Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(intent);

                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         borrar();
                     }
                 },2000);

             }else{
                 Toast.makeText(Login.this, "usuario o contra incorrecta", Toast.LENGTH_SHORT).show();
             }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Login.this, "nel bro", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("usuario",edtUsuario.getText().toString());
                parametros.put("clave",edtPassword.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void borrar() {
        edtUsuario.setText(null);
        edtPassword.setText(null);

    }
}