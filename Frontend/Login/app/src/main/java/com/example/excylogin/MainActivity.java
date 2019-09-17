package com.example.excylogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.Register);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                openSignUp();
            }
        });

    }

    public void openSignUp()
    {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }
}
