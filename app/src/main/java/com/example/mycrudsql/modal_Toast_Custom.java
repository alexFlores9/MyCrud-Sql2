package com.example.mycrudsql;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

public class modal_Toast_Custom {


    Dialog myDialog;
    androidx.appcompat.app.AlertDialog.Builder dialogo;
    AlertDialog.Builder dialogo1;


    public void dialogAbout(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.acercade);
        myDialog.setCancelable(false);

        ImageView BtnCerrarAutor = myDialog.findViewById(R.id.BtnCerrarAutor);

        BtnCerrarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



}
