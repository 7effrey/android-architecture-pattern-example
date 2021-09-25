package com.jeffrey.architecture.ribs;

import androidx.core.util.Pair;

import com.jeffrey.architecture.model.Calculator;
import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.core.InteractorHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RibsMainInteractorTest extends RibTestBasePlaceholder {

  @Mock RibsMainInteractor.RibsMainPresenter presenter;
  @Mock RibsMainRouter router;
  Calculator calculator;

  private RibsMainInteractor interactor;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    calculator = new Calculator();
    interactor = TestRibsMainInteractor.create(presenter, calculator);
  }

  @Test
  public void attach_whenViewEmitsPlus_shouldPass() {
    when(presenter.calculatePlus()).thenReturn(Observable.just(Pair.create("2", "3")));
    when(presenter.calculateMinus()).thenReturn(Observable.just(Pair.create("", "")));
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach(interactor, presenter, router, null);
    verify(presenter).showResult(5);
    InteractorHelper.detach(interactor);
  }

  @Test
  public void attach_whenViewEmitsMinus_shouldPass() {
    when(presenter.calculatePlus()).thenReturn(Observable.just(Pair.create("", "")));
    when(presenter.calculateMinus()).thenReturn(Observable.just(Pair.create("2", "3")));
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach(interactor, presenter, router, null);
    verify(presenter).showResult(-1);
    InteractorHelper.detach(interactor);
  }

  @Test
  public void attach_whenViewEmitsPlusExceedIntegerLimit_shouldNotPass() {
    when(presenter.calculatePlus()).thenReturn(Observable.just(Pair.create("221312312312321312321321", "3")));
    when(presenter.calculateMinus()).thenReturn(Observable.just(Pair.create("2", "3")));
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach(interactor, presenter, router, null);
    verify(presenter).showError(any(String.class));
    InteractorHelper.detach(interactor);
  }

  @Test
  public void attach_whenViewEmitsMinusExceedIntegerLimit_shouldNotPass() {
    when(presenter.calculatePlus()).thenReturn(Observable.just(Pair.create("3", "2")));
    when(presenter.calculateMinus()).thenReturn(Observable.just(Pair.create("21421412421421422142112412", "3")));
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach(interactor, presenter, router, null);
    verify(presenter).showError(any(String.class));
    InteractorHelper.detach(interactor);
  }

}
