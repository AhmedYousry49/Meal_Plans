package com.example.foodplanning.network.dao;

import com.example.foodplanning.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMealsDAO {

    @GET("search.php")
    Call<MealResponse> getMealByName(@Query("s")String name);

    @GET("search.php")
    Call<MealResponse> getMealByFirstChar(@Query("f") String firstChar);

    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i") String id);
    @GET("random.php")
    Call<MealResponse> getRandomMeal();


}
