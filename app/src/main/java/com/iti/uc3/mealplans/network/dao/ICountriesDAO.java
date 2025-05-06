package com.iti.uc3.mealplans.network.dao;

import com.iti.uc3.mealplans.model.CountryResponse;
import com.iti.uc3.mealplans.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICountriesDAO {



    @GET("api/json/v1/1/list.php?a=list")
    Call<CountryResponse> getAllCountries();
    @GET("api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String country);
}
