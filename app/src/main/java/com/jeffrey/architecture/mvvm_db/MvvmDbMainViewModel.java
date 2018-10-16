package com.jeffrey.architecture.mvvm_db;

import android.databinding.ObservableField;
import android.support.annotation.VisibleForTesting;

import com.jeffrey.architecture.model.Calculator;

public class MvvmDbMainViewModel implements MvvmDbMainContract.ViewModel {

    private final ObservableField<String> result;
    private final ObservableField<String> error;
    private ObservableField<String> number1;
    private ObservableField<String> number2;

    private final Calculator calculator;

    public MvvmDbMainViewModel(Calculator calculator) {
        this.result = new ObservableField<>("");
        this.error = new ObservableField<>("");
        this.number1 = new ObservableField<>("");
        this.number2 = new ObservableField<>("");
        this.calculator = calculator;
    }

    @Override
    public ObservableField<String> getResult() {
        return result;
    }

    @Override
    public ObservableField<String> getError() {
        return error;
    }

    @Override
    public ObservableField<String> getNumber1() {
        return number1;
    }

    @Override
    public ObservableField<String> getNumber2() {
        return number2;
    }

    @Override
    public void calculatePlus() {
        clear();
        try {
            calculator.setNumbers(number1.get(), number2.get());
            result.set(String.valueOf(calculator.calculatePlus()));
        } catch (NumberFormatException nfe) {
            error.set(nfe.getMessage());
        }
    }

    @Override
    public void calculateMinus() {
        clear();
        try {
            calculator.setNumbers(number1.get(), number2.get());
            result.set(String.valueOf(calculator.calculateMinus()));
        } catch (NumberFormatException nfe) {
            error.set(nfe.getMessage());
        }
    }

    @VisibleForTesting
    protected void clear() {
        result.set("");
        error.set("");
    }
}
