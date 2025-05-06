package com.iti.uc3.mealplans.network.delegate;


import com.iti.uc3.mealplans.model.Meal;

import java.util.List;

public interface IMealsDelegate {

    void onSuccessResultMeal(List<Meal> meals);
    void onFailureResult();
}
