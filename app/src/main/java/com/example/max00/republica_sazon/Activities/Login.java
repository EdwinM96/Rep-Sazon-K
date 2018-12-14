package com.example.max00.republica_sazon.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.max00.republica_sazon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RelativeLayout relativeLayout, relativeLayoutbelowimg;
    private Handler handler = new Handler();
    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        final ViewGroup transition = findViewById(R.id.transtion);
        relativeLayout = findViewById(R.id.belowimg);
        relativeLayoutbelowimg = findViewById(R.id.ll_data);
        login = findViewById(R.id.loginbtn);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                relativeLayout.setVisibility(View.VISIBLE);
                TransitionManager.beginDelayedTransition(transition);
                relativeLayoutbelowimg.setVisibility(View.VISIBLE);
            }
        };

        handler.postDelayed(runnable, 1000);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(Login.this,"Ingrese todos los campos",Toast.LENGTH_SHORT).show();
                } else {
                    signIn(email.getText().toString(),password.getText().toString(),transition);
                }
            }
        });
    }


    public void signIn(String email, String password, final ViewGroup transition){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this,"Inicio de sesion exitoso",Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            TransitionManager.beginDelayedTransition(transition);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this,"Inicio de sesion fallido",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
