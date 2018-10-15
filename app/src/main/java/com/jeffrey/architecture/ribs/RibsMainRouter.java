package com.jeffrey.architecture.ribs;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link RibsMainBuilder.RibsMainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class RibsMainRouter extends
    ViewRouter<RibsMainView, RibsMainInteractor, RibsMainBuilder.Component> {

  public RibsMainRouter(
      RibsMainView view,
      RibsMainInteractor interactor,
      RibsMainBuilder.Component component) {
    super(view, interactor, component);
  }
}
