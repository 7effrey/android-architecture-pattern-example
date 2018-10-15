package com.jeffrey.architecture.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jeffrey.architecture.R;
import com.jeffrey.architecture.RootActivity;
import com.jeffrey.architecture.model.Calculator;

public class MvpMainActivity extends AppCompatActivity implements MvpMainContract.View {

    private EditText etNum1;
    private EditText etNum2;
    private EditText etResult;
    private TextView tvError;

    private MvpMainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();

        Calculator calculator = new Calculator();
        presenter = new MvpMainPresenter(this, calculator);
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
                presenter.calculatePlus(etNum1.getText().toString(), etNum2.getText().toString());
            }
        });

        Button btnMinus = findViewById(R.id.btn_minus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.calculateMinus(etNum1.getText().toString(), etNum2.getText().toString());
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

    @Override
    public void goToNextScreen() {
        navigateTo(RootActivity.class);
    }

    private void navigateTo(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
