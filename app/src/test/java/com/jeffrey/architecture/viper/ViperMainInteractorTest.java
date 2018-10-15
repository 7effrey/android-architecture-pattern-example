package com.jeffrey.architecture.viper;

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

public class ViperMainInteractorTest {

    @Mock Calculator calculator;

    @Mock ViperMainContract.InteractorOutput interactorOutput;

    private ViperMainContract.Interactor interactor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        interactor = new ViperMainInteractor(calculator);
        interactor.setInteractorOutput(interactorOutput);
    }

    @Test
    public void calculatePlus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        int result = 7;
        when(calculator.calculatePlus()).thenReturn(result);

        String num1 = "3";
        String num2 = "4";
        interactor.calculatePlus(num1, num2);
        verify(calculator).setNumbers(num1, num2);
        verify(interactorOutput).onCalculateSuccess(result);
    }

    @Test
    public void calculatePlus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        doThrow(new NumberFormatException(errorMessage)).when(calculator).setNumbers(num1, num2);
        interactor.calculatePlus(num1, num2);
        verify(interactorOutput, never()).onCalculateSuccess(any(Integer.class));
        verify(interactorOutput).onCalculateFailed(any(String.class));
    }

    @Test
    public void calculateMinus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        String num1 = "10";
        String num2 = "4";
        int result = 6;
        when(calculator.calculateMinus()).thenReturn(result);
        interactor.calculateMinus(num1, num2);
        verify(calculator).setNumbers(num1, num2);
        verify(interactorOutput).onCalculateSuccess(result);
    }

    @Test
    public void calculateMinus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        doThrow(new NumberFormatException(errorMessage)).when(calculator).setNumbers(num1, num2);
        interactor.calculateMinus(num1, num2);
        verify(interactorOutput, never()).onCalculateSuccess(any(Integer.class));
        verify(interactorOutput).onCalculateFailed(any(String.class));
    }
}
