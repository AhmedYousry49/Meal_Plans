

package com.iti.uc3.mealplans.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import java.util.ArrayList;
import java.util.List;

public class NetworkMonitor {
    private static NetworkMonitor INSTANCE;
    private final ConnectivityManager connectivityManager;
    private final List<ConnectivityManager.NetworkCallback> callbacks = new ArrayList<>();

    private NetworkMonitor(Context context) {
        this.connectivityManager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public static synchronized NetworkMonitor getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NetworkMonitor(context.getApplicationContext());
        }
        return INSTANCE;
    }
    public boolean isNetworkAvailable() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            android.net.Network network = connectivityManager.getActiveNetwork();
            if (network == null) return false;

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        } else {
            android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
    public boolean isNetworkAvailable(Context context) {
       return getInstance(context).isNetworkAvailable();
    }
    public void registerNetworkCallback(ConnectivityManager.NetworkCallback callback) {
        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        connectivityManager.registerNetworkCallback(networkRequest, callback);
        callbacks.add(callback);
    }

    public void unregisterNetworkCallback(ConnectivityManager.NetworkCallback callback) {
        connectivityManager.unregisterNetworkCallback(callback);
        callbacks.remove(callback);
    }
}
