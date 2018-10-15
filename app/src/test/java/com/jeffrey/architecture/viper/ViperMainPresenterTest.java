package com.jeffrey.architecture.viper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class ViperMainPresenterTest {

    @Mock
    ViperMainContract.View view;
    @Mock
    ViperMainContract.Interactor interactor;
    @Mock
    ViperMainContract.Router router;

    private ViperMainContract.Presenter presenter;
    private ViperMainContract.InteractorOutput interactorOutput;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ViperMainPresenter mainPresenter = new ViperMainPresenter(view, interactor, router);
        presenter = mainPresenter;
        interactorOutput = mainPresenter;
    }

    @Test
    public void onPlusButtonClicked_whenGivenTwoValidStringsOfNumber_shouldPass() {
        String num1 = "3";
        String num2 = "4";
        presenter.onPlusButtonClicked(num1, num2);
        verify(view).clear();
        verify(interactor).calculatePlus(num1, num2);
    }

    @Test
    public void onMinusButtonClicked_whenGivenTwoValidStringsOfNumber_shouldPass() {
        String num1 = "3";
        String num2 = "4";
        presenter.onMinusButtonClicked(num1, num2);
        verify(view).clear();
        verify(interactor).calculateMinus(num1, num2);
    }

    @Test
    public void onCalculateSuccess_whenGivenResult_shouldPass() {
        interactorOutput.onCalculateSuccess(5);
        verify(view).showResult(any(Integer.class));
    }

    @Test
    public void onCalculateFailed_whenGivenError_shouldPass() {
        interactorOutput.onCalculateFailed("Fatal Error.");
        verify(view).showError(any(String.class));
    }
}
