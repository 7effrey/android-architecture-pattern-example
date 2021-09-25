package com.jeffrey.architecture.mvvm_arch;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
        viewModel.getResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.main.etResult.setText(s);
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.main.tvError.setText(s);
            }
        });

        viewModel.getClear().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void v) {
                binding.main.tvError.setText("");
                binding.main.etResult.setText("");
            }
        });
    }

    private void setEvent() {
        binding.main.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.calculatePlus(binding.main.etNum1.getText().toString(), binding.main.etNum2.getText().toString());
            }
        });

        binding.main.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.calculateMinus(binding.main.etNum1.getText().toString(), binding.main.etNum2.getText().toString());
            }
        });
    }
}
