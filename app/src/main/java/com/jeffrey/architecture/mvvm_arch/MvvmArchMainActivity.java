package com.jeffrey.architecture.mvvm_arch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.jeffrey.architecture.databinding.ActivityMainBinding;
import com.jeffrey.architecture.model.Calculator;

public class MvvmArchMainActivity extends AppCompatActivity {

    private MvvmArchMainContract.ViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        Calculator calculator = new Calculator();
        MvvmArchMainViewModel vm = new ViewModelProvider(this).get(MvvmArchMainViewModel.class);
        vm.calculator = calculator;

        viewModel = vm;
        bindLiveData();
        setEvent();
    }

    private void bindLiveData() {
        viewModel.getResult().observe(this, result -> binding.main.etResult.setText(result));

        viewModel.getError().observe(this, error -> binding.main.tvError.setText(error));

        viewModel.getClear().observe(this, v -> {
            binding.main.tvError.setText("");
            binding.main.etResult.setText("");
        });
    }

    private void setEvent() {
        binding.main.btnPlus.setOnClickListener(view ->
                viewModel.calculatePlus(
                        binding.main.etNum1.getText().toString(),
                        binding.main.etNum2.getText().toString()
                )
        );

        binding.main.btnMinus.setOnClickListener(view ->
                viewModel.calculateMinus(
                        binding.main.etNum1.getText().toString(),
                        binding.main.etNum2.getText().toString()
                )
        );
    }
}
