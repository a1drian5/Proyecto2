package com.villoria.wundertask.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TareasDao {

    @Query("SELECT * FROM tareas WHERE id = :id ")
    Tareas getTareasById(String id);

    @Query("SELECT * FROM tareas WHERE importancia > :id AND completado=0 ORDER BY importancia ")
    List<Tareas> getALLTareasByImp(int id);

    @Query("SELECT * FROM tareas WHERE id_list = :id ")
    List<Tareas> getTareasByListid(String id);

    @Query("SELECT * FROM tareas WHERE id_list = :id AND completado = 0")
    List<Tareas> getTareasListNocompletadas(String id);

    //Listas Generales
    @Query("SELECT * FROM tareas WHERE completado = 0 ")
    List<Tareas> getALLTareasNoCompletadas();

    @Query("SELECT * FROM tareas WHERE completado = 0  AND importancia > 0 ORDER BY importancia" )
    List<Tareas> getALLTareasNoCompletadasByImportancia();

    @Query("SELECT * FROM tareas WHERE completado = 0 AND date != '' ORDER BY date")
    List<Tareas> getALLTareasNoCompletadasByFecha();

    //Lista Especifica
    @Query("SELECT * FROM tareas WHERE id_list = :id")
    List<Tareas> getAllTareasNoCompletadasByListID(String id);

    //Lista de Completadas
    @Query("SELECT * FROM tareas WHERE completado = 1 ")
    List<Tareas> getAllTareasCompletadas();

    //Opciones 1
    @Query("SELECT * FROM tareas WHERE completado = 0 ORDER BY date ")
    List<Tareas> getALLTareasNoCompletadasORDERByFecha();

    @Query("SELECT * FROM tareas WHERE completado = 0 ORDER BY nombre ")
    List<Tareas> getALLTareasNoCompletadasORDERByName();

    @Query("SELECT * FROM tareas WHERE completado = 0 ORDER BY importancia ")
    List<Tareas> getALLTareasNoCompletadasORDERByImp();

    //Opciones 2
    @Query("SELECT * FROM tareas WHERE completado = 0  AND importancia > 0 ORDER BY importancia" )
    List<Tareas> getALLTareasNoCompletadasByImportanciaORDEREDImp();

    @Query("SELECT * FROM tareas WHERE completado = 0  AND importancia > 0 ORDER BY date" )
    List<Tareas> getALLTareasNoCompletadasByImportanciaORDEREDFecha();

    @Query("SELECT * FROM tareas WHERE completado = 0  AND importancia > 0 ORDER BY nombre" )
    List<Tareas> getALLTareasNoCompletadasByImportanciaORDEREDName();

    //Opciones 3
    @Query("SELECT * FROM tareas WHERE completado = 0 AND date != '' ORDER BY date")
    List<Tareas> getALLTareasNoCompletadasByFechaORDERdate();

    @Query("SELECT * FROM tareas WHERE completado = 0 AND date != '' ORDER BY nombre")
    List<Tareas> getALLTareasNoCompletadasByFechaORDERNombre();

    @Query("SELECT * FROM tareas WHERE completado = 0 AND date != '' ORDER BY importancia")
    List<Tareas> getALLTareasNoCompletadasByFechaORDERImpor();

    //Opciones 4
    @Query("SELECT * FROM tareas WHERE id_list = :id ORDER BY date")
    List<Tareas> getAllTareasNoCompletadasByListIDORDERFecha(String id);

    @Query("SELECT * FROM tareas WHERE id_list = :id ORDER BY nombre")
    List<Tareas> getAllTareasNoCompletadasByListIDORDERNombre(String id);

    @Query("SELECT * FROM tareas WHERE id_list = :id ORDER BY importancia")
    List<Tareas> getAllTareasNoCompletadasByListIDORDERImpor(String id);

    //Opciones 5
    @Query("SELECT * FROM tareas WHERE completado = 1 ORDER BY nombre")
    List<Tareas> getAllTareasCompletadasORDERNombre();

    @Query("SELECT * FROM tareas WHERE completado = 1 ORDER BY date")
    List<Tareas> getAllTareasCompletadasORDERFecha();

    @Query("SELECT * FROM tareas WHERE completado = 1 ORDER BY importancia")
    List<Tareas> getAllTareasCompletadasORDERImport();

    @Query("SELECT * FROM tareas WHERE completado = 0")
    List<Tareas> getTareasNocompletadas();

    @Query("SELECT * FROM tareas WHERE date != :id ")
    List<Tareas> getTareasByDate(String id);

    @Query("SELECT * FROM tareas WHERE id_list = :id ORDER BY nombre")
    List<Tareas> getAllTareasByList(String id);

    @Query("SELECT * FROM tareas ORDER BY nombre")
    List<Tareas> getAllTareas();

    @Update
    void updateTarea(Tareas tareas);

    @Insert
    void insertTarea(Tareas tareas);

    @Insert
    void insertAllTareas(List<Tareas> tareas);

    @Delete
    void deleteTarea(Tareas tareas);

    @Delete
    void deleteTareas(List<Tareas> tareas);

}
