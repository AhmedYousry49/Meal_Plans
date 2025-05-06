package com.example.foodplanning.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplanning.db.AppDataBase;
import com.example.foodplanning.model.Category;
import com.example.foodplanning.model.Country;
import com.example.foodplanning.model.Ingredient;
import com.example.foodplanning.model.Meal;
import com.example.foodplanning.network.delegate.ICategoriesDelegate;
import com.example.foodplanning.network.delegate.ICountriesDelegate;
import com.example.foodplanning.network.delegate.IIngredientsDelegate;
import com.example.foodplanning.network.delegate.IMealsDelegate;

import java.util.List;

public class DataRepository {
    private final RemoteRepository remoteRepo;
    private AppDataBase localRepo;
    private final Context context;
    private static DataRepository instance;
    private DataRepository(Context context) {
        this.context = context;
        remoteRepo = new RemoteRepository(context);
        localRepo =  AppDataBase.getInstance(context);
    }

















    public static synchronized DataRepository getInstance(Context context) {

        if (instance == null) {
            instance = new DataRepository(context);
        }
        return instance;
    }
    public LiveData<List<Meal>> getMealByName(String name) {

        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        remoteRepo.getMealByName(name,new IMealsDelegate()
        {
            @Override
            public void onSuccessResultMeal (List < Meal > meals) {
                data.setValue(meals);
            }
            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Meal>> getMealByFirstChar(String firstChar) {

        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        remoteRepo.getMealByFirstChar(firstChar,new IMealsDelegate()
        {
            @Override
            public void onSuccessResultMeal (List < Meal > meals) {
                data.setValue(meals);
            }
            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Meal>> getMealById(String id) {

        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        remoteRepo.getMealByFirstChar(id,new IMealsDelegate()
        {
            @Override
            public void onSuccessResultMeal (List < Meal > meals) {
                data.setValue(meals);
            }
            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Meal>> getMealsByIngredient(String ingredient) {

        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        remoteRepo.getMealsByIngredient(ingredient,new IMealsDelegate()
        {
            @Override
            public void onSuccessResultMeal (List < Meal > meals) {
                data.setValue(meals);
            }
            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Meal>> getMealsByCountry(String country) {

        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        remoteRepo.getMealsByCountry(country,new IMealsDelegate()
        {
            @Override
            public void onSuccessResultMeal (List < Meal > meals) {
                data.setValue(meals);
            }
            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Meal>> getMealsByCategory(String category) {

        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        remoteRepo.getMealsByCategory(category,new IMealsDelegate()
        {
            @Override
            public void onSuccessResultMeal (List < Meal > meals) {
                data.setValue(meals);
            }
            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Ingredient>> getAllIngredients() {

        MutableLiveData<List<Ingredient>> data = new MutableLiveData<>();
        remoteRepo.getAllIngredients(new IIngredientsDelegate()
        {
            @Override
            public void onSuccessResultIngredients(List<Ingredient> ingredient) {
                data.setValue(ingredient);
            }
            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Country>> getAllCountries() {

        MutableLiveData<List<Country>> data = new MutableLiveData<>();

        remoteRepo.getAllCountries(new ICountriesDelegate()
        {
            @Override
            public void onSuccessResultCategories(List<Country> country) {
                data.setValue(country);
            }

            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Category>> getAllCategories() {

        MutableLiveData<List<Category>> data = new MutableLiveData<>();

        remoteRepo.getAllCategories(new ICategoriesDelegate()
        {


            @Override
            public void onSuccessResultCategories(List<Category> category) {
                data.setValue(category);
            }

            @Override
            public void onFailureResult (){
                data.setValue(null);
            }
        });
        return data;
    }



}
