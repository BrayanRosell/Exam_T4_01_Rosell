package com.example.vargasrosell_t4.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vargasrosell_t4.Entities.Anime;

import java.util.List;
@Dao
public interface Anime_DAO {
    @Query("SELECT * FROM animes")
    List<Anime> getAll();

    @Query("SELECT * FROM animes WHERE id = :id")
    Anime find(int id);

    @Insert
    void create(Anime contact);
}
