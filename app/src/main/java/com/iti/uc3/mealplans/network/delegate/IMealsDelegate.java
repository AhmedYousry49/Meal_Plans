package com.example.foodplanning.network.delegate;

import com.example.foodplanning.model.Meal;

import java.util.List;

public interface IMealsDelegate {

    void onSuccessResultMeal(List<Meal> meals);
    void onFailureResult();
}
