package com.iti.uc3.mealplans.network.firebase;

import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class AuthModel {
    public final FirebaseAuth auth;
    private static AuthModel INSTANCE;

    public static AuthModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthModel();
        }
        return INSTANCE;
    }

    private AuthModel() {
        auth = FirebaseAuth.getInstance();
    }

    public void login(String email, String password, OnAuthResult callback) {
         if (!isEmailValid(email)) {
            callback.onFailure("Invalid email");

        } else if (!isPasswordValid(password)) {
            callback.onFailure("Password must be at least 6 characters");
        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            callback.onSuccess("Login successful!");
                        } else {
                            callback.onFailure("Login failed: " + Objects.requireNonNull(task.getException()).getMessage());
                        }
                    });
        }
    }

    public void logout() {
        auth.signOut();
    }



    public void resetpassword(String email, OnAuthResult callback) {

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess("Reset password successful!");
            } else {
                callback.onFailure("Reset password failed: " + Objects.requireNonNull(task.getException()).getMessage());
            }
            ;
        });


    }

    public boolean isLoggedIn() {
        return auth.getCurrentUser() != null;
    }

    public String getCurrentUserId() {
        return Objects.requireNonNull(auth.getCurrentUser()).getUid();
    }

    public String getCurrentUserName() {
        return Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
    }

    public String getCurrentUserPhotoUrl() {
        return Objects.requireNonNull(Objects.requireNonNull(auth.getCurrentUser()).getPhotoUrl()).toString();
    }


    public String getCurrentUserEmail() {
        return Objects.requireNonNull(auth.getCurrentUser()).getEmail();
    }


    public static boolean isEmailValid(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isUserNameValid(String UserName) {
        return UserName != null && UserName.trim().length() > 5;
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }



//    private void handleFacebookAccessToken(AccessToken token) {
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(c, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//
//                            FirebaseUser user = auth.getCurrentUser();
//                        } else {
//                            // If sign in fails, display a message to the user.
//
//                        }
//                    }
//                });
//    }
    public void register(String email, String UserName, String password, OnAuthResult callback) {
        if (!isUserNameValid(UserName)) {
            callback.onFailure("Invalid UserName");
        } else if (!isEmailValid(email)) {
            callback.onFailure("Invalid email");

        } else if (!isPasswordValid(password)) {
            callback.onFailure("Password must be at least 6 characters");
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            auth.getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(UserName).build());
                            callback.onSuccess("Registration successful!");

                        } else {
                            callback.onFailure("Registration failed: " + Objects.requireNonNull(task.getException()).getMessage());
                        }
                    });
        }
    }


}
