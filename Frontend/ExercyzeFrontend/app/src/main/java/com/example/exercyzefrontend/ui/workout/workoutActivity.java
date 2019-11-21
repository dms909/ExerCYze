package com.example.exercyzefrontend.ui.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.app.AppController;
import com.example.exercyzefrontend.ui.login.LoginActivity;
import com.example.exercyzefrontend.ui.workoutroutine.workoutroutineActivity;
import com.example.exercyzefrontend.utils.Const;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.exercyzefrontend.R;

public class workoutActivity extends AppCompatActivity implements WorkoutEntryDialog.WorkoutEntryDialogListener {


    private String TAG = workoutActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req";
    private ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> routineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        listView = findViewById(R.id.routineListView);
        String routineNameStr = "";
        Intent workoutViewItemIntent = getIntent();
        routineNameStr = workoutViewItemIntent.getStringExtra("workout_name");
        //ArrayList<String> routineList = new ArrayList<>();
        routineList = new ArrayList<>();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineList);
        listView.setAdapter(arrayAdapter);

        final Button addItemBtn = (Button) findViewById(R.id.workoutItemAddBtn);
        final Button delItemBtn = (Button) findViewById(R.id.workoutItemDeleteBtn);
        final Button saveBtn = (Button) findViewById(R.id.saveBtn);
        final Button discardBtn = (Button) findViewById(R.id.discardBtn);
        //final TextView routineName = (TextView) findViewById(R.id.routineName);
        final EditText routineName = (EditText) findViewById(R.id.routineNameET);

        routineName.setText(routineNameStr);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEntryDialog();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String routineNameInput = routineName.getText().toString();

                //postUserModel(workout, creator);
                Intent routineActivty = new Intent(getApplicationContext(), workoutroutineActivity.class);

                startActivity(routineActivty);
            }
        });
        discardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routineActivty = new Intent(getApplicationContext(), workoutroutineActivity.class);
                startActivity(routineActivty);
            }
        });
    }

    public void openEntryDialog() {
        WorkoutEntryDialog entryDialog = new WorkoutEntryDialog();
        entryDialog.show(getSupportFragmentManager(), "workout entry dialog");
    }

    @Override
    public void applyValue(String workoutItemEntryStr, int setEntry, int repEntry){
        routineList.add(workoutItemEntryStr + " \t \t " + setEntry + " x " + repEntry);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineList);
        listView.setAdapter(arrayAdapter);
    }

    private void postUserModel(String workoutName, int sets, int reps){
        //TODO
        //need to fix postUserModel to send workout items which are going to be attached
        //to the workoutroutine id from backend
        final Map<String, String> params = new HashMap<String, String>();
        params.put("workoutName", workoutName);
        params.put("reps", reps + "");
        params.put("sets", sets +  ""); 
        //params.put("workoutRoutineCreator", sets);
        //params.put("workoutRoutineCreator", reps);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_WORKOUTROUTINE_OBJECT, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage()
                        + "Error cause: "  + error.getCause());

            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
