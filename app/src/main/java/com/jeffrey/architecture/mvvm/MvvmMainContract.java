package com.jeffrey.architecture.mvvm;

import io.reactivex.Observable;

public interface MvvmMainContract {
    interface ViewModel {
        Observable<Integer> getShowResult();
        Observable getClear();

        void calculatePlus(String num1, String num2);
        void calculateMinus(String num1, String num2);
    }
}
