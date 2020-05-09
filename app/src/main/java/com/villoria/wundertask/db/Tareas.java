package com.villoria.wundertask.db;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;

@Entity(tableName = "tareas")
public final class Tareas {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id", typeAffinity = TEXT)
    private String id;
    @ColumnInfo(name = "id_list", typeAffinity = TEXT)
    private String id_list;
    @ColumnInfo(name = "nombre", typeAffinity = TEXT)
    private String nombre;
    @ColumnInfo(name = "descripcion", typeAffinity = TEXT)
    private String descripcion;
    @ColumnInfo(name = "importancia", typeAffinity = INTEGER)
    private int importancia;
    @ColumnInfo(name = "date", typeAffinity = TEXT)
    private String date;
    @ColumnInfo(name = "completado", typeAffinity = INTEGER)
    private int completado;

    public Tareas(String id, String id_list, String nombre, String descripcion, int importancia, String date,int completado) {
        this.id = id;
        this.id_list = id_list;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.importancia = importancia;
        this.date = date;
        this.completado = completado;
    }

    @Ignore
    public Tareas(String id_list, String nombre, String descripcion, int importancia, String date) {
        this.id_list = id_list;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.importancia = importancia;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getId_list() {
        return id_list;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImportancia() {
        return importancia;
    }

    public String getDate() {
        return date;
    }

    public int getCompletado() { return completado; }

    public void setId(String id) {
        this.id = id;
    }

    public void setId_list(String id_list) {
        this.id_list = id_list;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCompletado(int completado) { this.completado = completado;    }
}
