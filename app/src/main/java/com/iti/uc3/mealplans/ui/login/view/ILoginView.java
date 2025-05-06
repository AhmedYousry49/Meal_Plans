package com.iti.uc3.mealplans.ui.login.view;


import com.iti.uc3.mealplans.ui.base.IBaseView;

public interface ILoginView extends IBaseView {
    void onLoginSuccess(String message);
    void onLoginError(String error);
    void showProgress();
    void hideProgress();
}
