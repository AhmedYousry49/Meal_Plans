package com.iti.uc3.mealplans.ui.main.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.iti.uc3.mealplans.databinding.ActivityMainBinding;
import com.iti.uc3.mealplans.network.repository.DataRepository;
import com.iti.uc3.mealplans.ui.HomeFragment;

public class MainActivity extends AppCompatActivity {


    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());// setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        navigateTo(new HomeFragment(), true);
        Toast.makeText(this, "Welcome "+


                DataRepository.getInstance(this).getRandomMeal().getValue().size(), Toast.LENGTH_SHORT).show();


    }

   public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(binding.navHostFragment.getId(), fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}