package com.jeffrey.architecture.mvvm_arch;

import androidx.lifecycle.LiveData;

public interface MvvmArchMainContract {
    interface ViewModel {
        LiveData<String> getResult();
        LiveData<String> getError();
        LiveData<Void> getClear();

        void calculatePlus(String num1, String num2);
        void calculateMinus(String num1, String num2);
    }
}
