package com.jeffrey.architecture.mvp;

import com.jeffrey.architecture.model.Calculator;

public class MvpMainPresenter implements MvpMainContract.Presenter {

    private final MvpMainContract.View view;
    private final Calculator calculator;

    public MvpMainPresenter(MvpMainContract.View view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    @Override
    public void calculatePlus(String num1, String num2) {
        view.clear();
        try {
            calculator.setNumbers(num1, num2);
            view.showResult(calculator.calculatePlus());
        } catch (NumberFormatException nfe) {
            view.showError(nfe.getMessage());
        }
    }

    @Override
    public void calculateMinus(String num1, String num2) {
        view.clear();
        try {
            calculator.setNumbers(num1, num2);
            view.showResult(calculator.calculateMinus());
        } catch (NumberFormatException nfe) {
            view.showError(nfe.getMessage());
        }
    }
}
