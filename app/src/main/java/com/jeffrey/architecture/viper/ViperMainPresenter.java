package com.jeffrey.architecture.viper;

public class ViperMainPresenter implements ViperMainContract.Presenter, ViperMainContract.InteractorOutput {

    private final ViperMainContract.View view;
    private final ViperMainContract.Interactor interactor;
    private final ViperMainContract.Router router;

    public ViperMainPresenter(ViperMainContract.View view,
                              ViperMainContract.Interactor interactor,
                              ViperMainContract.Router router) {
        this.view = view;
        this.interactor = interactor;
        this.interactor.setInteractorOutput(this);
        this.router = router;
    }

    @Override
    public void onPlusButtonClicked(String num1, String num2) {
        view.clear();
        interactor.calculatePlus(num1, num2);
    }

    @Override
    public void onMinusButtonClicked(String num1, String num2) {
        view.clear();
        interactor.calculateMinus(num1, num2);
    }

    @Override
    public void onCalculateSuccess(int result) {
        view.showResult(result);
    }

    @Override
    public void onCalculateFailed(String error) {
        view.showError(error);
    }
}
