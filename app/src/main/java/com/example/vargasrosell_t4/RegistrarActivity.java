package com.example.vargasrosell_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vargasrosell_t4.Entities.Anime;
import com.example.vargasrosell_t4.Factories.RetrofitFactory;
import com.example.vargasrosell_t4.Services.AnimeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        EditText Nombre = findViewById(R.id.eTxtNombre);
        EditText Descripcion = findViewById(R.id.eTxtDescripcion);
        EditText Imagen = findViewById(R.id.eTxtUrlImagen);




        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = Nombre.getText().toString();
                String descripcion = Descripcion.getText().toString();
                String imagen = Imagen.getText().toString();
               //"http://pngimg.com/uploads/pokemon/pokemon_PNG9.png"

                Anime anime = new Anime(1,nombre,descripcion,imagen);

                Retrofit retrofit = RetrofitFactory.build(RegistrarActivity.this);
                AnimeService service = retrofit.create(AnimeService.class);
                Call<Anime> call = service.create(anime);
                call.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {

                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {

                    }
                });


                Intent intent = new Intent(RegistrarActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}