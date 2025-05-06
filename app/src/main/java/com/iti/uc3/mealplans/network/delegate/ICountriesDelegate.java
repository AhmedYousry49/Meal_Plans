package com.iti.uc3.mealplans.network.delegate;


import com.iti.uc3.mealplans.model.Country;

import java.util.List;

public interface ICountriesDelegate {

    void onSuccessResultCategories(List<Country> ingredient);
    void onFailureResult();

}
