package com.iti.uc3.mealplans.network.repository;

import android.content.Context;
import android.util.Log;

import com.iti.uc3.mealplans.model.CategoryResponse;
import com.iti.uc3.mealplans.model.CountryResponse;
import com.iti.uc3.mealplans.model.IngredientResponse;
import com.iti.uc3.mealplans.model.MealResponse;
import com.iti.uc3.mealplans.network.RetrofitClient;
import com.iti.uc3.mealplans.network.dao.IDataDAO;
import com.iti.uc3.mealplans.network.delegate.ICategoriesDelegate;
import com.iti.uc3.mealplans.network.delegate.ICountriesDelegate;
import com.iti.uc3.mealplans.network.delegate.IIngredientsDelegate;
import com.iti.uc3.mealplans.network.delegate.IMealsDelegate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RemoteRepository {
    private final IDataDAO dataDAO;


    public RemoteRepository(Context context) {
        dataDAO = RetrofitClient.getInstance(context).create(IDataDAO.class);
    }
    // --- MealsDAO Methods ---

//    public void getAllMeals(String Name,final NetworkDelegate networkDelegate) {
//        dataDAO.getAllMeals().enqueue(createMealCallback("getAllMeals", networkDelegate));
//    }

    public void getMealByName(String name, IMealsDelegate mealsDelegate) {
        dataDAO.getMealByName(name).enqueue(createMealCallback("getMealByName", mealsDelegate));
    }

    public void getMealByFirstChar(String firstChar, IMealsDelegate mealsDelegate) {
        dataDAO.getMealByFirstChar(firstChar).enqueue(createMealCallback("getMealByFirstChar", mealsDelegate));
    }

    public void getMealById(String id,  IMealsDelegate mealsDelegate) {
        dataDAO.getMealById(id).enqueue(createMealCallback("getMealById", mealsDelegate));
    }

    public void getRandomMeal( IMealsDelegate mealsDelegate) {
        dataDAO.getRandomMeal().enqueue(createMealCallback("getRandomMeal", mealsDelegate));
    }

    // --- IngredientDAO Methods ---
    public void getAllIngredients(IIngredientsDelegate ingredientsDelegate) {
        dataDAO.getAllIngredient().enqueue(createIngredientCallback("getAllIngredients", ingredientsDelegate));
    }

    public void getMealsByIngredient(String ingredient, IMealsDelegate mealsDelegate) {
        dataDAO.getMealsByIngredient(ingredient).enqueue(createMealCallback("getMealsByIngredient", mealsDelegate));
    }

    // --- CountriesDAO Methods ---
    public void getAllCountries(ICountriesDelegate countriesDelegate) {
        dataDAO.getAllCountries().enqueue(createCountryCallback("getAllCountries", countriesDelegate));
    }

    public void getMealsByCountry(String country, IMealsDelegate mealsDelegate) {
        dataDAO.getMealsByCountry(country).enqueue(createMealCallback("getMealsByCountry", mealsDelegate));
    }

    // --- CategoriesDAO Methods ---
    public void getAllCategories(ICategoriesDelegate categoriesDelegate) {
        dataDAO.getAllCategories().enqueue(createCategoryCallback("getAllCategories", categoriesDelegate));
    }

    public void getMealsByCategory(String category, IMealsDelegate mealsDelegate) {
        dataDAO.getMealsByCategory(category).enqueue(createMealCallback("getMealsByCategory", mealsDelegate));
    }

//    // --- Callback Handlers ---
    private Callback<MealResponse> createMealCallback(final String tag, final IMealsDelegate IMealsDelegate) {
        return new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getMeals() != null) {
                    Log.d("RemoteRepository", tag + " success with " + response.body().getMeals().size() + " meals.");
                    IMealsDelegate.onSuccessResultMeal(response.body().getMeals());
                } else {
                    Log.d("RemoteRepository", tag + " no results found.");
                    IMealsDelegate.onFailureResult();
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.e("RemoteRepository", tag + " failed: " + t.getMessage());
                IMealsDelegate.onFailureResult();
            }
        };
    }

    private Callback<IngredientResponse> createIngredientCallback(final String tag, final IIngredientsDelegate ingredientsDelegate) {
        return new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getIngredients() != null) {
                    ingredientsDelegate.onSuccessResultIngredients(response.body().getIngredients());
                } else {
                    ingredientsDelegate.onFailureResult();
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                ingredientsDelegate.onFailureResult();
            }
        };
    }

    private Callback<CategoryResponse> createCategoryCallback(final String tag, final ICategoriesDelegate categoriesDelegate) {
        return new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCategories() != null) {
                    categoriesDelegate.onSuccessResultCategories(response.body().getCategories());
                } else {
                    categoriesDelegate.onFailureResult();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                categoriesDelegate.onFailureResult();
            }
        };
    }

    private Callback<CountryResponse> createCountryCallback(final String tag, final ICountriesDelegate countriesDelegate) {
        return new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCountries() != null) {
                    countriesDelegate.onSuccessResultCategories(response.body().getCountries());
                } else {
                    countriesDelegate.onFailureResult();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                countriesDelegate.onFailureResult();
            }
        };
    }
}
