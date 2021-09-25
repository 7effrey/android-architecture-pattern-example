package com.jeffrey.architecture.ribs;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jeffrey.architecture.R;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link RibsMainBuilder.RibsMainScope}.
 */
public class RibsMainView extends CoordinatorLayout implements RibsMainInteractor.RibsMainPresenter {

  private EditText etNum1;
  private EditText etNum2;
  private EditText etResult;
  private TextView tvError;

  public RibsMainView(Context context) {
    this(context, null);
  }

  public RibsMainView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RibsMainView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    etNum1 = findViewById(R.id.et_num1);
    etNum2 = findViewById(R.id.et_num2);
    etResult = findViewById(R.id.et_result);
    tvError = findViewById(R.id.tv_error);
  }

  @Override
  public Observable<Pair<String, String>> calculatePlus() {
    return RxView.clicks(findViewById(R.id.btn_plus))
            .map(new Function<Object, Pair<String,String>>() {
              @Override
              public Pair<String, String> apply(Object o) throws Exception {
                return new Pair<>(etNum1.getText().toString(), etNum2.getText().toString());
              }
            });
  }

  @Override
  public Observable<Pair<String, String>> calculateMinus() {
    return RxView.clicks(findViewById(R.id.btn_minus))
            .map(new Function<Object, Pair<String,String>>() {
              @Override
              public Pair<String, String> apply(Object o) throws Exception {
                return new Pair<>(etNum1.getText().toString(), etNum2.getText().toString());
              }
            });
  }

  @Override
  public void clear() {
    etResult.setText("");
    tvError.setText("");
  }

  @Override
  public void showResult(int result) {
    etResult.setText(String.valueOf(result));
  }

  @Override
  public void showError(String error) {
    tvError.setText(error);
  }
}
