package com.iti.uc3.mealplans.network.dao;

import com.iti.uc3.mealplans.model.IngredientResponse;
import com.iti.uc3.mealplans.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IngredientDAO {



    @GET("api/json/v1/1/list.php?i=list")
    Call<IngredientResponse> getAllIngredient();

    @GET("api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);



}
