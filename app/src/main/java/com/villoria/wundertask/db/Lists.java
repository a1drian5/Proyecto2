package com.villoria.wundertask.db;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;

@Entity(tableName = "listas")

public final class Lists {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "listid",typeAffinity = TEXT)
    private String listid;
    @ColumnInfo(name = "name", typeAffinity = TEXT)
    private String name;
    @ColumnInfo(name = "correo", typeAffinity = TEXT)
    private String correo;
    @ColumnInfo(name = "color", typeAffinity = TEXT)
    private String color;
    @ColumnInfo(name = "icono", typeAffinity = INTEGER)
    private int icono;
    @ColumnInfo(name = "shared", typeAffinity = INTEGER)
    private int shared;

    public Lists(String listid, String name, String correo, String color, int icono, int shared) {
        this.listid = listid;
        this.name = name;
        this.correo = correo;
        this.color = color;
        this.icono = icono;
        this.shared = shared;
    }

    @NonNull
    public String getListid() { return listid;}

    public String getName() {
        return name;
    }

    public String getCorreo() {
        return correo;
    }

    public String getColor() {
        return color;
    }

    public int getIcono() {
        return icono;
    }

    public int getShared() {
        return shared;
    }

    public void setListid( String listid) { this.listid = listid; }

    public void setName(String name) {
        this.name = name;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }
}
