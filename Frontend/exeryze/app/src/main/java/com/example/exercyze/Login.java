package com.example.exercyze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private Button bLogin;
    private EditText etUsername, etPassword;
    private Button tvRegisterLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (Button) findViewById(R.id.tvRegisterLink);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bLogin:
                        break;

                    case R.id.tvRegisterLink:
                        openRegister();
                        break;
                }
            }
        });
        tvRegisterLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bLogin:
                        break;

                    case R.id.tvRegisterLink:
                        openRegister();
                        break;
                }
            }
        });

    }

    public void openRegister()
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
