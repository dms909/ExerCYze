package com.example.exercyzefrontend.ui.workoutroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;
import com.example.exercyzefrontend.ui.workout.workoutActivity;

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
import java.util.List;

public class workoutroutineActivity extends AppCompatActivity implements WorkoutNameEntryDialog.WorkoutNameEntryDialogListener {

    private ListView mListView;
    private ArrayAdapter arrayAdapter;
    //List<String> value = new ArrayList<>();
    Button addworkout, exitworkout;
    private String finalresult;
    ArrayList<String> routineNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutroutine);

        mListView = (ListView) findViewById(R.id.userRoutineList);
        addworkout = (Button) findViewById(R.id.addRoutineBtn);
        exitworkout = (Button) findViewById(R.id.exitworkout);

        routineNameList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineNameList);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String workoutViewStr = "";
                workoutViewStr = routineNameList.get(i);
                Intent workoutItemIntent = new Intent(workoutroutineActivity.this, workoutActivity.class);
                workoutItemIntent.putExtra("workout_name", workoutViewStr);
                startActivity(workoutItemIntent);
            }
        });

        addworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEntryDialog();
            }
        });
        exitworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routineActivty = new Intent(getApplicationContext(), workoutroutineActivity.class);
                startActivity(routineActivty);
            }
        });



        //new GetJsonData().execute();

        }

        public void openEntryDialog () {
            WorkoutNameEntryDialog entryDialog = new WorkoutNameEntryDialog();
            entryDialog.show(getSupportFragmentManager(), "workout name entry dialog");
        }

        @Override
        public void applyValue (String workoutNameEntryStr){
            routineNameList.add(workoutNameEntryStr);
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineNameList);
            mListView.setAdapter(arrayAdapter);
        }

        private class GetJsonData extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // before making http calls

            }

            @Override
            protected Void doInBackground(Void... arg0) {


                String getUrl = "http://coms-309-sb-7.misc.iastate.edu:8080/api/workout-routine";
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
                String workoutname = "";

                for (int count = 0; count < jArr.length(); count++) {
                    JSONObject obj = jArr.getJSONObject(count);
                    workoutname = obj.getString("workoutRoutineName");
                    if (workoutname == "null") {
                        // do nothing
                    } else {
                        routineNameList.add(workoutname);
                    }

                }
            }
        }
    }
