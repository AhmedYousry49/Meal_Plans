package com.iti.uc3.mealplans.ui.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;

import androidx.annotation.NonNull;

import com.iti.uc3.mealplans.network.NetworkMonitor;
import com.iti.uc3.mealplans.network.firebase.OnAuthResult;


public abstract class BasePresenter implements OnAuthResult {

    public final Context context;
    NetworkMonitor networkMonitor;


    protected abstract void onNetworkAvailable(String message);

    protected abstract void onNetworkLost(String message);
    ConnectivityManager.NetworkCallback networkCallback;

    public BasePresenter(Context context) {
        this.context = context;
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                onNetworkAvailable("Online");
            }
            @Override
            public void onLost(@NonNull Network network) {
                onNetworkLost("offline");
            }
        };
    }

    public void startMonitoring() {

        NetworkMonitor.getInstance(context).registerNetworkCallback(networkCallback);

    }

    public void stopMonitoring() {
        if (networkCallback != null) {
            NetworkMonitor.getInstance(context).unregisterNetworkCallback(networkCallback);
        }
    }
}