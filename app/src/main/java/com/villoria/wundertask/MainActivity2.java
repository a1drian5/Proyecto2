package com.villoria.wundertask;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.villoria.wundertask.db.AppDataBase;
import com.villoria.wundertask.db.Compartidos;
import com.villoria.wundertask.db.Lists;
import com.villoria.wundertask.db.Tareas;
import com.villoria.wundertask.db.User;
import com.villoria.wundertask.ui.home.HomeFragment;
import com.villoria.wundertask.ui.home.HomeViewModel;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2  extends AppCompatActivity implements Iomunicacion {

    private AppBarConfiguration mAppBarConfiguration;

    public static String ListaEnviada="";
    public static String ListAct="";
    public static boolean a = false;
    public List<Lists> listsC = new ArrayList<Lists>();
    public List<Lists> listsN = new ArrayList<Lists>();
    public List<Tareas> TareasC = new ArrayList<Tareas>();
    public List<Tareas> TareasN = new ArrayList<Tareas>();
    public List<Compartidos> Comp = new ArrayList<Compartidos>();
    public int Actual;
    public static List<Tareas> tareasImprimir = new ArrayList<Tareas>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
/*        ImageView im = findViewById(R.id.AvatarImage);
        TextView use = findViewById(R.id.NameUser);
        TextView useco = findViewById(R.id.CorreoUser);
        im.setImageResource(R.drawable.a1);*/

        listsC = MainActivity.listsC ;
        listsN = MainActivity.listsN ;
        TareasC = MainActivity.TareasC;
        TareasN = MainActivity.TareasN;
        Comp = MainActivity.Comp;

        setSupportActionBar(toolbar);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbDescargar = database.getReference("app").child("Usuarios").child(MainActivity.Correousuario);
        dbDescargar.addChildEventListener(new ChildEventListener() {
            @Override public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String ssd) { }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }


            /*      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                                    {
                                        Stetho.initializeWithDefaults(getBaseContext());
                                        final AppDataBase db = AppDataBase.getAppDatabase(getBaseContext());
                                        String  ab = dataSnapshot.getKey();

                                        if(ab.equals("ListasCompartidas")) {
                                            listsN = db.listsDao().getAllListsbyShare(1);
                                            for(int u =0;u<listsN.size();u++) {
                                                TareasC = db.tareasDao().getAllTareasByList(listsN.get(u).getListid());
                                            }
                                            Comp = db.compartidosDao().getAllCompartidos();
                                            try {
                                                listsC.clear();
                                                TareasC.clear();
                                                for (DataSnapshot Ds : dataSnapshot.getChildren()) {

                                                    String a = Ds.getKey().toString();
                                                    if (a.equals("ValorIrrelevante") != true && Ds.exists()) {
                                                        Lists li = new Lists(
                                                                Ds.child("listid").getValue().toString(),
                                                                Ds.child("name").getValue().toString(),
                                                                Sinpunto(MainActivity.Correousuario),
                                                                Ds.child("color").getValue().toString(),
                                                                Integer.parseInt(Ds.child("icono").getValue().toString()),
                                                                0);
                                                        listsC.add(li);

                                                        for (DataSnapshot ds : dataSnapshot.child(li.getListid()).child("Tareas").getChildren()) {
                                                            if (ds.getKey().toString().equals("ValorIrrelevante") != true) {

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
                                                                    Toast.makeText(MainActivity2.this, "Tareas", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                Toast.makeText(MainActivity2.this, "No compartidas", Toast.LENGTH_SHORT).show();
                                            }
                                            try {
                                                List<Tareas> borraT = new ArrayList<>();
                                                borraT = db.tareasDao().getAllTareas();

                                                List<Lists> borraL = new ArrayList<>();
                                                borraL = db.listsDao().getAllLists();

                                                List<Compartidos> borraC = new ArrayList<>();
                                                borraC = db.compartidosDao().getAllCompartidos();

                                                db.tareasDao().deleteTareas(borraT);
                                                db.listsDao().deleteLists(borraL);
                                                db.compartidosDao().deleteCompartidos(borraC);
                                                if(listsC.size()>0) db.listsDao().insertAllList(listsC);
                                                if(listsN.size()>0) db.listsDao().insertAllList(listsN);
                                                if(TareasC.size()>0) db.tareasDao().insertAllTareas(TareasC);
                                                if(TareasN.size()>0) db.tareasDao().insertAllTareas(TareasN);
                                                if(Comp.size()>0) db.compartidosDao().insertAllCompartidos(Comp);
                                            }catch (Exception e)
                                            {
                                                Toast.makeText(MainActivity2.this, "No envia a sql", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else if(ab.equals("ListasLocales")){
                                            listsC = db.listsDao().getAllListsbyShare(1);
                                            for(int u =0;u<listsN.size();u++) {
                                                TareasN = db.tareasDao().getAllTareasByList(listsN.get(u).getListid());
                                            }
                                            try {
                                                for (DataSnapshot Ds : dataSnapshot.getChildren()) {
                                                    if (Ds.getKey().toString().equals("ValorIrrelevante") != true) {
                                                        Lists lit = new Lists(
                                                                Ds.child("listid").getValue().toString(),
                                                                Ds.child("name").getValue().toString(),
                                                                Ds.child("correo").getValue().toString(),
                                                                Ds.child("color").getValue().toString(),
                                                                Integer.parseInt(Ds.child("icono").getValue().toString()),
                                                                1);
                                                        listsN.add(lit);

                                                        for (DataSnapshot dsc : dataSnapshot.child(lit.getListid()).child("Usuarios").getChildren()) {
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
                                                                Toast.makeText(MainActivity2.this, "compartusuarios", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                        ////////////////////////////////////////////
                                                        for (DataSnapshot ds : dataSnapshot.child("ListasCompartidas").child(lit.getListid()).child("Tareas").getChildren()) {
                                                            if (ds.getKey().toString().equals("ValorIrrelevante") != true&&ds.exists()&& ds.getKey().equals("Tareas") != true) {

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
                                                Toast.makeText(MainActivity2.this, "No compartidas", Toast.LENGTH_SHORT).show();
                                            }
                                            try {
                                                List<Tareas> borraT = new ArrayList<>();
                                                borraT = db.tareasDao().getAllTareas();

                                                List<Lists> borraL = new ArrayList<>();
                                                borraL = db.listsDao().getAllLists();

                                                List<Compartidos> borraC = new ArrayList<>();
                                                borraC = db.compartidosDao().getAllCompartidos();

                                                db.tareasDao().deleteTareas(borraT);
                                                db.listsDao().deleteLists(borraL);
                                                db.compartidosDao().deleteCompartidos(borraC);
                                                if(listsC.size()>0) db.listsDao().insertAllList(listsC);
                                                if(listsN.size()>0) db.listsDao().insertAllList(listsN);
                                                if(TareasC.size()>0) db.tareasDao().insertAllTareas(TareasC);
                                                if(TareasN.size()>0) db.tareasDao().insertAllTareas(TareasN);
                                                if(Comp.size()>0) db.compartidosDao().insertAllCompartidos(Comp);
                                            }catch (Exception e)
                                            {
                                                Toast.makeText(MainActivity2.this, "No envia a sql", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                        */
            @Override public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

            @Override public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override public void onCancelled(@NonNull DatabaseError databaseError) { }
        });



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        displayMenu();

    }

    private void displayMenu(){

        Stetho.initializeWithDefaults(this);
        final AppDataBase db = AppDataBase.getAppDatabase(this);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final List<Lists> list = db.listsDao().getAllLists();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("app").child("Usuarios");
        dbRef.child(MainActivity.Correousuario).child("active").setValue(1);

        //navController es lo que me da control de en que fragmento se encuentra
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //AppBarConfiguration nos otorga las direcciones para el drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_other)
                .setDrawerLayout(drawer)
                .build();
        //

        //Los dos de abajo otorgan el boton para abrir el menú, sin embargo, es raro a profundidad.
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //A partir de aquí, el menu, agregar los botones junto con el
        final Menu menu = navigationView.getMenu();

        final SubMenu menu1 = menu.addSubMenu("Listas");
        final SubMenu menu2 = menu.addSubMenu("Listas compartidas");

        //funcion add agrega items. Cada item contiene icono y un onclick para controlar su salida
        menu1.add("Todos").setIcon(R.drawable.ic_menu_all_24dp)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        navController.popBackStack();
                        a= false;
                        Actual =1;
                        navController.navigate(R.id.nav_home);//Este es el fragmento dentro del mobile_navigation;
                        return false;
                    }
                });
        menu1.add("Importantes").setIcon(R.drawable.ic_menu_important_24dp)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        navController.popBackStack();
                        a= false;
                        Actual =2;
                        navController.navigate(R.id.nav_gallery);
                        return false;
                    }
                });
        menu1.add("Planeados").setIcon(R.drawable.ic_menu_planned_24dp)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        navController.popBackStack();
                        a= false;
                        Actual =3;
                        navController.navigate(R.id.nav_slideshow);
                        return false;
                    }
                });
        menu1.add("Agregar lista").setIcon(R.drawable.ic_menu_add_24dp)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Primero el Intent
                        Intent intent = new Intent(MainActivity2.this, NewListActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });
        for(int u =0; u< list.size();u++)
        {
            final int enviado= u;
            if(list.get(u).getShared()==0) {
                menu1.add(list.get(u).getName()).setIcon(R.drawable.ic_menu_planned_24dp/*list.get(u).getIcono()*/)
                        .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                navController.popBackStack();
                                a= true;
                                Actual =4;
                                navController.navigate(R.id.nav_other);
                                ListaEnviada = list.get(enviado).getListid();
                                return false;
                            }
                        });
            }
        }
        for(int u =0; u< list.size();u++)
        {
            final int enviado = u;

            if(list.get(u).getShared()==1) {
                menu2.add(list.get(u).getName()).setIcon(R.drawable.ic_menu_planned_24dp/*list.get(u).getIcono()*/)
                        .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                navController.popBackStack();
                                a= true;
                                Actual =4;
                                navController.navigate(R.id.nav_other);
                                ListaEnviada = list.get(enviado).getListid();
                                return false;
                            }
                        });
            }
        }
        menu2.add("Agregar listas compartidas").setIcon(R.drawable.ic_menu_add_24dp)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Intent intent = new Intent(MainActivity2.this, NewListActivity.class);
                        startActivity(intent);
                        //La parte de abajo agrega para las listas compartidas, sin embargo,
                        //debe abrir la lista compartida

                        return false;
                    }
                });

        //Cada que cambia el destino, se coloca esto. La verdad no hace nada por el momento
        /*navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                //controller.popBackStack();
                //El popBackStack me quita el fragmento colocado, pero quita funcionalidad al boton de abrir
            }
        });*/

        drawer.closeDrawers();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //CUANDO CREES MENU TOOLBAR, AQUI SE AGREGAN LAS COSAS
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if(a == true) {
                        Intent intent = new Intent(MainActivity2.this, EditListActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity2.this,"No puedes editar esta lista",Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });

        MenuItem logoutItem = menu.findItem(R.id.action_logout);
        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Stetho.initializeWithDefaults(getBaseContext());
                final AppDataBase db = AppDataBase.getAppDatabase(getBaseContext());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference dbRef = database.getReference("app").child("Usuarios");
                dbRef.child(MainActivity.Correousuario).child("active").setValue(0);

                try {

                    User user = db.userDao().getUserById(MainActivity.Correousuario);

                    Toast.makeText(MainActivity2.this, "Hasta Luego" + user.getName(), Toast.LENGTH_SHORT).show();

                    db.userDao().deleteUser(user);
                    user.setActive(0);
                    db.userDao().updateUser(user);
                    MainActivity.Correousuario ="";
                    MainActivity.identi = true;
                    finish();
                }catch (Exception e)
                {

                }
                return false;
            }
        });
        MenuItem sortImpItem = menu.findItem(R.id.action_sortByImportance);
        sortImpItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Stetho.initializeWithDefaults(getBaseContext());
                final AppDataBase db = AppDataBase.getAppDatabase(getBaseContext());
                switch (Actual)
                {
                    case 1:///TODOS
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasORDERByImp();
                        break;
                    case 2://Importancia
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasByImportanciaORDEREDImp();
                        break;
                    case 3://Fecha
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasByFechaORDERImpor();

                        break;
                    case 4://BYID
                        tareasImprimir = db.tareasDao().getAllTareasNoCompletadasByListIDORDERImpor(MainActivity2.ListaEnviada);

                        break;
                }
                Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(intent);
                finish();
                return false;
            }
        });

        MenuItem sortDateItem = menu.findItem(R.id.action_sortByDate);
        sortDateItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Stetho.initializeWithDefaults(getBaseContext());
                final AppDataBase db = AppDataBase.getAppDatabase(getBaseContext());
                switch (Actual) {
                    case 1:///TODOS
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasORDERByFecha();
                        break;
                    case 2://Importancia
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasByImportanciaORDEREDFecha();

                        break;
                    case 3://Fecha
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasByFechaORDERdate();
                        break;
                    case 4://BYID
                        tareasImprimir = db.tareasDao().getAllTareasNoCompletadasByListIDORDERFecha(MainActivity2.ListaEnviada);
                        break;
                }
                Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(intent);
                finish();
                return false;
            }
        });

        MenuItem sortUserItem = menu.findItem(R.id.action_sortByUser);
        sortUserItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Stetho.initializeWithDefaults(getBaseContext());
                final AppDataBase db = AppDataBase.getAppDatabase(getBaseContext());
                switch (Actual)
                {
                    case 1:///TODOS
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasORDERByName();
                        break;
                    case 2://Importancia
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasByImportanciaORDEREDName();
                        break;
                    case 3://Fecha
                        tareasImprimir = db.tareasDao().getALLTareasNoCompletadasByFechaORDERNombre();
                        break;
                    case 4://BYID
                        tareasImprimir = db.tareasDao().getAllTareasNoCompletadasByListIDORDERFecha(MainActivity2.ListaEnviada);

                        break;
                }
                Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(intent);
                finish();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void CambioActivity(String a)
    {
        AgregarTareas.DONDEVIENES=a;
        Intent intent = new Intent(MainActivity2.this, AgregarTareas.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent inte = new Intent(Intent.ACTION_MAIN);
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
