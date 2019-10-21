package com.example.exercyzefrontend.ui.workoutroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercyzefrontend.R;

public class workoutroutineActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter aAdapter;
    private String[] users = {"Meet", "Dylan", "Noah", "Austin"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutroutine);
        mListView = (ListView) findViewById(R.id.userlist);
        aAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        mListView.setAdapter(aAdapter);
    }
}
