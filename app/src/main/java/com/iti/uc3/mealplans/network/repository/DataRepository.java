package com.iti.uc3.mealplans.network.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.iti.uc3.mealplans.db.AppDataBase;
import com.iti.uc3.mealplans.model.Category;
import com.iti.uc3.mealplans.model.Country;
import com.iti.uc3.mealplans.model.Ingredient;
import com.iti.uc3.mealplans.model.Meal;
import com.iti.uc3.mealplans.network.delegate.ICategoriesDelegate;
import com.iti.uc3.mealplans.network.delegate.ICountriesDelegate;
import com.iti.uc3.mealplans.network.delegate.IIngredientsDelegate;
import com.iti.uc3.mealplans.network.delegate.IMealsDelegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRepository {
    private final RemoteRepository remoteRepo;
    private AppDataBase localRepo;
    private final Context context;
    private static DataRepository instance;


    private List<Meal> cachedRandomMeals = null;
    private List<Category> cachedAllCategories = null;
    private List<Ingredient> cachedIngredients = null;
    private List<Country> cachedCountries = null;

    private final Map<String, List<Meal>> cacheByName = new HashMap<>();
    private final Map<String, List<Meal>> cacheByFirstChar = new HashMap<>();
    private final Map<String, List<Meal>> cacheById = new HashMap<>();
    private final Map<String, List<Meal>> cacheByIngredient = new HashMap<>();
    private final Map<String, List<Meal>> cacheByCountry = new HashMap<>();
    private final Map<String, List<Meal>> cacheByCategory = new HashMap<>();


    private DataRepository(Context context) {
        this.context = context;
        remoteRepo = new RemoteRepository(context);
        localRepo =  AppDataBase.getInstance(context);

        getRandomMeal();
        getAllCategories();
        getAllIngredients();
        getAllCountries();


    }

















    public static synchronized DataRepository getInstance(Context context) {

        if (instance == null) {
            instance = new DataRepository(context);
        }
        return instance;
    }


    public LiveData<List<Meal>> getRandomMeal() {
        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        if (cachedRandomMeals != null) {
            data.setValue(cachedRandomMeals);
            return data;
        }

        remoteRepo.getRandomMeal(new IMealsDelegate() {
            @Override
            public void onSuccessResultMeal(List<Meal> meals) {
                cachedRandomMeals = meals;
                data.setValue(meals);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cachedRandomMeals);
            }
        });

        return data;
    }

    public LiveData<List<Meal>> getMealByName(String name) {
        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        if (cacheByName.containsKey(name)) {
            data.setValue(cacheByName.get(name));
            return data;
        }

        remoteRepo.getMealByName(name, new IMealsDelegate() {
            @Override
            public void onSuccessResultMeal(List<Meal> meals) {
                cacheByName.put(name, meals);
                data.setValue(meals);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cacheByName.get(name));
            }
        });

        return data;
    }

    public LiveData<List<Meal>> getMealByFirstChar(String firstChar) {
        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        if (cacheByFirstChar.containsKey(firstChar)) {
            data.setValue(cacheByFirstChar.get(firstChar));
            return data;
        }

        remoteRepo.getMealByFirstChar(firstChar, new IMealsDelegate() {
            @Override
            public void onSuccessResultMeal(List<Meal> meals) {
                cacheByFirstChar.put(firstChar, meals);
                data.setValue(meals);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cacheByFirstChar.get(firstChar));
            }
        });

        return data;
    }

    public LiveData<List<Meal>> getMealById(String id) {
        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        if (cacheById.containsKey(id)) {
            data.setValue(cacheById.get(id));
            return data;
        }

        remoteRepo.getMealById(id, new IMealsDelegate() {
            @Override
            public void onSuccessResultMeal(List<Meal> meals) {
                cacheById.put(id, meals);
                data.setValue(meals);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cacheById.get(id));
            }
        });

        return data;
    }

    public LiveData<List<Meal>> getMealsByIngredient(String ingredient) {
        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        if (cacheByIngredient.containsKey(ingredient)) {
            data.setValue(cacheByIngredient.get(ingredient));
            return data;
        }

        remoteRepo.getMealsByIngredient(ingredient, new IMealsDelegate() {
            @Override
            public void onSuccessResultMeal(List<Meal> meals) {
                cacheByIngredient.put(ingredient, meals);
                data.setValue(meals);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cacheByIngredient.get(ingredient));
            }
        });

        return data;
    }

    public LiveData<List<Meal>> getMealsByCountry(String country) {
        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        if (cacheByCountry.containsKey(country)) {
            data.setValue(cacheByCountry.get(country));
            return data;
        }

        remoteRepo.getMealsByCountry(country, new IMealsDelegate() {
            @Override
            public void onSuccessResultMeal(List<Meal> meals) {
                cacheByCountry.put(country, meals);
                data.setValue(meals);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cacheByCountry.get(country));
            }
        });

        return data;
    }

    public LiveData<List<Meal>> getMealsByCategory(String category) {
        MutableLiveData<List<Meal>> data = new MutableLiveData<>();

        if (cacheByCategory.containsKey(category)) {
            data.setValue(cacheByCategory.get(category));
            return data;
        }

        remoteRepo.getMealsByCategory(category, new IMealsDelegate() {
            @Override
            public void onSuccessResultMeal(List<Meal> meals) {
                cacheByCategory.put(category, meals);
                data.setValue(meals);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cacheByCategory.get(category));
            }
        });

        return data;
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        MutableLiveData<List<Ingredient>> data = new MutableLiveData<>();

        if (cachedIngredients != null) {
            data.setValue(cachedIngredients);
            return data;
        }

        remoteRepo.getAllIngredients(new IIngredientsDelegate() {
            @Override
            public void onSuccessResultIngredients(List<Ingredient> ingredient) {
                cachedIngredients = ingredient;
                data.setValue(ingredient);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cachedIngredients);
            }
        });

        return data;
    }

    public LiveData<List<Country>> getAllCountries() {
        MutableLiveData<List<Country>> data = new MutableLiveData<>();

        if (cachedCountries != null) {
            data.setValue(cachedCountries);
            return data;
        }

        remoteRepo.getAllCountries(new ICountriesDelegate() {
            @Override
            public void onSuccessResultCategories(List<Country> country) {
                cachedCountries = country;
                data.setValue(country);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cachedCountries);
            }
        });

        return data;
    }

    public LiveData<List<Category>> getAllCategories() {
        MutableLiveData<List<Category>> data = new MutableLiveData<>();

        if (cachedAllCategories != null) {
            data.setValue(cachedAllCategories);
            return data;
        }

        remoteRepo.getAllCategories(new ICategoriesDelegate() {
            @Override
            public void onSuccessResultCategories(List<Category> category) {
                cachedAllCategories = category;
                data.setValue(category);
            }

            @Override
            public void onFailureResult() {
                data.setValue(cachedAllCategories);
            }
        });

        return data;
    }
}


