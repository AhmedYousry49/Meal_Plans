package com.iti.uc3.mealplans.ui.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.iti.uc3.mealplans.R;
import com.iti.uc3.mealplans.databinding.ActivityLoginBinding;
import com.iti.uc3.mealplans.databinding.ActivityRegisterBinding;
import com.iti.uc3.mealplans.ui.login.view.LoginActivity;
import com.iti.uc3.mealplans.ui.register.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {



    private ActivityRegisterBinding binding;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());//        setContentView(R.layout.activity_register);
        setContentView(binding.getRoot());
        presenter = new RegisterPresenter(this, this);
        binding.registerButton.setOnClickListener(v -> register());

    }


    @Override
    public void onRegisterError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        binding.loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        binding.loading.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startMonitoring();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopMonitoring();
    }

    void register() {
        String username = binding.fullNameInput.getText().toString();
        String email = binding.emailInput.getText().toString();
        String password = binding.passwordInput.getText().toString();
        String confirmPassword = binding.confirmPasswordInput.getText().toString();
        // Clear previous errors
        binding.fullNameInputLayout.setError(null);
        binding.emailInputLayout.setError(null);
        binding.passwordInputLayout.setError(null);
        binding.confirmPasswordLayout.setError(null);

        // Validate Full Name
        if (username.isEmpty()) {
            binding.fullNameInputLayout.setError("Full Name cannot be empty");
            return;
        }
        // Validate Email
        if (email.isEmpty()) {
            binding.emailInputLayout.setError("Email cannot be empty");
            return;
        }

        // Validate Password
        if (password.isEmpty() || password.length() < 6) {
            binding.passwordInputLayout.setError("Password must be at least 6 characters");
            return;
        }

        // Validate Confirm Password
        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            binding.confirmPasswordLayout.setError("Passwords do not match");
            return;
        }

        // Validate Confirm Password
        if (!password.equals(confirmPassword)) {
            binding.confirmPasswordLayout.setError("Passwords do not match");
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

            presenter.register(email,username, password);
    }


    @Override
    public void onRegisterSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); //  this removes LoginActivity from the back stack

    }


    @Override
    public void showOnlineStatus(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOfflineStatus(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}