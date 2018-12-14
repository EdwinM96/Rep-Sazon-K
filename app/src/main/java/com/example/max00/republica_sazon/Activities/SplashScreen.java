package com.example.max00.republica_sazon.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.max00.republica_sazon.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Thread para cambio de pantalla
        Thread timer= new Thread(){
            public void run(){
                try{
                    //Display for 3 seconds
                    sleep(3000);
                }
                catch (InterruptedException e){
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally{
                    Intent Startup = new Intent(getApplicationContext(), MainActivity.class);
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

