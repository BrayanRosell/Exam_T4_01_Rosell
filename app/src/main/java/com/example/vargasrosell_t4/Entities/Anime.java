package com.example.vargasrosell_t4.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "animes")
public class Anime {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "Nombre") // opcional
    public String Nombre;
    public String Descripcion;
    public String Imagen;

    public Anime() {

    }

    public Anime(int id, String nombre, String descripcion, String imagen) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        Imagen = imagen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
