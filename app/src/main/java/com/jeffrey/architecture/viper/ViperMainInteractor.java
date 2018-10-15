package com.jeffrey.architecture.viper;

import com.jeffrey.architecture.model.Calculator;

public class ViperMainInteractor implements ViperMainContract.Interactor {

    private ViperMainContract.InteractorOutput interactorOutput;
    private final Calculator calculator;

    public ViperMainInteractor(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void setInteractorOutput(ViperMainContract.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void calculatePlus(String num1, String num2) {
        try {
            calculator.setNumbers(num1, num2);
            if (interactorOutput != null)
                interactorOutput.onCalculateSuccess(calculator.calculatePlus());
        } catch (NumberFormatException nfe) {
            if (interactorOutput != null)
                interactorOutput.onCalculateFailed(nfe.getMessage());
        }
    }

    @Override
    public void calculateMinus(String num1, String num2) {
        try {
            calculator.setNumbers(num1, num2);
            if (interactorOutput != null)
                interactorOutput.onCalculateSuccess(calculator.calculateMinus());
        } catch (NumberFormatException nfe) {
            if (interactorOutput != null)
                interactorOutput.onCalculateFailed(nfe.getMessage());
        }
    }
}
