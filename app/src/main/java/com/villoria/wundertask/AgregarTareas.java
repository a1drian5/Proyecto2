package com.villoria.wundertask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.Stetho;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.villoria.wundertask.db.AppDataBase;
import com.villoria.wundertask.db.Lists;
import com.villoria.wundertask.db.Tareas;

public class AgregarTareas extends AppCompatActivity {

    public static String DONDEVIENES ="";
    public static String ListaActual="";
    public int nul=0;
    public Switch sw_importancia;
    public EditText ettitulo;
    public EditText etDescription;
    public TextView txt_titulo_layout;
    public RadioGroup grupo_importante;
    public RadioButton rb_muy,rb_poco,rb_import;
    public ImageView im_import;
    public Button eliminar,agregar,editar;
    public int variable_titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tareas);

        sw_importancia = (Switch)findViewById(R.id.switch_importancia);
        grupo_importante = (RadioGroup) findViewById(R.id.rg_importante);
        rb_import = (RadioButton) findViewById(R.id.rb_importante);
        rb_muy = (RadioButton) findViewById(R.id.rb_muy_importante);
        rb_poco = (RadioButton) findViewById(R.id.rb_poco_importante);
        im_import = (ImageView)findViewById(R.id.im_importancia);
        txt_titulo_layout = (TextView)findViewById(R.id.txt_agregar);
        eliminar = (Button)findViewById(R.id.bt_eliminar_correo);
        agregar = (Button)findViewById(R.id.bt_agregar_correo);
        editar = (Button)findViewById(R.id.bt_editar);
        ettitulo = findViewById(R.id.et_titulo);
        etDescription = findViewById(R.id.et_desc);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("app").child("Usuarios");
        Stetho.initializeWithDefaults(this.getBaseContext());
        final AppDataBase db = AppDataBase.getAppDatabase(this.getBaseContext());
        final DatePickerFragment dpf = new DatePickerFragment(this, R.id.etDates);
        final Tareas tar = db.tareasDao().getTareasById(ListaActual);


        if(DONDEVIENES=="Editar"){
            txt_titulo_layout.setText("EDITAR");
            agregar.setVisibility(View.INVISIBLE);
            ettitulo.setText(tar.getNombre());
            etDescription.setText(tar.getDescripcion());
            dpf.editText.setText(tar.getDate());
            agregar.setText("Completado");
            switch (tar.getImportancia())
            {
                case 0:
                    sw_importancia.setChecked(true);
                    im_import.setImageDrawable(getResources().getDrawable(R.drawable.rayo1));
                    break;
                case 1:
                    sw_importancia.setChecked(true);
                    im_import.setImageDrawable(getResources().getDrawable(R.drawable.rayo2));
                    break;
                case 2:
                    sw_importancia.setChecked(true);
                    im_import.setImageDrawable(getResources().getDrawable(R.drawable.rayo3));
                    break;
            }
        }
        else
        {
            txt_titulo_layout.setText("AGREGAR");
            editar.setVisibility(View.INVISIBLE);
            eliminar.setVisibility(View.INVISIBLE);
        }

        if(!sw_importancia.isChecked()){
            grupo_importante.setEnabled(false);
            grupo_importante.clearCheck();
            rb_import.setEnabled(false);
            rb_muy.setEnabled(false);
            rb_poco.setEnabled(false);
            rb_poco.setChecked(false);
            rb_muy.setChecked(false);
            rb_import.setChecked(false);
            im_import.setVisibility(View.INVISIBLE);
        }

        sw_importancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sw_importancia.isChecked()){
                    im_import.setVisibility(View.INVISIBLE);
                    grupo_importante.setEnabled(false);
                    grupo_importante.clearCheck();
                    rb_poco.setChecked(false);
                    rb_muy.setChecked(false);
                    rb_import.setChecked(false);
                    rb_import.setEnabled(false);
                    rb_muy.setEnabled(false);
                    rb_poco.setEnabled(false);
                }
                if(sw_importancia.isChecked()){
                    im_import.setVisibility(View.VISIBLE);
                    grupo_importante.setEnabled(true);
                    grupo_importante.clearCheck();
                    rb_import.setClickable(true);
                    rb_import.setEnabled(true);
                    rb_muy.setClickable(true);
                    rb_muy.setEnabled(true);
                    rb_poco.setClickable(true);
                    rb_poco.setEnabled(true);
                }
            }
        });

        rb_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im_import.setImageDrawable(getResources().getDrawable(R.drawable.rayo2));
                nul =1;
            }
        });

        rb_poco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im_import.setImageDrawable(getResources().getDrawable(R.drawable.rayo1));
                nul =0;
            }
        });

        rb_muy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im_import.setImageDrawable(getResources().getDrawable(R.drawable.rayo3));
                nul = 2;
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Lists L = db.listsDao().getListById(tar.getId_list());
                    if (L.getShared() == 1) {
                        dbRef.child(MainActivity.Correousuario).child("ListasCompartidas").child(L.getListid()).child("Tareas").child(tar.getId()).removeValue();
                    } else {
                        dbRef.child(MainActivity.Correousuario).child("ListasLocales").child(L.getListid()).child("Tareas").child(tar.getId()).removeValue();
                    }
                    Toast.makeText(AgregarTareas.this,"Se elimino la Tarea",Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    Toast.makeText(AgregarTareas.this,"No se pudo eliminar la tarea",Toast.LENGTH_SHORT).show();
                }
                db.tareasDao().deleteTarea(tar);
                Intent inteeent = new Intent(AgregarTareas.this, MainActivity2.class);
                startActivity(inteeent);
                finish();
            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(DONDEVIENES == "Editar") {
                    int importancia = 0;
                    if (rb_import.isChecked()) {
                        importancia = 2;
                    }
                    if (rb_muy.isChecked()) {
                        importancia = 3;
                    }
                    if (rb_poco.isChecked()) {
                        importancia = 1;
                    }

                    Tareas new_tarea = new Tareas(ListaActual, tar.getId_list(), ettitulo.getText().toString(),
                            etDescription.getText().toString(), importancia, dpf.editText.getText().toString(), 0);
                    db.tareasDao().insertTarea(new_tarea);
                }
                else {
                    tar.setNombre(ettitulo.getText().toString());
                    tar.setDescripcion(etDescription.getText().toString());
                    tar.setDate(dpf.editText.getText().toString());
                    Lists L = db.listsDao().getListById(tar.getId_list());
                    tar.setId(L.getListid() + "-" + tar.getNombre());
                    tar.setCompletado(1);
                    db.tareasDao().updateTarea(tar);
                }


                try {
                    Tareas tar1 = new Tareas(MainActivity2.ListaEnviada, ettitulo.getText().toString(), etDescription.getText().toString(),
                            nul, dpf.editText.getText().toString());
                    Lists L = db.listsDao().getListById(tar1.getId_list());
                    tar1.setId(L.getListid() + "-" + tar1.getNombre());

                    if (L.getShared() == 1) {
                        dbRef.child(MainActivity.Correousuario).child("ListasCompartidas").child(L.getListid()).child("Tareas").child(tar1.getId()).setValue(tar1);

                    } else {
                        dbRef.child(MainActivity.Correousuario).child("ListasLocales").child(L.getListid()).child("Tareas").child(tar1.getId()).setValue(tar1);
                    }
                    Toast.makeText(AgregarTareas.this,"Se agrego la Tarea",Toast.LENGTH_SHORT).show();

                }catch (Exception e)
                {
                    Toast.makeText(AgregarTareas.this,"No se pudo agregar la tarea",Toast.LENGTH_SHORT).show();
                }
                Intent inteeent = new Intent(AgregarTareas.this, MainActivity2.class);
                startActivity(inteeent);
                finish();
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tareas tarea = tar;
                db.tareasDao().deleteTarea(tarea);
                tar.setNombre(ettitulo.getText().toString());
                tar.setDescripcion(etDescription.getText().toString());
                tar.setDate(dpf.editText.getText().toString());
                Lists L = db.listsDao().getListById(tar.getId_list());
                tar.setId(L.getListid() + "-" + tar.getNombre());
                db.tareasDao().insertTarea(tar);


                try {

                    if (L.getShared() == 1) {
                        dbRef.child(MainActivity.Correousuario).child("ListasCompartidas").child(L.getListid()).child("Tareas").child(tarea.getId()).removeValue();
                        dbRef.child(MainActivity.Correousuario).child("ListasCompartidas").child(L.getListid()).child("Tareas").child(tar.getId()).setValue(tar);

                    } else {
                        dbRef.child(MainActivity.Correousuario).child("ListasLocales").child(L.getListid()).child("Tareas").child(tarea.getId()).removeValue();
                        dbRef.child(MainActivity.Correousuario).child("ListasLocales").child(L.getListid()).child("Tareas").child(tar.getId()).setValue(tar);
                    }
                    Toast.makeText(AgregarTareas.this,"Se edito la Tarea",Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    Toast.makeText(AgregarTareas.this,"No se pudo editar la Tarea",Toast.LENGTH_SHORT).show();
                }
                Intent inteeent = new Intent(AgregarTareas.this, MainActivity2.class);
                startActivity(inteeent);
                finish();

            }
        });
    }
}
