package com.jeffrey.architecture.mvvm_db;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.jeffrey.architecture.R;
import com.jeffrey.architecture.databinding.ActivityMvvmDbMainBinding;
import com.jeffrey.architecture.model.Calculator;

public class MvvmDbMainActivity extends AppCompatActivity {

    private MvvmDbMainContract.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmDbMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_db_main);
        Calculator calculator = new Calculator();
        viewModel = new MvvmDbMainViewModel(calculator);
        binding.setViewModel(viewModel);

        binding.etNum1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.getNumber1().set(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etNum2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.getNumber2().set(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        setTitle(R.string.title_activity_mvvm_db_main);
    }
}
