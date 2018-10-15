package com.jeffrey.architecture.mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jeffrey.architecture.R;
import com.jeffrey.architecture.model.Calculator;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.calculatePlus(etNum1.getText().toString(), etNum2.getText().toString());
            }
        });

        Button btnMinus = findViewById(R.id.btn_minus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.calculateMinus(etNum1.getText().toString(), etNum2.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();

        Disposable disposable = viewModel.getShowResult()
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        etResult.setText(integer.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        tvError.setText(throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);

        viewModel.getClear()
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        etResult.setText("");
                        tvError.setText("");
                    }
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
