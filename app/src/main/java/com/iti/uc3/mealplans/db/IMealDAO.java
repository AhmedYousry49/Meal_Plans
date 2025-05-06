package com.iti.uc3.mealplans.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.iti.uc3.mealplans.model.Meal;

import java.util.List;



@Dao
public interface IMealDAO {
        @Query("SELECT * From Meal")
        LiveData<List<Meal>> getAllMeals();

//    @Query("SELECT * FROM singleMeal WHERE name LIKE :first " + "LIMIT 1")
//    Meal findMealByName(String first);



        @Update
        void update(Meal meal);

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Meal meal);

        @Delete
        void delete(Meal meal);
    }