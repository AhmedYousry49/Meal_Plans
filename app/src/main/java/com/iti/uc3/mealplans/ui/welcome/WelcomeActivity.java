package com.iti.uc3.mealplans.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.iti.uc3.mealplans.R;
import com.iti.uc3.mealplans.databinding.ActivityLoginBinding;
import com.iti.uc3.mealplans.databinding.ActivityWelcomeBinding;
import com.iti.uc3.mealplans.network.repository.DataRepository;
import com.iti.uc3.mealplans.ui.login.view.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);

        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.getstartedbtn.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

           ;

        }


    }
