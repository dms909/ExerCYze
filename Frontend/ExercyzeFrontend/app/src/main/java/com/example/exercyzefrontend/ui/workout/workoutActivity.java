package com.example.exercyzefrontend.ui.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.exercyzefrontend.R;

public class workoutActivity extends AppCompatActivity implements WorkoutEntryDialog.WorkoutEntryDialogListener {


    private String TAG = workoutActivity.class.getSimpleName();
    private String finalresult;

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> routineList;
    private ArrayList<Workout> routineWorkoutList;
    private String routineNameStr;
    private String routineID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        listView = findViewById(R.id.routineListView);

        Intent workoutViewItemIntent = getIntent();
        routineNameStr = workoutViewItemIntent.getStringExtra("workout_name");
        routineID = workoutViewItemIntent.getStringExtra("workout_id");

        routineList = new ArrayList<>();
        routineWorkoutList = new ArrayList<>();

        new GetJsonData().execute();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineList);
        listView.setAdapter(arrayAdapter);

        final Button addItemBtn = (Button) findViewById(R.id.workoutItemAddBtn);
        final Button delItemBtn = (Button) findViewById(R.id.workoutItemDeleteBtn);
        final Button saveBtn = (Button) findViewById(R.id.saveBtn);
        final Button discardBtn = (Button) findViewById(R.id.discardBtn);
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
                for(int i=0; i<routineList.size(); i++) {
                    String newWorkoutItem = routineWorkoutList.get(i).getWorkoutItem();
                    int newSets = routineWorkoutList.get(i).getSets();
                    int newReps = routineWorkoutList.get(i).getReps();
                    postWorkoutItemModel(newWorkoutItem, newSets, newReps, routineID);
                }
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

    private void setAdapter(ArrayList<String> aList) {
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aList);
        listView.setAdapter(arrayAdapter);
    }

    public void openEntryDialog() {
        WorkoutEntryDialog entryDialog = new WorkoutEntryDialog();
        entryDialog.show(getSupportFragmentManager(), "workout entry dialog");
    }

    @Override
    public void applyValue(String workoutItemEntryStr, int setEntry, int repEntry){
        routineList.add(workoutItemEntryStr + " \t \t " + setEntry + " x " + repEntry);
        routineWorkoutList.add(new Workout(workoutItemEntryStr,setEntry,repEntry));
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineList);
        listView.setAdapter(arrayAdapter);
    }

    private class GetJsonData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String getUrl = "http://coms-309-sb-7.misc.iastate.edu:8080/api/workout/getRoutine/" + routineID;
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                url = new URL(getUrl);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

                StringBuffer response = new StringBuffer();
                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) { //success
                    BufferedReader inurl = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
                    String inputLine;
                    while ((inputLine = inurl.readLine()) != null) {
                        response.append(inputLine);
                    }
                    inurl.close();

                } else {

                    Log.i("test", "GET request not worked.");
                }

                finalresult = response.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                parseJson(finalresult);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void parseJson(String json) throws JSONException {

            JSONArray jArr = new JSONArray(json);
            String workoutItemName = "";
            int workoutItemSets = 0;
            int workoutItemReps = 0;

            for (int count = 0; count < jArr.length(); count++) {
                JSONObject obj = jArr.getJSONObject(count);
                workoutItemName = obj.getString("workoutName");
                workoutItemReps = Integer.parseInt(obj.getString("reps"));
                workoutItemSets = Integer.parseInt(obj.getString("sets"));
                routineWorkoutList.add(new Workout(workoutItemName, workoutItemSets, workoutItemReps));
                routineList.add(workoutItemName + " \t \t " + workoutItemSets + " x " + workoutItemReps);
            }
        }
    }

    private void postWorkoutItemModel(String workoutName, int sets, int reps, String workoutRoutineID){
        final Map<String, String> params = new HashMap<String, String>();
        params.put("workoutRoutineId", workoutRoutineID );
        params.put("workoutName", workoutName);
        params.put("reps", reps + "");
        params.put("sets", sets + "");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_WORKOUTITEM_ADD, new JSONObject(params),
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
