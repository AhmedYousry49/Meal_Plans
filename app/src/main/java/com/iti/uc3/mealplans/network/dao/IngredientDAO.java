package com.example.foodplanning.network.dao;

import com.example.foodplanning.model.IngredientResponse;
import com.example.foodplanning.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IngredientDAO {



    @GET("list.php?i=list")
    Call<IngredientResponse> getAllIngredient();

    @GET("filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);



}
