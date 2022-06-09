package com.example.vargasrosell_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.vargasrosell_t4.AppDataBase.AppDatabase;
import com.example.vargasrosell_t4.Dao.Anime_DAO;
import com.example.vargasrosell_t4.Entities.Anime;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mSharedPref;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mSharedPref = getPreferences(Context.MODE_PRIVATE);
        String token = mSharedPref.getString("com.example.vargasrosell_t4.TOKEN","");

        Button btnRegistrar = findViewById(R.id.btnNuevo);
        Button btnMostrar = findViewById(R.id.btnMostrar);
        Button btnSincronizar = findViewById(R.id.btnSincronizar);

        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = AppDatabase.getDatabase(getApplicationContext());
                Anime_DAO dao = db.animeDAO();

//        Contact contact = new Contact();
//        contact.name = "brayan rosell";
//        contact.number = "90909838";
//
//        dao.create(contact);


                List<Anime> contacts = dao.getAll();
                Log.i("APP_VJ20202", "cadena:" + new Gson().toJson(contacts));
                Intent intent = new Intent(MainActivity.this,SincronizarActivity.class);
                startActivity(intent);

            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrarActivity.class);
                startActivity(intent);
            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MostrarActivity.class);
                startActivity(intent);
            }
        });
    }

}