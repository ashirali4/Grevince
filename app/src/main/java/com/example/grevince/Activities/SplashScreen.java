package com.example.grevince.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.grevince.Dashboard;
import com.example.grevince.R;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        l=findViewById(R.id.animation);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable
        l.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                    Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                    startActivity(intent);


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
    }

