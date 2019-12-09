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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.app.AppController;
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;
import com.example.exercyzefrontend.ui.workout.workoutActivity;
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
import java.util.List;
import java.util.Map;

public class workoutroutineActivity extends AppCompatActivity implements WorkoutNameEntryDialog.WorkoutNameEntryDialogListener {

    private String TAG = workoutActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req";
    private ListView mListView;
    private ArrayAdapter arrayAdapter;
    //List<String> value = new ArrayList<>();
    private Button addworkout, saveBtn, exitBtn;
    private String finalresult;
    private ArrayList<String> routineNameList;
    private ArrayList<WorkoutRoutine> workoutRoutineViewList;
    private String creator;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutroutine);


        mListView = (ListView) findViewById(R.id.userRoutineList);
        addworkout = (Button) findViewById(R.id.addRoutineBtn);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);

        creator = getIntent().getStringExtra("user_name");

        routineNameList = new ArrayList<>();
        workoutRoutineViewList = new ArrayList<>();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineNameList);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String workoutViewStr = "";
                int workoutID = 0;
                workoutViewStr = workoutRoutineViewList.get(i).getWorkoutRoutineName();
                workoutID = workoutRoutineViewList.get(i).getWorkoutRoutineID();
                Intent workoutItemIntent = new Intent(workoutroutineActivity.this, workoutActivity.class);
                workoutItemIntent.putExtra("workout_name", workoutViewStr);
                workoutItemIntent.putExtra("workout_id", workoutID+"");
                startActivity(workoutItemIntent);
            }
        });

        addworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEntryDialog();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "";
                for (int i=0; i<routineNameList.size(); i++) {
                    name = routineNameList.get(i);
                    postWorkoutRoutineModel(name, creator);
                }
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userProfileIntent = new Intent(workoutroutineActivity.this, UserProfileActivity.class);
                userProfileIntent.putExtra("user_name", creator);
                startActivity(userProfileIntent);
            }
        });


        new GetJsonData().execute();

    }
    private void setAdatper(ArrayList<String> aList) {
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineNameList);
        mListView.setAdapter(arrayAdapter);
    }

    public void openEntryDialog() {
        WorkoutNameEntryDialog entryDialog = new WorkoutNameEntryDialog();
        entryDialog.show(getSupportFragmentManager(), "workout name entry dialog");
    }

    @Override
    public void applyValue(String workoutNameEntryStr) {
        routineNameList.add(workoutNameEntryStr);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, routineNameList);
        mListView.setAdapter(arrayAdapter);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          METHOD FOR JSON GET                                                                                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
            String workoutID = "";

            for (int count = 0; count < jArr.length(); count++) {
                JSONObject obj = jArr.getJSONObject(count);
                if (obj.getString("workoutRoutineCreator").equals(creator)) {
                    workoutname = obj.getString("workoutRoutineName");
                    workoutID = obj.getString("id");
                    if (workoutname == "null") {
                        // do nothing
                    } else {
                        routineNameList.add(workoutname);

                        workoutRoutineViewList.add(new WorkoutRoutine((Integer.parseInt(workoutID)), workoutname,creator));
                    }
                }

            }
            setAdatper(routineNameList);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          METHOD FOR JSON POST                                                                                                     //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * postUserModel will be posting the workoutNames to the server when a new
     * workout routine is created. Workout item information is associated with
     * the specific routine name which is stored as an id for the item activity
     * to see
     *
     * @param workoutName name of new workout name to be stored in backend
     * @param creator name of the user who created the workout
     */
    private void postWorkoutRoutineModel(String workoutName, String creator) {
        final Map<String, String> params = new HashMap<String, String>();

        params.put("workoutRoutineName", workoutName);
        params.put("workoutRoutineCreator", creator);

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
                System.out.println("error: " + error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage()
                        + "Error cause: " + error.getCause());
                error.printStackTrace();

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
