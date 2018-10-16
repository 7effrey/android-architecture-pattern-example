package com.jeffrey.architecture.mvvm_db;

import com.jeffrey.architecture.model.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

public class MvvmDbMainViewModelTest {

    @Mock
    Calculator calculator;

    private MvvmDbMainContract.ViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new MvvmDbMainViewModel(calculator);
    }

    @Test
    public void calculatePlus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        int result = 7;
        when(calculator.calculatePlus()).thenReturn(result);

        String num1 = "3";
        String num2 = "4";
        viewModel.getNumber1().set(num1);
        viewModel.getNumber2().set(num2);
        viewModel.calculatePlus();
        verify(calculator).setNumbers(num1, num2);
        assertEquals(viewModel.getResult().get(), String.valueOf(result));
    }

    @Test
    public void calculatePlus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        NumberFormatException nfe = new NumberFormatException(errorMessage);
        doThrow(nfe).when(calculator).setNumbers(num1, num2);

        viewModel.getNumber1().set(num1);
        viewModel.getNumber2().set(num2);
        viewModel.calculatePlus();
        verify(calculator).setNumbers(num1, num2);
        assertEquals(viewModel.getError().get(), errorMessage);
    }

    @Test
    public void calculateMinus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        String num1 = "10";
        String num2 = "4";
        int result = 6;
        when(calculator.calculateMinus()).thenReturn(result);

        viewModel.getNumber1().set(num1);
        viewModel.getNumber2().set(num2);
        viewModel.calculateMinus();
        verify(calculator).setNumbers(num1, num2);
        assertEquals(viewModel.getResult().get(), String.valueOf(result));
    }

    @Test
    public void calculateMinus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        NumberFormatException nfe = new NumberFormatException(errorMessage);
        doThrow(nfe).when(calculator).setNumbers(num1, num2);

        viewModel.getNumber1().set(num1);
        viewModel.getNumber2().set(num2);
        viewModel.calculateMinus();
        verify(calculator).setNumbers(num1, num2);
        assertEquals(viewModel.getError().get(), errorMessage);
    }

    @Test
    public void clear_shouldPass() {
        MvvmDbMainViewModel vm = (MvvmDbMainViewModel) viewModel;
        vm.clear();
        assertEquals(viewModel.getResult().get(), "");
        assertEquals(viewModel.getError().get(), "");
    }
}
