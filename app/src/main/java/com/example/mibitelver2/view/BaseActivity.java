package com.example.mibitelver2.view;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mibitelver2.R;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {

    private boolean backAgainToExit = false;
    private Dialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void showErrorSnackBar(String message, Boolean err) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        if (err) {
            snackBarView.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.errorColor));
        } else {
            snackBarView.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.successColor));
        }
        snackbar.show();
    }

//    public void showProgressBar() {
//        progressBar = new Dialog(this);
//
//        progressBar.setContentView(R.layout.dialog_progress);
//
//        progressBar.setCancelable(false);
//        progressBar.setCanceledOnTouchOutside(false);
//
//        progressBar.show();
//    }
//
//    public void hideProgressBar() {
//        progressBar.dismiss();
//    }

    public void pressBackAgainToExit() {
        if (backAgainToExit) {
            super.onBackPressed();
            return;
        }
        this.backAgainToExit = true;

        Toast.makeText(this, R.string.press_back_again_to_exit,
                Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper())
                .postDelayed(() -> backAgainToExit = false, 1999);
    }
}
