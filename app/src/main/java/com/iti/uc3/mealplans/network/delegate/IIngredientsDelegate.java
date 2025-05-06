package com.example.foodplanning.network.delegate;

import com.example.foodplanning.model.Ingredient;
import com.example.foodplanning.model.Meal;

import java.util.List;

public interface IIngredientsDelegate {
    void onSuccessResultIngredients(List<Ingredient> ingredient);
    void onFailureResult();

}
