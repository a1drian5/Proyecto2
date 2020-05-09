package com.villoria.wundertask.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompartidosDao {
    //@Query("SELECT * FROM compartidas WHERE correo = :id ")
    //User getUserById(int id);

    @Query("SELECT * FROM compartidas WHERE list_id = :id ORDER BY correo")
    List<Compartidos> getAllCompartidosByListId(String id);

    @Query("SELECT * FROM compartidas ORDER BY correo")
    List<Compartidos> getAllCompartidos();


    @Query("SELECT * FROM compartidas WHERE creador = 1 ORDER BY correo")
    Compartidos getCrador();

    @Update
    void updateCompartido(Compartidos compartidos);

    @Insert
    void insertCompartido(Compartidos compartidos);

    @Insert
    void insertAllCompartidos(List<Compartidos> compartidos);

    @Delete
    void deleteCompartido(Compartidos compartidos);

    @Delete
    void deleteCompartidos(List<Compartidos> compartidos);

}
