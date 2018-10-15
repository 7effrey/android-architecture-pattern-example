package com.jeffrey.architecture.viper;

public interface ViperMainContract {
    interface View {
        void clear();
        void showResult(int result);
        void showError(String error);
    }

    interface Router {
        void goToNextScreen();
    }

    interface Presenter {
        void onPlusButtonClicked(String num1, String num2);
        void onMinusButtonClicked(String num1, String num2);
    }

    interface Interactor {
        void calculatePlus(String num1, String num2);
        void calculateMinus(String num1, String num2);
        void setInteractorOutput(InteractorOutput interactorOutput);
    }

    interface InteractorOutput {
        void onCalculateSuccess(int result);
        void onCalculateFailed(String error);
    }
}
