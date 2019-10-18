package com.example.fibonacciapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("locations")
    Call<List<Location>> getLocations();
}
