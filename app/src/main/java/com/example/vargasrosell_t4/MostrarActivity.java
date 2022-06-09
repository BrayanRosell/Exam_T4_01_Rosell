package com.example.vargasrosell_t4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.vargasrosell_t4.Adapter.AnimeAdapter;
import com.example.vargasrosell_t4.AppDataBase.AppDatabase;
import com.example.vargasrosell_t4.Dao.Anime_DAO;
import com.example.vargasrosell_t4.Entities.Anime;
import com.example.vargasrosell_t4.Factories.RetrofitFactory;
import com.example.vargasrosell_t4.Services.AnimeService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MostrarActivity extends AppCompatActivity {
    List<Anime> contacts = new ArrayList<>();
    SharedPreferences mSharedPref;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        db = AppDatabase.getDatabase(getApplicationContext());

        mSharedPref = getSharedPreferences("com.example.vargasrosell_t4.SHARED_PREFERENCES", Context.MODE_PRIVATE);

        String token = mSharedPref.getString("com.example.vargasrosell_t4.TOKEN", "");

        Log.i("APP_VJ20202", "El token es:" + token);
        FloatingActionButton btnSincronizar = findViewById(R.id.fab);

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
                Intent intent = new Intent(MostrarActivity.this,SincronizarActivity.class);
                //String contactJSON = new Gson().toJson(contacts);
                //intent.putExtra("CONTACT", contactJSON);
                startActivity(intent);

            }
        });

        Retrofit retrofit = RetrofitFactory.build(this);
        AnimeService service = retrofit.create(AnimeService.class);
        Call<List<Anime>> call = service.getContacts();
        call.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                if(!response.isSuccessful()) {
                    Log.e("APP_VJ20202", "Error de aplicación");
                } else {
                    Log.i("APP_VJ20202", "Respuesta Correcta");
                    contacts = response.body();
                    //saveInDatabase(contacts);
                    AnimeAdapter adapter = new AnimeAdapter(contacts);

                    RecyclerView rv = findViewById(R.id.rvContacts);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
                Log.e("APP_VJ20202", "No hubo conectividad con el servicio web");
            }
        });


    }
    private void saveInDatabase(List<Anime> animes) {
        Anime_DAO dao = db.animeDAO();
        for (Anime anime : animes) {
            dao.create(anime);
        }
    }
}