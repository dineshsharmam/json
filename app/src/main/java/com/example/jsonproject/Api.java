package com.example.jsonproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://run.mocky.io/";
    @GET("https://run.mocky.io/v3/3a016adf-9dd5-4190-8569-d419d5e5a660")
    Call<quiz> getquiz();
}
