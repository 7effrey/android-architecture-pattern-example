package com.jeffrey.architecture.mvvm;

import com.jeffrey.architecture.model.Calculator;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MvvmMainViewModel implements MvvmMainContract.ViewModel {

    private PublishSubject<Integer> showResult = PublishSubject.create();
    private PublishSubject clear = PublishSubject.create();

    private final Calculator calculator;

    public MvvmMainViewModel(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Observable<Integer> getShowResult() {
        return showResult;
    }

    @Override
    public Observable<Void> getClear() {
        return clear;
    }

    @Override
    public void calculatePlus(String num1, String num2) {
        clear.onNext(true);
        try {
            calculator.setNumbers(num1, num2);
            showResult.onNext(calculator.calculatePlus());
        } catch (NumberFormatException nfe) {
            showResult.onError(nfe);
        }
    }

    @Override
    public void calculateMinus(String num1, String num2) {
        clear.onNext(true);
        try {
            calculator.setNumbers(num1, num2);
            showResult.onNext(calculator.calculateMinus());
        } catch (NumberFormatException nfe) {
            showResult.onError(nfe);
        }
    }
}
