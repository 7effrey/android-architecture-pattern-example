package com.jeffrey.architecture.mvp;

import com.jeffrey.architecture.model.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MvpMainPresenterTest {

    @Mock MvpMainContract.View view;
    @Mock Calculator calculator;

    private MvpMainContract.Presenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MvpMainPresenter(view, calculator);
    }

    @Test
    public void calculatePlus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        int result = 7;
        when(calculator.calculatePlus()).thenReturn(result);

        String num1 = "3";
        String num2 = "4";
        presenter.calculatePlus(num1, num2);
        verify(view).clear();
        verify(calculator).setNumbers(num1, num2);
        verify(view).showResult(result);
    }

    @Test
    public void calculatePlus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        doThrow(new NumberFormatException(errorMessage)).when(calculator).setNumbers(num1, num2);
        presenter.calculatePlus(num1, num2);
        verify(view).clear();
        verify(view, never()).showResult(any(Integer.class));
        verify(view).showError(any(String.class));
    }

    @Test
    public void calculateMinus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        String num1 = "10";
        String num2 = "4";
        int result = 6;
        when(calculator.calculateMinus()).thenReturn(result);
        presenter.calculateMinus(num1, num2);
        verify(view).clear();
        verify(calculator).setNumbers(num1, num2);
        verify(view).showResult(result);
    }

    @Test
    public void calculateMinus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        doThrow(new NumberFormatException(errorMessage)).when(calculator).setNumbers(num1, num2);
        presenter.calculateMinus(num1, num2);
        verify(view).clear();
        verify(view, never()).showResult(any(Integer.class));
        verify(view).showError(any(String.class));
    }
}
