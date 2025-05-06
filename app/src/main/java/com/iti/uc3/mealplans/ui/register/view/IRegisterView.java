package com.iti.uc3.mealplans.ui.register.view;

import com.iti.uc3.mealplans.ui.base.IBaseView;

public interface IRegisterView extends IBaseView {

    void onRegisterSuccess(String message);
    void onRegisterError(String error);
    void showProgress();
    void hideProgress();



}
