package com.fountane.www.fountanehrapp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Utils.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sessionManager = new SessionManager(this);

        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {


                if(sessionManager.getLogInStatus()){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i);
                }

                // close this activity

                finish();

            }

        }, 3*1000);

    }
}
