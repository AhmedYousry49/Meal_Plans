package com.iti.uc3.mealplans;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import com.iti.uc3.mealplans.network.NetworkMonitor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NetworkMonitorTest {

    @Mock
    Context mockContext;

    @Mock
    ConnectivityManager mockConnectivityManager;

    @Mock
    ConnectivityManager.NetworkCallback mockCallback;

    NetworkMonitor networkMonitor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager);
        networkMonitor = NetworkMonitor.getInstance(mockContext);
    }

    @Test
    public void testIsNetworkAvailable() {
        when(mockConnectivityManager.getActiveNetwork()).thenReturn(null);
        assertFalse(networkMonitor.isNetworkAvailable());

        NetworkCapabilities mockCapabilities = mock(NetworkCapabilities.class);
        when(mockConnectivityManager.getNetworkCapabilities(any())).thenReturn(mockCapabilities);
        when(mockCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)).thenReturn(true);
        assertTrue(networkMonitor.isNetworkAvailable());
    }

    @Test
    public void testRegisterNetworkCallback() {
        NetworkRequest.Builder builder = mock(NetworkRequest.Builder.class);
        NetworkRequest mockRequest = mock(NetworkRequest.class);
        when(builder.build()).thenReturn(mockRequest);

        networkMonitor.registerNetworkCallback(mockCallback);
        verify(mockConnectivityManager).registerNetworkCallback(any(NetworkRequest.class), eq(mockCallback));
    }

    @Test
    public void testUnregisterNetworkCallback() {
        networkMonitor.unregisterNetworkCallback(mockCallback);
        verify(mockConnectivityManager).unregisterNetworkCallback(mockCallback);
    }
}