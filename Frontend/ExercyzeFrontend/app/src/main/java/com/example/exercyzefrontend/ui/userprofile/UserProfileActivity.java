package com.example.exercyzefrontend.ui.userprofile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercyzefrontend.R;

import org.w3c.dom.Text;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

    Button workoutBtn, progressBtn, socialBtn;
    TextView userNameTV, userRealNameTV, userHeightTV, userWeightTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userNameTV = (TextView) findViewById(R.id.userName);
        userRealNameTV = (TextView) findViewById(R.id.userRealName);
        userHeightTV = (TextView) findViewById(R.id.userHeight);
        userWeightTV = (TextView) findViewById(R.id.userWeight);

        workoutBtn = (Button) findViewById(R.id.workoutBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);
        socialBtn = (Button) findViewById(R.id.socialBtn);

        workoutBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        socialBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.workoutBtn:
                //TO DO
                break;

            case R.id.progressBtn:
                //TO DO
                break;

            case R.id.socialBtn:
                //TO DO
                break;
        }
    }
}
