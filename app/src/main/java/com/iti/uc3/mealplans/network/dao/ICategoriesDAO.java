package com.iti.uc3.mealplans.network.dao;

import com.iti.uc3.mealplans.model.CategoryResponse;
import com.iti.uc3.mealplans.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICategoriesDAO {

    @GET("api/json/v1/1/categories.php")
    Call<CategoryResponse> getAllCategories();

    @GET("api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

}
