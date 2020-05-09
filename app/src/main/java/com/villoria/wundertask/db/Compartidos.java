package com.villoria.wundertask.db;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;

@Entity(tableName = "compartidas")

public final class Compartidos {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id",typeAffinity = TEXT)
    private String id;
    @ColumnInfo(name = "list_id",typeAffinity = TEXT)
    private String list_id;
    @ColumnInfo(name = "correo",typeAffinity = TEXT)
    private String correo;
    @ColumnInfo(name = "nombre",typeAffinity = TEXT)
    private String nombre;
    @ColumnInfo(name = "creador",typeAffinity = INTEGER)
    private int creador;
    @ColumnInfo(name = "estado",typeAffinity = INTEGER)
    private int estado;

    public Compartidos(String id, String list_id, String correo, String nombre, int creador, int estado) {
        this.id = id;
        this.list_id = list_id;
        this.correo = correo;
        this.nombre = nombre;
        this.creador = creador;
        this.estado = estado;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreador() {
        return creador;
    }

    public int getEstado() {
        return estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCreador(int creador) {
        this.creador = creador;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
