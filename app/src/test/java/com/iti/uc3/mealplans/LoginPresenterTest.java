package com.iti.uc3.mealplans;

import android.content.Context;

import com.iti.uc3.mealplans.network.firebase.AuthModel;
import com.iti.uc3.mealplans.ui.login.presenter.LoginPresenter;
import com.iti.uc3.mealplans.ui.login.view.ILoginView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LoginPresenterTest {

    @Mock
    ILoginView mockView;

    @Mock
    AuthModel mockAuthModel;

    @Mock
    Context mockContext;

    LoginPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockAuthModel.getInstance()).thenReturn(mockAuthModel);
        presenter = new LoginPresenter(mockView, mockContext);
    }

    @Test
    public void testLoginSuccess() {
        doAnswer(invocation -> {
            presenter.onSuccess("Login successful");
            return null;
        }).when(mockAuthModel).login(anyString(), anyString(), eq(presenter));

        presenter.login("test@example.com", "password");

        verify(mockView).showProgress();
        verify(mockView).hideProgress();
        verify(mockView).onLoginSuccess("Login successful");
    }

    @Test
    public void testLoginFailure() {
        doAnswer(invocation -> {
            presenter.onFailure("Login failed");
            return null;
        }).when(mockAuthModel).login(anyString(), anyString(), eq(presenter));

        presenter.login("test@example.com", "password");

        verify(mockView).showProgress();
        verify(mockView).hideProgress();
        verify(mockView).onLoginError("Login failed");
    }
}