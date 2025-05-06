package com.example.foodplanning.network.dao;

import com.example.foodplanning.model.CountryResponse;
import com.example.foodplanning.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICountriesDAO {



    @GET("list.php?a=list")
    Call<CountryResponse> getAllCountries();
    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String country);
}
