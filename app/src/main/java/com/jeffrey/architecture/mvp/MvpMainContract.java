package com.jeffrey.architecture.mvp;

public interface MvpMainContract {
    interface View {
        void clear();
        void showResult(int result);
        void showError(String error);
        void goToNextScreen();
    }

    interface Presenter {
        void calculatePlus(String num1, String num2);
        void calculateMinus(String num1, String num2);
    }
}
