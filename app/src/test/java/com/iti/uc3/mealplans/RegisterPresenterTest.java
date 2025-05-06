package com.iti.uc3.mealplans;

import android.content.Context;

import com.iti.uc3.mealplans.network.firebase.AuthModel;
import com.iti.uc3.mealplans.ui.register.presenter.RegisterPresenter;
import com.iti.uc3.mealplans.ui.register.view.IRegisterView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RegisterPresenterTest {

    @Mock
    IRegisterView mockView;

    @Mock
    AuthModel mockAuthModel;

    @Mock
    Context mockContext;

    RegisterPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockAuthModel.getInstance()).thenReturn(mockAuthModel);
        presenter = new RegisterPresenter(mockView, mockContext);
    }

    @Test
    public void testRegisterSuccess() {
        doAnswer(invocation -> {
            presenter.onSuccess("Registration successful");
            return null;
        }).when(mockAuthModel).register(anyString(), anyString(), anyString(), eq(presenter));

        presenter.register("test@example.com", "username", "password");

        verify(mockView).showProgress();
        verify(mockView).hideProgress();
        verify(mockView).onRegisterSuccess("Registration successful");
    }

    @Test
    public void testRegisterFailure() {
        doAnswer(invocation -> {
            presenter.onFailure("Registration failed");
            return null;
        }).when(mockAuthModel).register(anyString(), anyString(), anyString(), eq(presenter));

        presenter.register("test@example.com", "username", "password");

        verify(mockView).showProgress();
        verify(mockView).hideProgress();
        verify(mockView).onRegisterError("Registration failed");
    }
}