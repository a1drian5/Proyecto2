package com.villoria.wundertask.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface UserDao {
    @Query("SELECT * FROM usuarios WHERE correo = :id ")
    User getUserById(String id);

    @Query("SELECT * FROM usuarios WHERE active = 1 ")
    int getUserById();

    @Query("SELECT * FROM usuarios ORDER BY correo")
    List<User> getAllUser();

    @Query("SELECT * FROM usuarios WHERE active = 1 ORDER BY correo")
    User getActiveUser();

    @Update
    void updateUser(User user);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}
