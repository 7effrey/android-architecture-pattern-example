package com.jeffrey.architecture.mvvm_arch;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.jeffrey.architecture.model.Calculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class MvvmArchMainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule(); // To force tests to be executed synchronously

    @Mock
    Calculator calculator;

    private MvvmArchMainContract.ViewModel viewModel;

    @Mock
    Observer<String> obsResult;

    @Mock
    Observer<Void> obsClear;

    @Mock
    Observer<String> obsError;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MvvmArchMainViewModel vm = new MvvmArchMainViewModel();
        vm.calculator = calculator;
        viewModel = vm;

        viewModel.getClear().observeForever(obsClear);
        viewModel.getResult().observeForever(obsResult);
        viewModel.getError().observeForever(obsError);
    }

    @Test
    public void calculatePlus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        int result = 7;
        when(calculator.calculatePlus()).thenReturn(result);

        String num1 = "3";
        String num2 = "4";
        viewModel.calculatePlus(num1, num2);
        verify(obsClear).onChanged(null);
        verify(calculator).setNumbers(num1, num2);
        verify(obsResult).onChanged(String.valueOf(result));
        verifyZeroInteractions(obsError);
    }

    @Test
    public void calculatePlus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        NumberFormatException nfe = new NumberFormatException(errorMessage);
        doThrow(nfe).when(calculator).setNumbers(num1, num2);
        viewModel.calculatePlus(num1, num2);
        verify(obsClear).onChanged(null);
        verify(calculator).setNumbers(num1, num2);
        verifyZeroInteractions(obsResult);
        verify(obsError).onChanged(errorMessage);
    }

    @Test
    public void calculateMinus_whenGivenTwoValidStringsOfNumber_shouldPass() {
        String num1 = "10";
        String num2 = "4";
        int result = 6;
        when(calculator.calculateMinus()).thenReturn(result);
        viewModel.calculateMinus(num1, num2);
        verify(obsClear).onChanged(null);
        verify(calculator).setNumbers(num1, num2);
        verify(obsResult).onChanged(String.valueOf(result));
        verifyZeroInteractions(obsError);
    }

    @Test
    public void calculateMinus_whenReceiveException_shouldNotPass() {
        String num1 = "312312312312321311231231221";
        String num2 = "4";
        String errorMessage = "Number is out of range.";
        NumberFormatException nfe = new NumberFormatException(errorMessage);
        doThrow(nfe).when(calculator).setNumbers(num1, num2);
        viewModel.calculateMinus(num1, num2);
        verify(obsClear).onChanged(null);
        verify(calculator).setNumbers(num1, num2);
        verifyZeroInteractions(obsResult);
        verify(obsError).onChanged(errorMessage);
    }
}
