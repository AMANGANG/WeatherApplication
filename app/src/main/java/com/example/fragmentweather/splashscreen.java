package com.example.fragmentweather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

//// TODO File and class name should start with a capital letter for bifurcation activities should end with activity
////e.g. SplashScreenActivity and fragments e.g. SplashScreenFragment
public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreen);
        ////TODO please explain what is Handler, Looper.getMainLooper(),
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); ////TODO no magic literals
    }
}