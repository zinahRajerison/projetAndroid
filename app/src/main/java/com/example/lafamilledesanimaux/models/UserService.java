package com.example.lafamilledesanimaux.models;

import com.example.lafamilledesanimaux.views.Fiche;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface UserService {
    /**
     * connexion
     * @param user
     * @return
     */
    @POST("/login")
    public Call<LoginResponse> login(@Body User user);

    /**
     * inscription
     * @param user
     * @return
     */
    @POST("user/signup")
    public Call<LoginResponse> signUp(@Body User user);

    /**
     * recherche animal par l'id
     * @param id
     * @return
     */
    @POST("/findById")
    public Call<FicheResponse> findById(@Body Id id);

    /**
     * ajout favoris animal
     * @param fav
     * @return
     */
    @POST("/ajoutFavoris")
    public Call<LoginResponse> ajoutFavoris(@Body Favoris fav);
}
