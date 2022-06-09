package com.example.vargasrosell_t4.AppDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vargasrosell_t4.Dao.Anime_DAO;
import com.example.vargasrosell_t4.Entities.Anime;

@Database(entities = {Anime.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Anime_DAO animeDAO();

    public static AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "com.example.androidvj20221.database.contacts.db")
                .allowMainThreadQueries()
                .build();
    }
}
