package com.iti.uc3.mealplans.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.iti.uc3.mealplans.model.Meal;


@Database(entities = {Meal.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract IMealDAO geFavoriteDAO();

    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "Favorite.db")
                    .build();
        }
        return instance;
    }


}