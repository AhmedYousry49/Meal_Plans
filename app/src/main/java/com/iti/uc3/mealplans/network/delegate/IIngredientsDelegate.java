package com.iti.uc3.mealplans.network.delegate;


import com.iti.uc3.mealplans.model.Ingredient;

import java.util.List;

public interface IIngredientsDelegate {
    void onSuccessResultIngredients(List<Ingredient> ingredient);
    void onFailureResult();

}
