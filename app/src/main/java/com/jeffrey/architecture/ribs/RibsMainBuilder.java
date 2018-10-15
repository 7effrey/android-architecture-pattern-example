package com.jeffrey.architecture.ribs;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jeffrey.architecture.R;
import com.jeffrey.architecture.model.Calculator;
import com.uber.rib.core.InteractorBaseComponent;
import com.uber.rib.core.ViewBuilder;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Scope;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Builder for the {@link RibsMainScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class RibsMainBuilder
    extends ViewBuilder<RibsMainView, RibsMainRouter, RibsMainBuilder.ParentComponent> {

  public RibsMainBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link RibsMainRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link RibsMainRouter}.
   */
  public RibsMainRouter build(ViewGroup parentViewGroup) {
    RibsMainView view = createView(parentViewGroup);
    RibsMainInteractor interactor = new RibsMainInteractor();
    Component component = DaggerRibsMainBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.ribsMainRouter();
  }

  @Override
  protected RibsMainView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    return (RibsMainView) inflater.inflate(R.layout.rib_main, parentViewGroup, false);
  }

  public interface ParentComponent {

  }

  @dagger.Module
  public abstract static class Module {

    @RibsMainScope
    @Binds
    abstract RibsMainInteractor.RibsMainPresenter presenter(RibsMainView view);

    @RibsMainScope
    @Provides
    static RibsMainRouter router(
      Component component,
      RibsMainView view,
      RibsMainInteractor interactor) {
      return new RibsMainRouter(view, interactor, component);
    }

    @RibsMainScope
    @Provides
    static Calculator calculator() {
      return new Calculator();
    }
  }

  @RibsMainScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<RibsMainInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(RibsMainInteractor interactor);
      @BindsInstance
      Builder view(RibsMainView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    RibsMainRouter ribsMainRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface RibsMainScope { }

  @Qualifier
  @Retention(CLASS)
  @interface RibsMainInternal { }
}
