package com.example.max00.republica_sazon.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.max00.republica_sazon.R;

public class SplashScreen extends AppCompatActivity {

    private ViewGroup splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splash = findViewById(R.id.splash);

        //Thread para cambio de pantalla
        Thread timer= new Thread(){
            public void run(){
                try{
                    //Display for 1.5 seconds
                    sleep(1500);
                }
                catch (InterruptedException e){
                    // TODO: handle exception
                    e.printStackTrace();
                }

                finally
                {
                    Intent Startup = new Intent(getApplicationContext(), Login.class);
                    TransitionManager.beginDelayedTransition(splash);
                    startActivity(Startup);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}

