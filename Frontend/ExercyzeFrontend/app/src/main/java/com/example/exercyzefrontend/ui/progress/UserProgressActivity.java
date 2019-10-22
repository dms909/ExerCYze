package com.example.exercyzefrontend.ui.progress;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercyzefrontend.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class UserProgressActivity extends AppCompatActivity {

    BarChart userProgressBC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_progress);

        userProgressBC = (BarChart) findViewById(R.id.userProgressBC);

        ArrayList<BarEntry> userEntries = new ArrayList<>();
        userEntries.add(new BarEntry((150f), 0));
        userEntries.add(new BarEntry((148f), 1));
        userEntries.add(new BarEntry((151f), 2));
        userEntries.add(new BarEntry((152f), 3));
        userEntries.add(new BarEntry((151f), 4));
        userEntries.add(new BarEntry((151f), 5));
        userEntries.add(new BarEntry((151f), 6));
        BarDataSet userProgressDataSet = new BarDataSet(userEntries, "Weight (lbs)");

        ArrayList<String> datesByWeek = new ArrayList<>();
        datesByWeek.add("10/21");
        datesByWeek.add("10/22");
        datesByWeek.add("10/23");
        datesByWeek.add("10/24");
        datesByWeek.add("10/25");
        datesByWeek.add("10/26");
        datesByWeek.add("10/27");

        BarData userProgressData = new BarData(datesByWeek, userProgressDataSet);
        userProgressBC.setData(userProgressData);

        userProgressBC.setTouchEnabled(true);
        userProgressBC.setDragEnabled(true);
        userProgressBC.setScaleEnabled(true);
    }
}
