package com.jeffrey.architecture.mvvm_db;

import androidx.databinding.ObservableField;

public interface MvvmDbMainContract {
    interface ViewModel {
        ObservableField<String> getResult();
        ObservableField<String> getError();
        ObservableField<String> getNumber1();
        ObservableField<String> getNumber2();

        void calculatePlus();
        void calculateMinus();
    }
}
