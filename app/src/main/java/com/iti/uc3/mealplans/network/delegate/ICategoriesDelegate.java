package com.example.foodplanning.network.delegate;

import com.example.foodplanning.model.Category;

import java.util.List;

public interface ICategoriesDelegate {

    void onSuccessResultCategories(List<Category> ingredient);
    void onFailureResult( );

}
