package com.villoria.wundertask.db;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Tareas.class, Lists.class, Compartidos.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase{


    public abstract UserDao userDao();
    public abstract TareasDao tareasDao();
    public abstract ListsDao listsDao();
    public abstract CompartidosDao compartidosDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "Pruebas.db")
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    initializeData(db);
                                }
                            })
                            .build();
        }

        return INSTANCE;
    }

    public static void initializeData(@NonNull SupportSQLiteDatabase db) {
        //db.beginTransaction();

        //Usuarios
//        db.execSQL("INSERT INTO usuarios (correo,name,password,avatar,confirmacion,active) VALUES ('leo','Leo','335enter',0,1,1)");
//        db.execSQL("INSERT INTO usuarios (correo,name,password,avatar,confirmacion,active) VALUES ('lais','Leo','335enter',0,1,0)");
//
//
//         //Tareas
//        db.execSQL("INSERT INTO tareas (id, id_list, nombre, descripcion,importancia,date) VALUES (4,'lista1','prueba','prueba',1,'hoy')");
//        db.execSQL("INSERT INTO tareas (id, id_list, nombre, descripcion,importancia,date) VALUES (5,'lista1','prueb2','prueba',1,'hoy')");
//        // db.execSQL("INSERT INTO configurations (user_id, cine, fisica, matematicas,historia,deporte,geografia,no_question,dificulty,clues,no_clues) VALUES (1,1,1,1,1,1,1,6,1,1,1)");
//        // db.execSQL("INSERT INTO configurations (user_id, cine, fisica, matematicas,historia,deporte,geografia,no_question,dificulty,clues,no_clues) VALUES (2,1,1,1,1,0,1,7,1,1,1)");
//
//        // //Lists
//        db.execSQL("INSERT INTO listas (id, name, correo, color,icono,shared) VALUES ('prueba','lista1','prueba','prueba',1,1)");
//        db.execSQL("INSERT INTO listas (id, name, correo, color,icono,shared) VALUES ('prueba2','lista2','prueba','prueba',1,1)");
//        // db.execSQL("INSERT INTO scores (count,user_id, name,score,pistas) VALUES (1,1,'Adr',40,3)");
//        // db.execSQL("INSERT INTO scores (count,user_id, name,score,pistas) VALUES (2,2,'Prr',10,0)");
//
//        //Compartidas
//        db.execSQL("INSERT INTO compartidas (id,list_id,correo,nombre,creador,estado) VALUES ('prueba','resp1','resp2','resp3',1,1)");
//        db.execSQL("INSERT INTO compartidas (id,list_id,correo,nombre,creador,estado) VALUES ('prueba2','resp1','resp2','resp3',1,1)");

    }
}