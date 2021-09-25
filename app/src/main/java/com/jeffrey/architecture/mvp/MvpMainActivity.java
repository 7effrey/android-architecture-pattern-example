package com.jeffrey.architecture.mvp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jeffrey.architecture.RootActivity;
import com.jeffrey.architecture.databinding.ActivityMainBinding;
import com.jeffrey.architecture.model.Calculator;

public class MvpMainActivity extends AppCompatActivity implements MvpMainContract.View {

    private ActivityMainBinding binding;

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
    }

    private void setListener() {
        binding.main.btnPlus.setOnClickListener(view ->
                presenter.calculatePlus(
                        binding.main.etNum1.getText().toString(),
                        binding.main.etNum2.getText().toString()
                )
        );

        binding.main.btnMinus.setOnClickListener(view ->
                presenter.calculateMinus(
                        binding.main.etNum1.getText().toString(),
                        binding.main.etNum2.getText().toString()
                )
        );
    }

    @Override
    public void clear() {
        binding.main.etResult.setText("");
        binding.main.tvError.setText("");
    }

    @Override
    public void showResult(int result) {
        binding.main.etResult.setText(String.valueOf(result));
    }

    @Override
    public void showError(String error) {
        binding.main.tvError.setText(error);
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
