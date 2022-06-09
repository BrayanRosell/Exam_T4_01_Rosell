package com.example.vargasrosell_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vargasrosell_t4.Entities.Anime;
import com.example.vargasrosell_t4.Factories.RetrofitFactory;
import com.example.vargasrosell_t4.Services.AnimeService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TiendaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        String contactJson = getIntent().getStringExtra("CONTACT");
        Anime anim = new Gson().fromJson(contactJson, Anime.class);
        Log.i("APP_VJ20202", "llega hasat a qui");

        TextView Nombre = findViewById(R.id.editNombre);
        TextView Descripcion = findViewById(R.id.editDescripcion);
        TextView Imagen = findViewById(R.id.editUrlImage);

        Nombre.setText(anim.Nombre);
        Descripcion.setText(anim.Descripcion);
        Imagen.setText(anim.Imagen);
        String nombre = Nombre.getText().toString();
        String descripcion = Descripcion.getText().toString();
        String imagen = Imagen.getText().toString();


        Button edit = findViewById(R.id.btnEditar);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.Nombre = nombre;
                anim.Descripcion = descripcion;
                anim.Imagen = imagen;
                //Anime anime = new Anime(1,nombre,descripcion,imagen);
                Retrofit retrofit = RetrofitFactory.build(TiendaActivity.this);
                AnimeService service = retrofit.create(AnimeService.class);
                Call<Anime> call = service.edit(anim.Id);
                call.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {

                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {

                    }
                });

            }
        });



    }
}