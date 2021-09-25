package com.jeffrey.architecture.ribs;

import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.jeffrey.architecture.model.Calculator;
import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Coordinates Business Logic for {@link RibsMainBuilder.RibsMainScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class RibsMainInteractor
    extends Interactor<RibsMainInteractor.RibsMainPresenter, RibsMainRouter> {

  @Inject RibsMainPresenter presenter;
  @Inject Calculator calculator;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    presenter.calculatePlus()
            .subscribe(pair -> {
              presenter.clear();
              try {
                calculator.setNumbers(pair.first, pair.second);
                presenter.showResult(calculator.calculatePlus());
              } catch (NumberFormatException nfe) {
                presenter.showError(nfe.getMessage());
              }
            });

    presenter.calculateMinus()
            .subscribe(pair -> {
              presenter.clear();
              try {
                calculator.setNumbers(pair.first, pair.second);
                presenter.showResult(calculator.calculateMinus());
              } catch (NumberFormatException nfe) {
                presenter.showError(nfe.getMessage());
              }
            });
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface RibsMainPresenter {
    Observable<Pair<String, String>> calculatePlus();
    Observable<Pair<String, String>> calculateMinus();
    void clear();
    void showResult(int result);
    void showError(String error);
  }
}
