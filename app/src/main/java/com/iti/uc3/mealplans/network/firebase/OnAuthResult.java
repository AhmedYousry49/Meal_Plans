package com.iti.uc3.mealplans.network.firebase;

public interface OnAuthResult {
    void onSuccess(String message);
    void onFailure(String error);
}
