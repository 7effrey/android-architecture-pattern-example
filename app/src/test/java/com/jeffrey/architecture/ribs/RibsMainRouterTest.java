package com.jeffrey.architecture.ribs;

import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.core.RouterHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RibsMainRouterTest extends RibTestBasePlaceholder {

  @Mock RibsMainBuilder.Component component;
  @Mock RibsMainInteractor interactor;
  @Mock RibsMainView view;

  private RibsMainRouter router;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    router = new RibsMainRouter(view, interactor, component);
  }

}
