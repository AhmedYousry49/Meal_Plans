package com.iti.uc3.mealplans.ui.login.presenter;

import android.content.Context;

import com.iti.uc3.mealplans.network.firebase.AuthModel;
import com.iti.uc3.mealplans.ui.base.BasePresenter;
import com.iti.uc3.mealplans.ui.login.view.ILoginView;


public class LoginPresenter extends BasePresenter {


    private final ILoginView view;
    private final AuthModel model;
    public LoginPresenter(ILoginView view, Context context) {
        super(context);
        this.view = view;
        this.model = AuthModel.getInstance();
    }
    public void login(String email, String password) {

        view.showProgress();
        model.login(email, password, this);
    }


    @Override
    protected void onNetworkAvailable(String message) {
        view.showOnlineStatus(message);

    }

    @Override
    protected void onNetworkLost(String message) {
        view.showOfflineStatus(message);
    }

    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.onLoginSuccess(message);

    }
    @Override
    public void onFailure(String error) {
        view.hideProgress();
        view.onLoginError(error);

    }
}
