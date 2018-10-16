package com.jeffrey.architecture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jeffrey.architecture.mvp.MvpMainActivity;
import com.jeffrey.architecture.mvvm.MvvmMainActivity;
import com.jeffrey.architecture.mvvm_db.MvvmDbMainActivity;
import com.jeffrey.architecture.ribs.RibsMainActivity;
import com.jeffrey.architecture.viper.ViperMainActivity;

public class RootActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        Button btnMvp = findViewById(R.id.btn_mvp);
        btnMvp.setOnClickListener(this);

        Button btnMvvm = findViewById(R.id.btn_mvvm);
        btnMvvm.setOnClickListener(this);

        Button btnMvvmDb = findViewById(R.id.btn_mvvm_db);
        btnMvvmDb.setOnClickListener(this);

        Button btnRibs = findViewById(R.id.btn_ribs);
        btnRibs.setOnClickListener(this);

        Button btnViper = findViewById(R.id.btn_viper);
        btnViper.setOnClickListener(this);
    }

    private void navigateTo(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mvp:
                navigateTo(MvpMainActivity.class);
                break;
            case R.id.btn_mvvm:
                navigateTo(MvvmMainActivity.class);
                break;
            case R.id.btn_mvvm_db:
                navigateTo(MvvmDbMainActivity.class);
                break;
            case R.id.btn_viper:
                navigateTo(ViperMainActivity.class);
                break;
            case R.id.btn_ribs:
                navigateTo(RibsMainActivity.class);
                break;

        }
    }
}
