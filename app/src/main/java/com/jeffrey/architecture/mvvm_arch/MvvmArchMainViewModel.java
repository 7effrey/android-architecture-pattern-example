package com.jeffrey.architecture.mvvm_arch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jeffrey.architecture.model.Calculator;

public class MvvmArchMainViewModel extends ViewModel implements MvvmArchMainContract.ViewModel {

    private MutableLiveData<String> result = new MutableLiveData<>();
    private MutableLiveData<Void> clear = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public Calculator calculator;

    @Override
    public LiveData<String> getResult() {
        return result;
    }

    @Override
    public LiveData<Void> getClear() {
        return clear;
    }

    @Override
    public MutableLiveData<String> getError() {
        return error;
    }

    @Override
    public void calculatePlus(String num1, String num2) {
        clear.postValue(null);
        try {
            calculator.setNumbers(num1, num2);
            result.postValue(String.valueOf(calculator.calculatePlus()));
        } catch (NumberFormatException nfe) {
            error.postValue(nfe.getMessage());
        }
    }

    @Override
    public void calculateMinus(String num1, String num2) {
        clear.postValue(null);
        try {
            calculator.setNumbers(num1, num2);
            result.postValue(String.valueOf(calculator.calculateMinus()));
        } catch (NumberFormatException nfe) {
            error.postValue(nfe.getMessage());
        }
    }
}
