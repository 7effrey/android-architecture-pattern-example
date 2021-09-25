package com.jeffrey.architecture.mvvm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jeffrey.architecture.R;
import com.jeffrey.architecture.model.Calculator;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MvvmMainActivity extends AppCompatActivity {

    private EditText etNum1;
    private EditText etNum2;
    private EditText etResult;
    private TextView tvError;

    private MvvmMainContract.ViewModel viewModel;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        Calculator calculator = new Calculator();
        viewModel = new MvvmMainViewModel(calculator);
        setListener();
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
        btnPlus.setOnClickListener(view ->
                viewModel.calculatePlus(etNum1.getText().toString(), etNum2.getText().toString())
        );

        Button btnMinus = findViewById(R.id.btn_minus);
        btnMinus.setOnClickListener(view ->
                viewModel.calculateMinus(etNum1.getText().toString(), etNum2.getText().toString())
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();

        Disposable disposable = viewModel.getShowResult()
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(integer -> etResult.setText(integer.toString()), throwable -> tvError.setText(throwable.getMessage()));
        compositeDisposable.add(disposable);

        viewModel.getClear()
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(o -> {
                    etResult.setText("");
                    tvError.setText("");
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    private Scheduler getIoScheduler() {
        return Schedulers.io();
    }

    private Scheduler getUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
