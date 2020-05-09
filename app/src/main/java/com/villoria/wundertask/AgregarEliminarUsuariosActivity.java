package com.villoria.wundertask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AgregarEliminarUsuariosActivity extends AppCompatActivity {

    private EditText correo;
    private ListView l_correos;
    private Button agregar, eliminar;
    private int PositionActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_eliminar_usuarios);

        correo = (EditText) findViewById(R.id.et_correo);
        l_correos = (ListView) findViewById(R.id.list_correos);
        agregar = (Button)findViewById(R.id.bt_agregar_correo);
        eliminar =(Button)findViewById(R.id.bt_eliminar_correo);

        final ArrayList<String> listacorreos= new ArrayList<>();
        listacorreos.add("Correo@hotmail.com");

        final ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listacorreos);
        l_correos.setAdapter(adaptador);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correo.getText().toString().isEmpty()){
                    CorreoVacio();
                }else{
                    listacorreos.add(correo.getText().toString());
                    adaptador.notifyDataSetChanged();
                    correo.setText("");
                }
            }
        });

        eliminar.setEnabled(false);
      //  if(!l_correos.isSelected()){
      //      eliminar.setEnabled(false);
      //  }
      //  if(l_correos.isSelected()){
      //      eliminar.setEnabled(true);
      //  }

        l_correos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(parent.getContext(),
                        "Selecciono: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();

               // eliminar.setOnClickListener(new View.OnClickListener() {
              //     @Override
              //     public void onClick(View v) {
              //         listacorreos.remove(position);
              //         adaptador.notifyDataSetChanged();
              //     }
              // });
                eliminar.setEnabled(true);
                PositionActual = position;
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listacorreos.remove(PositionActual);
                adaptador.notifyDataSetChanged();
                eliminar.setEnabled(false);
            }
        });


    }

    public void CorreoVacio(){
        Toast.makeText(this,"Debe introducir un correo electronico",Toast.LENGTH_SHORT).show();
    }
}
