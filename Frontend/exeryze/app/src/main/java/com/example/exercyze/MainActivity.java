package com.example.exercyze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {

    private Button bLogout;
    private EditText etName, etAge, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bLogout:
                        openLogin();
                        break;
                }
            }
        });
    }

    public void openLogin()
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
