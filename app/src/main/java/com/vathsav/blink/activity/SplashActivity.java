package com.vathsav.blink.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vathsav.blink.R;
import com.vathsav.blink.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(Constants.intentMainActivity));
                    finish();
                } catch (Exception ex) {
                    Log.e(Constants.LOG_ERROR, ex.getMessage());
                }
            }
        }.start();
    }
}