package com.villoria.wundertask.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;

@Entity(tableName = "usuarios")
public final class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "correo", typeAffinity = TEXT)
    private String correo;
    @ColumnInfo(name = "name", typeAffinity = TEXT)
    private String name;
    @ColumnInfo(name = "password", typeAffinity = TEXT)
    private String password;
    @ColumnInfo(name = "avatar", typeAffinity = INTEGER)
    private int avatar;
    @ColumnInfo(name = "confirmacion", typeAffinity = INTEGER)
    private int confirmacion;
    @ColumnInfo(name = "active", typeAffinity = INTEGER)
    private int active;

    public User(String correo, String name, String password, int avatar, int confirmacion, int active) {
        this.correo = correo;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
        this.confirmacion = confirmacion;
        this.active = active;
    }
    public void vacio()
    {
        this.correo = "";
        this.name = "";
        this.password= "";
        this.avatar =0;
        this.confirmacion = 0;
        this.active = 0;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getActive() {
        return active;
    }

    public String getCorreo() {
        return correo;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAvatar() {
        return avatar;
    }

    public int getConfirmacion() {
        return confirmacion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setConfirmacion(int confirmacion) {
        this.confirmacion = confirmacion;
    }
}
