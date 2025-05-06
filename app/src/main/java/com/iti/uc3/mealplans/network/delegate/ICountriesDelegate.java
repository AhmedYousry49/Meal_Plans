package com.example.foodplanning.network.delegate;

import com.example.foodplanning.model.Country;

import java.util.List;

public interface ICountriesDelegate {

    void onSuccessResultCategories(List<Country> ingredient);
    void onFailureResult();

}
