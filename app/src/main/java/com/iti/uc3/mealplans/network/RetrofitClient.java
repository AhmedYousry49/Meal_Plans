package com.iti.uc3.mealplans.network;


import android.content.Context;


import com.iti.uc3.mealplans.network.dao.IDataDAO;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {



    private static RetrofitClient instance = null;

    //private DataDAO mealApiInterface;

    Retrofit retrofit;
    private static final String BASE_URL = "https://www.themealdb.com/";

    private RetrofitClient(Context context) {

        Cache cache = new Cache(context.getCacheDir(), 100 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
             //   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build();


   ////     mealApiInterface = retrofit.create(DataDAO.class);
    }

    public static synchronized RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }
/*
    public void getMealByName(String name, NetworkDelegate networkDelegate) {
        mealApiInterface.getMealByName(name).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()) {
                    if (response.body().getMeals() != null) {
                        Log.d("TAG", "onResponse: done to get Meal by Name and size equal " + response.body().getMeals().size());
                        networkDelegate.onSuccessResultMeal(response.body().getMeals());
                    } else {
                        networkDelegate.onFailureResult("no Result..");
                    }
                }
                else {
                    networkDelegate.onFailureResult("no falue..");
                }
            }

            @Override
            public void onFailure(Call<MealResponse>  call, Throwable t) {
                Log.d("TAG", "onFailure: failed to get Meal By Name");
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }*/

    public IDataDAO create(Class<IDataDAO> dataDAOClass) {
        return  retrofit.create(dataDAOClass);
    }



/*
    public List<Product> getDataOverNetwork() {
        List<Product> result = new ArrayList<>();

        try {
            Response<ProductResponse> response = apiInterface.getProducts().execute(); // synchronous call

            if (response.isSuccessful() && response.body() != null) {
                result.addAll(response.body().products);
                Log.d("TAG", "getDataOverNetwork Response = " + result.size());
            } else {
                Log.d("TAG", "Response was not successful");
            }
        } catch (IOException e) {
            Log.e("TAG", "getDataOverNetwork Exception: " + e.getMessage());
        }

        return result;
    }

    public void getDataOverNetwork(NetworkDelegate delegate) {

        apiInterface.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                // result.addAll((List<ProductDAO>) response.body().products);
                delegate.onSuccessResult((List<Product>) response.body().products);

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                delegate.onFailureResult(t.toString());


            }
        });

    }

*/




}
