package com.example.vargasrosell_t4.Services;

import com.example.vargasrosell_t4.Entities.Anime;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface AnimeService {
    @GET("contacts")
    retrofit2.Call<List<Anime>> getContacts();

    // contacts/:id
    @GET("contacts/{id}")
    retrofit2.Call<Anime> findContact(@Path("id") int id);

    @POST("contacts")
    retrofit2.Call<Anime> create(@Body Anime contact);

    @DELETE("contacts/{id}")
    retrofit2.Call<Anime> delete(@Path("Id") int Id);
    @PUT("contacts/{id}")
    retrofit2.Call<Anime> edit(@Path("Id") int Id);
}
