package com.jeffrey.architecture.mvvm;

import com.jeffrey.architecture.model.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MvvmMainViewModelTest {

    @Mock Calculator calculator;

    private MvvmMainContract.ViewModel viewModel;

    private TestObserver<Integer> obsResult;
    private TestObserver obsClear;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new MvvmMainViewModel(calculator);

        obsResult = new TestObserver<>();
        obsClear = new TestObserver();
        viewModel.getClear().subscribe(obsClear);
        viewModel.getShowResult().subscribe(obsResult);
    }

    @Test
    public void calculatePlus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        int result = 7;
        when(calculator.calculatePlus()).thenReturn(result);

        String num1 = "3";
        String num2 = "4";
        viewModel.calculatePlus(num1, num2);
        obsClear.assertValue(true);
        verify(calculator).setNumbers(num1, num2);
        obsResult.assertValue(result);
    }

    @Test
    public void calculatePlus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        NumberFormatException nfe = new NumberFormatException(errorMessage);
        doThrow(nfe).when(calculator).setNumbers(num1, num2);
        viewModel.calculatePlus(num1, num2);
        obsClear.assertValue(true);
        verify(calculator).setNumbers(num1, num2);
        obsResult.assertNoValues();
        obsResult.assertError(nfe);
    }

    @Test
    public void calculateMinus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        String num1 = "10";
        String num2 = "4";
        int result = 6;
        when(calculator.calculateMinus()).thenReturn(result);
        viewModel.calculateMinus(num1, num2);
        obsClear.assertValue(true);
        verify(calculator).setNumbers(num1, num2);
        obsResult.assertValue(result);
    }

    @Test
    public void calculateMinus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        NumberFormatException nfe = new NumberFormatException(errorMessage);
        doThrow(nfe).when(calculator).setNumbers(num1, num2);
        viewModel.calculateMinus(num1, num2);
        obsClear.assertValue(true);
        verify(calculator).setNumbers(num1, num2);
        obsResult.assertNoValues();
        obsResult.assertError(nfe);
    }
}
