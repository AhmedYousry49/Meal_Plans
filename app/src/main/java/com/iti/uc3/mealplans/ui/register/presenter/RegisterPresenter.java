package com.iti.uc3.mealplans.ui.register.presenter;

import android.content.Context;

import com.iti.uc3.mealplans.network.firebase.AuthModel;
import com.iti.uc3.mealplans.ui.base.BasePresenter;
import com.iti.uc3.mealplans.ui.register.view.IRegisterView;


public class RegisterPresenter extends BasePresenter {


    private final AuthModel model;
    private final IRegisterView view;
    public RegisterPresenter(IRegisterView view, Context context) {
        super(context);
        this.view = view;
        this.model = AuthModel.getInstance();
    }
    public void register(String email,String username, String password ) {
        view.showProgress();
        model.register(email,username, password, this);
    }

    @Override
    protected void onNetworkAvailable(String message) {
        view.showOnlineStatus( message);

    }

    @Override
    protected void onNetworkLost(String message) {
        view.showOfflineStatus(message);
    }


    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.onRegisterSuccess(message);
    }
    @Override
    public void onFailure(String error) {
        view.hideProgress();
        view.onRegisterError(error);
    }
}
