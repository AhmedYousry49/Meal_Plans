package com.iti.uc3.mealplans.network.delegate;

import com.iti.uc3.mealplans.model.Category;

import java.util.List;

public interface ICategoriesDelegate {

    void onSuccessResultCategories(List<Category> ingredient);
    void onFailureResult( );

}
