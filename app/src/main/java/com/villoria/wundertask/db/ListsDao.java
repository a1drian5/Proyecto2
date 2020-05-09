package com.villoria.wundertask.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface ListsDao {
    @Query("SELECT * FROM listas WHERE listid = :id ")
    Lists getListById(String id);

    @Query("SELECT * FROM listas WHERE correo = :mail ORDER BY name")
    List<Lists> getAllListsbyMail(String mail);

    @Query("SELECT * FROM listas WHERE shared = :share")
    List<Lists> getAllListsbyShare(int share);

    @Query("SELECT * FROM listas ORDER BY name")
    List<Lists> getAllLists();

    @Update
    void updateList(Lists lists);

    @Insert
    void insertList(Lists lists);

    @Insert
    void insertAllList(List<Lists> lists);

    @Delete
    void deleteList(Lists lists);

    @Delete
    void deleteLists(List<Lists> lists);
}
