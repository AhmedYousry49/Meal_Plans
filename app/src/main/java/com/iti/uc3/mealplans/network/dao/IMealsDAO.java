package com.iti.uc3.mealplans.network.dao;

import com.iti.uc3.mealplans.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface IMealsDAO {

    @GET("api/json/v1/1/search.php")
    Call<MealResponse> getMealByName(@Query("s")String name);

    @GET("api/json/v1/1/search.php")
    Call<MealResponse> getMealByFirstChar(@Query("f") String firstChar);

    @GET("api/json/v1/1/lookup.php")
    Call<MealResponse> getMealById(@Query("i") String id);
    @GET("api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();


}

