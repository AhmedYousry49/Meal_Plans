package com.example.foodplanning.network.dao;

import com.example.foodplanning.model.CategoryResponse;
import com.example.foodplanning.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICategoriesDAO {

    @GET("categories.php")
    Call<CategoryResponse> getAllCategories();

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

}
