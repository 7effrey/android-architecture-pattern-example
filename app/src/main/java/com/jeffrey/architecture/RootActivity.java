package com.jeffrey.architecture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jeffrey.architecture.mvp.MvpMainActivity;
import com.jeffrey.architecture.mvvm.MvvmMainActivity;
import com.jeffrey.architecture.mvvm_arch.MvvmArchMainActivity;
import com.jeffrey.architecture.mvvm_db.MvvmDbMainActivity;
import com.jeffrey.architecture.ribs.RibsMainActivity;
import com.jeffrey.architecture.viper.ViperMainActivity;

import java.util.Arrays;
import java.util.List;

public class RootActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        List<View> buttons = Arrays.asList(
            findViewById(R.id.btn_mvp),
            findViewById(R.id.btn_mvvm),
            findViewById(R.id.btn_mvvm_arch),
            findViewById(R.id.btn_mvvm_db),
            findViewById(R.id.btn_ribs),
            findViewById(R.id.btn_viper)
        );
        for (View button : buttons) {
            button.setOnClickListener(this);
        }
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
            case R.id.btn_mvvm_arch:
                navigateTo(MvvmArchMainActivity.class);
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
