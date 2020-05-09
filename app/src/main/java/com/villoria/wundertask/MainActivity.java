package com.villoria.wundertask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.villoria.wundertask.db.AppDataBase;
import com.villoria.wundertask.db.Compartidos;
import com.villoria.wundertask.db.Lists;
import com.villoria.wundertask.db.Tareas;
import com.villoria.wundertask.db.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.net.ConnectivityManager;
import 	android.net.NetworkInfo;


public class MainActivity extends AppCompatActivity {

    public int NR=1;
    public static String Correousuario="";
    public static boolean identi=false;
    public String correo;
    public String usuario;
    public String contra;
    public String recontra;
    public int avatarNum;
    public User log_in;
    public static List<Lists> listsC = new ArrayList<Lists>();
    public static List<Lists> listsN = new ArrayList<Lists>();
    public static List<Tareas> TareasC = new ArrayList<Tareas>();
    public static List<Tareas> TareasN = new ArrayList<Tareas>();
    public static List<Compartidos> Comp = new ArrayList<Compartidos>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);
        final AppDataBase db = AppDataBase.getAppDatabase(this);
        final Intent Main1Main2 = new Intent(this,MainActivity2.class);
        final Intent prueba = new Intent(this,AgregarEliminarUsuariosActivity.class);
       //6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666 startActivity(prueba);

        db.userDao().getActiveUser();

        String archivos [] = fileList();
        String Lineas[] = new String[100];
        try {

            User us =db.userDao().getActiveUser();
            if (us == null) {
            }
            else
            {
                Correousuario = us.getCorreo();
                startActivity(Main1Main2);
            }
        }catch (Exception e)
        {

        }

        RelativeLayout RL = findViewById(R.id.container);
        ImageView IL = findViewById(R.id.logoview);
        Button CB = findViewById(R.id.CrearUsuario);
        Button IS = findViewById(R.id.iniciar_secion);
        final EditText CorreoIngresar = findViewById(R.id.CorreoIngresar);
        final EditText ContraseñaIngresar = findViewById(R.id.ContraseñaIngresar);
        final ProgressBar Cargar = findViewById(R.id.Cargando);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("app").child("Usuarios");

        ArrayList<ImagenItem> mimageList;
        final imagenAdapter imadap;
        mimageList = new ArrayList<>();
        mimageList.add(new ImagenItem(R.drawable.a1));
        mimageList.add(new ImagenItem(R.drawable.a2));
        mimageList.add(new ImagenItem(R.drawable.a3));
        mimageList.add(new ImagenItem(R.drawable.a4));
        mimageList.add(new ImagenItem(R.drawable.a5));
        mimageList.add(new ImagenItem(R.drawable.a6));
        mimageList.add(new ImagenItem(R.drawable.a7));
        mimageList.add(new ImagenItem(R.drawable.a8));
        imadap = new imagenAdapter(this,mimageList);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
        {
            // Si hay conexión a Internet en este momento
        }
        else {
            // No hay conexión a Internet en este momento
        }

        int comprobar=0;

        if(ArchivoExiste(archivos, "Guardado.txt"))
        {
            try {
                InputStreamReader arch =  new InputStreamReader(openFileInput("Guardado.txt"));
                BufferedReader br = new BufferedReader(arch);
                String linea = br.readLine();
                int h=0;
                Lineas[h] = linea;

                try {
                    comprobar = Integer.parseInt(Lineas[0]);

                }catch (NumberFormatException nfe)
                {
                    Toast.makeText(MainActivity.this,"NO se convirtio",Toast.LENGTH_SHORT).show();
                }
                br.close();
                arch.close();
            }catch (IOException e)
            {
                Toast.makeText(MainActivity.this,"NO lo lee",Toast.LENGTH_SHORT).show();
            }
        }
        Random r = new Random();
        int BR=0;
        NR = r.nextInt(5)+1;
        BR = r.nextInt(2)+1;
        switch (BR)
        {
            case 1:
                IL.setBackgroundResource(R.drawable.l1);
                break;
            case 2:
                IL.setBackgroundResource(R.drawable.l2);
                break;
            case 3:
                IL.setBackgroundResource(R.drawable.l3);
                break;
            default:
                IL.setBackgroundResource(R.drawable.l1);
                break;
        }
        while(NR == comprobar)
        {
            Random i = new Random();
            NR = i.nextInt(5)+1;
        }
        switch (NR)
        {
            case 1:
                RL.setBackgroundResource(R.drawable.f1);
                break;
            case 2:
                RL.setBackgroundResource(R.drawable.f2);
                break;
            case 3:
                RL.setBackgroundResource(R.drawable.f3);
                break;
            case 4:
                RL.setBackgroundResource(R.drawable.f4);
                break;
            case 5:
                RL.setBackgroundResource(R.drawable.f5);
                break;
            case 6:
                RL.setBackgroundResource(R.drawable.f6);
                break;
            default:
                RL.setBackgroundResource(R.drawable.f6);
                break;
        }
        Guardar();
        CB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText Correo;
                final EditText Usuario;
                final EditText Contra;
                final EditText Recontra;
                final Spinner avatar;
                CorreoIngresar.setText("");
                ContraseñaIngresar.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.crear_usuario,null);
                Correo = view.findViewById(R.id.CorreoNew);
                Usuario = view.findViewById(R.id.NombreUsNew);
                Contra = view.findViewById(R.id.ContraNew);
                Recontra = view.findViewById(R.id.RecontraNew);
                avatar = view.findViewById(R.id.SpinerAvatar);
                avatar.setAdapter(imadap);
                avatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ImagenItem clicked = (ImagenItem) adapterView.getItemAtPosition(i);
                        avatarNum = clicked.getImagen();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                builder.setView(view).setTitle("").setCancelable(true)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    final DatabaseReference drf = FirebaseDatabase.getInstance().getReference("app").child("Usuarios");
                                    drf.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.child(CorreoIngresar.getText().toString()).exists())
                                            {
                                                Toast.makeText(MainActivity.this, "Ya existe este correo", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                correo = Correo.getText().toString();
                                                usuario = Usuario.getText().toString();
                                                contra = Contra.getText().toString();
                                                avatarNum = avatar.getSelectedItemPosition();
                                                recontra = Recontra.getText().toString();
                                                CrearUsuario();
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "Debe llenar el formato", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                try {
                    builder.create().show();
                    Toast.makeText(MainActivity.this, "Que onda que pez", Toast.LENGTH_SHORT).show();

                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, e + "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        IS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identi = false;
                Cargar.setVisibility(View.VISIBLE);
                final DatabaseReference drf = FirebaseDatabase.getInstance().getReference("app").child("Usuarios").child(Sinpunto(CorreoIngresar.getText().toString()));
                log_in = new User("","","",0,0,0);
                drf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Lugar) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {
                            if (Lugar.exists() && Correousuario == "" && identi == false) {
                                listsC.clear();
                                listsN.clear();
                                Comp.clear();
                                TareasN.clear();
                                TareasC.clear();
                                if (Lugar.child("correo").getValue().toString().equals(Sinpunto(CorreoIngresar.getText().toString()))) {
                                    try {
                                        if (Lugar.getKey().equals(Sinpunto(CorreoIngresar.getText().toString()))) {
                                            try {
                                                if (Lugar.child("password").getValue().toString().equals(ContraseñaIngresar.getText().toString())) {
                                                    if (Integer.parseInt(Lugar.child("confirmacion").getValue().toString()) > 0) {

                                                        //// Entro el usuario
                                                        Toast.makeText(MainActivity.this, "Bienvenido " + Lugar.child("name").getValue().toString(), Toast.LENGTH_SHORT).show();
                                                        log_in = new User(
                                                                Lugar.child("correo").getValue().toString(),
                                                                Lugar.child("name").getValue().toString(),
                                                                Lugar.child("password").getValue().toString(),
                                                                Integer.parseInt(Lugar.child("avatar").getValue().toString()),
                                                                Integer.parseInt(Lugar.child("confirmacion").getValue().toString()),
                                                                1);

                                                        try {
                                                            User ja = db.userDao().getUserById(log_in.getCorreo());
                                                            if (ja == null) {
                                                                db.userDao().insertUser(log_in);
                                                            }
                                                        } catch (Exception e) {

                                                        }
                                                        String contra_pu = ContraseñaIngresar.getText().toString();
                                                        String contra_re = log_in.getPassword();
/////////////////////////////////////////////////////////
                                                        {
                                                            try {
                                                                for (DataSnapshot Ds : Lugar.child("ListasLocales").getChildren()) {

                                                                    if (Ds.getKey().toString().equals("ValorIrrelevante") != true) {
                                                                        Lists li = new Lists(
                                                                                Ds.child("listid").getValue().toString(),
                                                                                Ds.child("name").getValue().toString(),
                                                                                Sinpunto(CorreoIngresar.getText().toString()),
                                                                                Ds.child("color").getValue().toString(),
                                                                                Integer.parseInt(Ds.child("icono").getValue().toString()),
                                                                                0);
                                                                        listsC.add(li);

                                                                        for (DataSnapshot ds : Lugar.child("ListasLocales").child(li.getListid()).child("Tareas").getChildren()) {
                                                                            if (ds.getKey().toString().equals("ValorIrrelevante") != true && ds.getKey().toString().equals("ValorIrrelevante") != true) {

                                                                                try {
                                                                                    Tareas ta = new Tareas(
                                                                                            ds.child("id").getValue().toString(),
                                                                                            li.getListid(),
                                                                                            ds.child("nombre").getValue().toString(),
                                                                                            ds.child("descripcion").getValue().toString(),
                                                                                            Integer.parseInt(ds.child("importancia").getValue().toString()),
                                                                                            ds.child("date").getValue().toString(),
                                                                                            Integer.parseInt(ds.child("completado").getValue().toString()));
                                                                                    TareasC.add(ta);
                                                                                } catch (Exception e) {
                                                                                    Toast.makeText(MainActivity.this, "Tareas", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } catch (Exception e) {
                                                                Toast.makeText(MainActivity.this, "No compartidas", Toast.LENGTH_SHORT).show();
                                                            }
                                                            try {
                                                                for (DataSnapshot Ds : Lugar.child("ListasCompartidas").getChildren()) {
                                                                    if (Ds.getKey().toString().equals("ValorIrrelevante") != true) {
                                                                        Lists lit = new Lists(
                                                                                Ds.child("listid").getValue().toString(),
                                                                                Ds.child("name").getValue().toString(),
                                                                                Ds.child("correo").getValue().toString(),
                                                                                Ds.child("color").getValue().toString(),
                                                                                Integer.parseInt(Ds.child("icono").getValue().toString()),
                                                                                1);
                                                                        listsN.add(lit);

                                                                        for (DataSnapshot dsc : Lugar.child("ListasCompartidas").child(lit.getListid()).child("Usuarios").getChildren()) {
                                                                            try {
                                                                                Compartidos compar = new Compartidos(
                                                                                        dsc.child("id").getValue().toString(),
                                                                                        lit.getListid(),
                                                                                        dsc.child("correo").getValue().toString(),
                                                                                        dsc.child("usuario").getValue().toString(),
                                                                                        Integer.parseInt(dsc.child("creador").getValue().toString()),
                                                                                        Integer.parseInt(dsc.child("estado").getValue().toString()));
                                                                                Comp.add(compar);

                                                                            } catch (Exception e) {
                                                                                Toast.makeText(MainActivity.this, "compartusuarios", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        }
                                                                        ////////////////////////////////////////////
                                                                        for (DataSnapshot ds : Lugar.child("ListasCompartidas").child(lit.getListid()).child("Tareas").getChildren()) {
                                                                            if (ds.getKey().toString().equals("ValorIrrelevante") != true) {

                                                                                Tareas tal = new Tareas(
                                                                                        ds.child("id").getValue().toString(),
                                                                                        lit.getListid(),
                                                                                        ds.child("nombre").getValue().toString(),
                                                                                        ds.child("descripcion").getValue().toString(),
                                                                                        Integer.parseInt(ds.child("importancia").getValue().toString()),
                                                                                        ds.child("date").getValue().toString(),
                                                                                        Integer.parseInt(ds.child("completado").getValue().toString()));
                                                                                TareasN.add(tal);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } catch (Exception e) {
                                                                Toast.makeText(MainActivity.this, "No compartidas", Toast.LENGTH_SHORT).show();
                                                            }

                                                            List<Tareas> borraT = new ArrayList<>();
                                                            borraT = db.tareasDao().getAllTareas();

                                                            List<Lists> borraL = new ArrayList<>();
                                                            borraL = db.listsDao().getAllLists();

                                                            List<Compartidos> borraC = new ArrayList<>();
                                                            borraC = db.compartidosDao().getAllCompartidos();

                                                            db.tareasDao().deleteTareas(borraT);
                                                            db.listsDao().deleteLists(borraL);
                                                            db.compartidosDao().deleteCompartidos(borraC);
                                                            log_in.setActive(1);
                                                            db.listsDao().insertAllList(listsC);
                                                            db.listsDao().insertAllList(listsN);
                                                            db.tareasDao().insertAllTareas(TareasC);
                                                            db.tareasDao().insertAllTareas(TareasN);
                                                            db.compartidosDao().insertAllCompartidos(Comp);
                                                            try {

                                                                Correousuario = log_in.getCorreo();

                                                                Cargar.setVisibility(View.INVISIBLE);
                                                                startActivity(Main1Main2);
                                                            } catch (Exception e) {
                                                                Toast.makeText(MainActivity.this, "Error enviar", Toast.LENGTH_SHORT).show();
                                                            }

//                                                    //Probando Insertar
//                                                    Tareas new_tareas = new Tareas("2","Codigo","Perro","Ada 3 ",1,"1-1-1",0);
//                                                    Tareas new_tareas2 = new Tareas("1","Codigo","Historia","Ada 3 ",1,"1-1-1",0);
//
//                                                    Lists new_Lists = new Lists("prueba","lista1","prueba","prueba",1,1);
//                                                    Lists new_Lists2 = new Lists("prueba2","lista12","prueba","prueba",1,1);
//                                                    //db.tareasDao().insertTarea(new_tareas);
//                                                    //db.tareasDao().insertTarea(new_tareas2);
//
//                                                    Compartidos new_comp = new Compartidos("prueba","lista1","prueba","prueba",1,1);
//                                                    Compartidos new_comp2 = new Compartidos("prueba2","lista1","prueba2","prueba",0,1);
//
//                                                    List<Tareas> lista_tareas = new ArrayList<>();
//                                                    lista_tareas.add(new_tareas);
//                                                    lista_tareas.add(new_tareas2);
//
//                                                    List<Lists> lista_listas = new ArrayList<>();
//                                                    lista_listas.add(new_Lists);
//                                                    lista_listas.add(new_Lists2);
//
//                                                    List<Compartidos> lista_compa = new ArrayList<>();
//                                                    lista_compa.add(new_comp);
//                                                    lista_compa.add(new_comp2);
//
//                                                    db.tareasDao().insertAllTareas(lista_tareas);
//                                                    db.listsDao().insertAllList(lista_listas);
//                                                    db.compartidosDao().insertAllCompartidos(lista_compa);
                                                        }
                                                        ////
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "Valide su correo Porfavor", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(MainActivity.this, "No coincide la contraseña", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (Exception e) {
                                                Toast.makeText(MainActivity.this, " Error  de contraseña", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(MainActivity.this, "No existe el correo", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(MainActivity.this, "Error de correo", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "problemas con el correo", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "No mames", Toast.LENGTH_SHORT).show();
                            }
                        }else
                        {

                        }
                        identi= false;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    boolean ArchivoExiste(String archivos [],String NomArch)
    {
        for (int i = 0;i<archivos.length;i++)
        {
            if(NomArch.equals(archivos[i]))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    public void Guardar()
    {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Guardado.txt", Activity.MODE_PRIVATE));
            archivo.write(NR+"");
            archivo.flush();
        }catch(IOException e)
        {
            Toast.makeText(MainActivity.this,"No lo guarda",Toast.LENGTH_SHORT).show();
        }
    }
    public void CrearUsuario()
    {
        Stetho.initializeWithDefaults(this);
        final AppDataBase db = AppDataBase.getAppDatabase(this);
        if(contra.equals(recontra)== true)
        {
            User Enviar =new  User(Sinpunto(correo),usuario,contra,avatarNum,0,0);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference dbRef = database.getReference("app").child("Usuarios");
            dbRef.child(Enviar.getCorreo()).setValue(Enviar);
            dbRef.child(Enviar.getCorreo()).child("ListasCompartidas").child("ValorIrrelevante").setValue("1");
            dbRef.child(Enviar.getCorreo()).child("ListasLocales").child("ValorIrrelevante").setValue("1");
            Lists li = new Lists("NuncaSeRepetira","Pendientes",Enviar.getCorreo(),"1",2,0);
            dbRef.child(Enviar.getCorreo()).child("ListasLocales").child(Enviar.getCorreo()).setValue(li);

            db.userDao().insertUser(Enviar);
        }
        else
        {
            Toast.makeText(MainActivity.this,"No lo guarda",Toast.LENGTH_SHORT).show();
        }
    }
    public String Sinpunto(String correo)
    {
        String regresar = "";
        for(int a=0; a<correo.length();a++)
        {
            if(correo.charAt(a)!='.')
            {
                regresar = regresar + correo.charAt(a);
            }
        }
        return regresar;
    }
}
