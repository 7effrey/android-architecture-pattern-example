package com.jeffrey.architecture.viper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jeffrey.architecture.R;
import com.jeffrey.architecture.model.Calculator;

public class ViperMainActivity extends AppCompatActivity implements ViperMainContract.View {

    private EditText etNum1;
    private EditText etNum2;
    private EditText etResult;
    private TextView tvError;

    private ViperMainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();

        Calculator calculator = new Calculator();
        ViperMainContract.Interactor interactor = new ViperMainInteractor(calculator);
        ViperMainContract.Router router = new ViperMainRouter(this);
        presenter = new ViperMainPresenter(this, interactor, router);
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etNum1 = findViewById(R.id.et_num1);
        etNum2 = findViewById(R.id.et_num2);
        etResult = findViewById(R.id.et_result);
        tvError = findViewById(R.id.tv_error);
    }

    private void setListener() {
        Button btnPlus = findViewById(R.id.btn_plus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPlusButtonClicked(etNum1.getText().toString(), etNum2.getText().toString());
            }
        });

        Button btnMinus = findViewById(R.id.btn_minus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onMinusButtonClicked(etNum1.getText().toString(), etNum2.getText().toString());
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
