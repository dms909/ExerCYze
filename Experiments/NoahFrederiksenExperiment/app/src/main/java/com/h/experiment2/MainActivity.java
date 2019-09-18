package com.h.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button dateButton;
    boolean dateFormat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateButton = findViewById(R.id.Date_Btn);
        dateButton.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate =  findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.Date_Btn:
                if (!dateFormat) {
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                    TextView textViewDate = findViewById(R.id.text_view_date);
                    textViewDate.setText(currentDate);
                    
                    dateFormat = true; 
                }
                if (dateFormat) {
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                    TextView textViewDate = findViewById(R.id.text_view_date);
                    textViewDate.setText(currentDate);
                    
                    dateFormat = false; 
                }
                break;
            default:
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                TextView textViewDate = findViewById(R.id.text_view_date);
                textViewDate.setText(currentDate);
        }
    }

    /*public void changeDateFormat(View view) {

        int date = 0;
        if (date == 0) {
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

            TextView textViewDate = findViewById(R.id.text_view_date);
            textViewDate.setText(currentDate);

            date = date + 1;


        }
        if (date != 0) {
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());

            TextView textViewDate =  findViewById(R.id.text_view_date);
            textViewDate.setText(currentDate);

            date = date * 0;
        }

    }*/
}
