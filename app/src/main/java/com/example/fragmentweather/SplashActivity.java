package com.example.fragmentweather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

//// TODO File and class name should start with a capital letter for bifurcation activities should end with activity
////e.g. SplashScreenActivity and fragments e.g. SplashScreenFragment
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreen);
        /*
        ////TODO please explain what is Handler, Looper.getMainLooper(),
        Handler is basically used  to schedule code that should be executed at some point in the future,
        or to enqueue an action which we want to  be performed on a different thread than our own.
        Looper.getMainLooper() returns the application's main thread of the application.
        */
        final int SPLASH_SCREEN_DELAY_TIME = 3000;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_DELAY_TIME); ////TODO no magic literals
    }
}