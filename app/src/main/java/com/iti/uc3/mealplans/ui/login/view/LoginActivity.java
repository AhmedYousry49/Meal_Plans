package com.iti.uc3.mealplans.ui.login.view;

import android.content.Intent;
import android.credentials.Credential;
import android.credentials.GetCredentialException;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import static com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CustomCredential;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.iti.uc3.mealplans.R;
import com.iti.uc3.mealplans.databinding.ActivityLoginBinding;
import com.iti.uc3.mealplans.network.firebase.AuthModel;
import com.iti.uc3.mealplans.ui.login.presenter.LoginPresenter;
import com.iti.uc3.mealplans.ui.main.view.MainActivity;
import com.iti.uc3.mealplans.ui.register.view.RegisterActivity;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private ActivityLoginBinding binding;
    private LoginPresenter presenter;
    private CallbackManager mCallbackManager;
    private static final String TAG = "LoginActivity";


    private CredentialManager credentialManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());// setContentView(R.layout.activity_login);
        setContentView(binding.getRoot());
        binding.registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });

        binding.googleButton.setOnClickListener(view -> launchCredentialManager());






        presenter =new LoginPresenter(this, this);
        binding.loginButton.setOnClickListener(v -> login());



        credentialManager = CredentialManager.create(this.getBaseContext().getApplicationContext());
        launchCredentialManager();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton)binding.facebookButton;
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
             //   handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

    }

    private void launchCredentialManager() {
        // [START create_credential_manager_request]
        // Instantiate a Google sign-in request
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(getString(R.string.default_web_client_id))
                .build();

        // Create the Credential Manager request
        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();
        // [END create_credential_manager_request]

        // Launch Credential Manager UI
        credentialManager.getCredentialAsync(
                getBaseContext(),
                request,
                new CancellationSignal(),
                Executors.newSingleThreadExecutor(),
                new CredentialManagerCallback<>() {
                    @Override
                    public void onError(@NonNull androidx.credentials.exceptions.GetCredentialException e) {
                        Log.e(TAG, "Couldn't retrieve user's credentials: " + e.getLocalizedMessage());

                    }

                    @Override
                    public void onResult(GetCredentialResponse result) {
                        // Extract credential from the result returned by Credential Manager
                        handleSignIn(result.getCredential());
                    }


                }
        );
    }


    private void handleSignIn(androidx.credentials.Credential credential) {
        // Check if credential is of type Google ID



        if (credential instanceof CustomCredential && credential.getType().equals(TYPE_GOOGLE_ID_TOKEN_CREDENTIAL)) {

            CustomCredential customCredential = (CustomCredential) credential;
            // Create Google ID Token
            Bundle credentialData = customCredential.getData();
            GoogleIdTokenCredential googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credentialData);

            // Sign in to Firebase with using the token
            firebaseAuthWithGoogle(googleIdTokenCredential.getIdToken());
        } else {
            Log.w(TAG, "Credential is not of type Google ID!");
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        AuthModel.getInstance().auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = AuthModel.getInstance().auth.getCurrentUser();

                    } else {
                        // If sign in fails, display a message to the user
                        Log.w(TAG, "signInWithCredential:failure", task.getException());

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.startMonitoring();
        FacebookSdk.sdkInitialize(getApplicationContext());
       // AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopMonitoring();
    }


    void login() {
        String email = binding.emailInput.getText().toString();
        String password = binding.passwordInput.getText().toString();

        presenter.login(email, password);

        //      Toast.makeText(LoginActivity.this, "Login Attempted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        binding.loginloading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.loginloading.setVisibility(View.GONE);
    }
    @Override
    public void showOnlineStatus(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOfflineStatus(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class Builder {
    }
}