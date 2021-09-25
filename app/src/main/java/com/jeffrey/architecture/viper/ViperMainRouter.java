package com.jeffrey.architecture.viper;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.jeffrey.architecture.RootActivity;

public class ViperMainRouter implements ViperMainContract.Router {

    private final AppCompatActivity activity;

    public ViperMainRouter(ViperMainContract.View view) {
        this.activity = view instanceof AppCompatActivity ? (AppCompatActivity) view : null;
    }

    @Override
    public void goToNextScreen() {
        navigateTo(RootActivity.class);
    }

    private void navigateTo(Class<?> cls) {
        if (activity == null)
            return;
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }
}
