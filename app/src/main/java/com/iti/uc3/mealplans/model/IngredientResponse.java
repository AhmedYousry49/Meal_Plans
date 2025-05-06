package com.iti.uc3.mealplans.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {
    @SerializedName("meals")
    private List<Ingredient> Ingredients;

    public IngredientResponse(List<Ingredient> Ingredients) {
        this.Ingredients = Ingredients;
    }

    public List<Ingredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredient(List<Ingredient> meals) {
        this.Ingredients = meals;
    }


}