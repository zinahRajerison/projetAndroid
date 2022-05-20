package com.example.lafamilledesanimaux.models;

import com.example.lafamilledesanimaux.views.Fiche;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface UserService {

    @POST("/login")
    public Call<LoginResponse> login(@Body User user);

    @POST("user/signup")
    public Call<LoginResponse> signUp(@Body User user);

    @POST("/findById")
    public Call<FicheResponse> findById(@Body Id id);
}
